package com.patho.messenger.activities;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.patho.messenger.R;
import com.patho.messenger.locater.PlaceJSONParser;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

/**
 * Created by eren on 1.03.2017.
 */

public class MapActivity extends FragmentActivity implements LocationListener{

    GoogleMap googleMap;
    double latitude;
    double longitude;

    //Yer Gösterimleri İçin
    Spinner mSprPlaceType;
    Button bt_showPlaces,bt_takeSS;
    String[] mPlaceType=null;
    String[] mPlaceTypeName=null;
    String placeType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        mPlaceType = getResources().getStringArray(R.array.place_type);
        mPlaceTypeName = getResources().getStringArray(R.array.place_type_name);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, mPlaceTypeName);
        mSprPlaceType = (Spinner) findViewById(R.id.spinner_locations);
        bt_showPlaces = (Button) findViewById(R.id.bt_showPlaces);
        bt_takeSS = (Button) findViewById(R.id.bt_takeSS);

        bt_takeSS.setVisibility(View.GONE);
        mSprPlaceType.setAdapter(adapter);


        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getBaseContext());
        if (status != ConnectionResult.SUCCESS) { // Google Play Services are not available
            int requestCode = 10;
            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(status, this, requestCode);
            dialog.show();
        } else { // Google Play Servisi Ulaşılabilir

            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            //  GoogleMap objesini map fragmentdan alma
            googleMap = fm.getMap();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            googleMap.setMyLocationEnabled(true);
            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, true);

            //Şuanki Konumu Alma
            Location location = locationManager.getLastKnownLocation(provider);

            if (location != null) {
                onLocationChanged(location);
            }
            locationManager.requestLocationUpdates(provider, 20000, 0, this);
        }

        bt_showPlaces.setOnClickListener(new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            bt_takeSS.setVisibility(View.VISIBLE);
            int selectedPosition = mSprPlaceType.getSelectedItemPosition();
            placeType = mSprPlaceType.getSelectedItem().toString();
            String type = mPlaceType[selectedPosition];
            StringBuilder sb = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
            //ankara,kızılay: location=39.920860,32.854240
            //akyaka: location=37.0557,28.3242
            sb.append("location="+latitude+","+longitude);
            sb.append("&radius=30000");
            sb.append("&types="+type);
            sb.append("&sensor=true");
            sb.append("&key=AIzaSyBUECmAA1GSbhzZrwsgYAWVDm46Ao-wSts");  //google api place web service keyim
            PlacesTask placesTask = new PlacesTask();
            placesTask.execute(sb.toString());


        }
    });
        bt_takeSS.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                CaptureMapScreen();
            }
        });
        }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try{
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
            StringBuffer sb  = new StringBuffer();
            String line = "";
            while( ( line = br.readLine())  != null){
                sb.append(line);
            }
            data = sb.toString();
            br.close();

        }catch(Exception e){
            Log.d("Exception while", e.toString());
        }finally{
            iStream.close();
            urlConnection.disconnect();
        }

        return data;
    }

    private class PlacesTask extends AsyncTask<String, Integer, String> {

        String data = null;
        @Override
        protected String doInBackground(String... url) {
            try{
                data = downloadUrl(url[0]);
            }catch(Exception e){
                Log.d("Background Task",e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result){
            ParserTask parserTask = new ParserTask();
            parserTask.execute(result);
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<HashMap<String,String>>>{
        JSONObject jObject;
        @Override
        protected List<HashMap<String,String>> doInBackground(String... jsonData) {

            List<HashMap<String, String>> places = null;
            PlaceJSONParser placeJsonParser = new PlaceJSONParser();

            try{
                jObject = new JSONObject(jsonData[0]);
                places = placeJsonParser.parse(jObject);

            }catch(Exception e){
                Log.d("Exception",e.toString());
            }
            return places;
        }

        //İşaretleme Yeri
        @Override
        protected void onPostExecute(List<HashMap<String,String>> list){

            googleMap.clear();
            for(int i=0;i<list.size();i++){
                MarkerOptions markerOptions = new MarkerOptions();
                HashMap<String, String> hmPlace = list.get(i);
                double lat = Double.parseDouble(hmPlace.get("lat"));
                double lng = Double.parseDouble(hmPlace.get("lng"));
                final String name = hmPlace.get("place_name");
                final String vicinity = hmPlace.get("vicinity");
                LatLng latLng = new LatLng(lat, lng);
                markerOptions.position(latLng);
                markerOptions.title(name);
                markerOptions.snippet(vicinity);
                googleMap.addMarker(markerOptions);
            }
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        TextView tvLocation = (TextView) findViewById(R.id.tv_location);
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
        tvLocation.setText("Enlem:" + latitude + ", Boylam:" + longitude);
    }


    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void CaptureMapScreen()
    {
        GoogleMap.SnapshotReadyCallback callback = new GoogleMap.SnapshotReadyCallback() {
            Bitmap bitmap;

            @Override
            public void onSnapshotReady(Bitmap snapshot) {
                // TODO Auto-generated method stub
                bitmap = snapshot;
                try {
                    String root = Environment.getExternalStorageDirectory().toString();
                    File myDir = new File(root + "/Pictures/DevaBul/"); //galeriminresetlenmesı gerekıyor
                    myDir.mkdirs();
                    String filePath1 = myDir.toString();
                    FileOutputStream out = new FileOutputStream(filePath1 + "/"+System.currentTimeMillis()
                            + ".jpg");
                    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
                    Toast.makeText(getApplicationContext(), "Haritanın ekran görüntüsü galerinize kaydedildi !", Toast.LENGTH_LONG).show();


                    /*Intent mediaScannerIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    Uri fileContentUri = Uri.fromFile(myDir);
                    mediaScannerIntent.setData(fileContentUri);
                    MapActivity.this.sendBroadcast(mediaScannerIntent);*/

                    Uri contentUri = Uri.fromFile(myDir);
                    Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                    mediaScanIntent.setData(contentUri);
                    sendBroadcast(mediaScanIntent);


                    //sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + Environment.getExternalStorageDirectory())))


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        googleMap.snapshot(callback);
    }


   /* public void galleryAddPic(File currentPhotoPath) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(currentPhotoPath);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void refreshGallery(){
        MediaScannerConnection.scanFile(this,
            new String[] { Environment.getExternalStorageDirectory().toString() }, null,
            new MediaScannerConnection.OnScanCompletedListener() {
        public void onScanCompleted(String path, Uri uri) {
            Log.i("ExternalStorage", "Scanned " + path + ":");
            Log.i("ExternalStorage", "-> uri=" + uri);
        }
    });
    }

    private void refreshGallery2() {
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
                + Environment.getExternalStorageDirectory())));
    }*/
}
