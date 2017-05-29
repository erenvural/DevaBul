package com.patho.messenger.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.dao.ProfileImageRetrieveAndPostRequest;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Map;

/**
 * Created by eren on 9.03.2017.
 */

public class UploadImageActivity extends AppCompatActivity implements View.OnClickListener  {

    ProfileImageRetrieveAndPostRequest profileImageRetrieveAndPostRequest;
    private String username;
    private Button button_selectpic;
    private Button imageUploadButton;
    private ImageView imageView_pic;
    private Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);

        Bundle extras = getIntent().getExtras();
        username = extras.getString("username");

        button_selectpic = (Button) findViewById(R.id.button_selectpic);
        imageUploadButton = (Button) findViewById(R.id.uploadButton);
        imageView_pic  = (ImageView) findViewById(R.id.imageView_pic);
        button_selectpic.setOnClickListener(this);
        imageUploadButton.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == profileImageRetrieveAndPostRequest.PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView_pic.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(){
        final ProgressDialog loading = ProgressDialog.show(this,"Yükleniyor...","Lütfen Bekleyiniz...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, profileImageRetrieveAndPostRequest.IMAGE_UPLOAD_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(UploadImageActivity.this, s , Toast.LENGTH_LONG).show();
                        Log.v("Response :",s.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        loading.dismiss();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                String image = getStringImage(bitmap);
                Map<String,String> params = new Hashtable<String, String>();
                params.put(profileImageRetrieveAndPostRequest.KEY_IMAGE, image);
                params.put(profileImageRetrieveAndPostRequest.KEY_USER_NAME, username);
                return params;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Resim Seçiniz !"), profileImageRetrieveAndPostRequest.PICK_IMAGE_REQUEST);
    }

    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    @Override
    public void onClick(View v) {
        if(v == button_selectpic){
            showFileChooser();
        }
        if(v == imageUploadButton){
            uploadImage();
        }
    }
}