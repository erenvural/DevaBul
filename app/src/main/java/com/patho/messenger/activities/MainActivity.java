package com.patho.messenger.activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.controller.CommentController;
import com.patho.messenger.controller.DiseaseController;
import com.patho.messenger.controller.MessageController;
import com.patho.messenger.controller.ProfileSettingsController;
import com.patho.messenger.controller.SubjectConnectController;
import com.patho.messenger.controller.SubjectController;
import com.patho.messenger.controller.SuggestionController;
import com.patho.messenger.controller.UserConnectController;
import com.patho.messenger.controller.UserController;
import com.patho.messenger.controller.UserSessionManager;
import com.patho.messenger.dao.DiseaseSuggestRetrieveAndPostRequest;
import com.patho.messenger.fragment.ForumFragment;
import com.patho.messenger.fragment.TimelineFragment;
import com.patho.messenger.locater.GPSTracker;
import com.patho.messenger.locater.LocationAddress;
import com.patho.messenger.model.Disease;
import com.patho.messenger.model.DiseaseSuggestion;
import com.patho.messenger.model.User;
import com.patho.messenger.model.UserConnection;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ProgressDialog dialog;
    String otherUserEmail, otherUserGender, otherUserRelatedDisease, otherUserProfileStatus, followingUser;
    int followStatus=0;
    com.patho.messenger.model.Message msj;
    FindUsersActivity findUsersActivity;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date suggestDate;
    DiseaseSuggestRetrieveAndPostRequest dsrp;

    //Search
    List searchList= new ArrayList();

    Toolbar toolbar;

    UserSessionManager session;
    String fullAdress, username;
    final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Hastalıkların Çekildiği Yer
        final DiseaseController diseaseController = new DiseaseController();
        diseaseController.getDisease(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        diseaseController.parseDiseaseJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });


        //Konuların Çekildiği Yer
        final SubjectController subjectController = new SubjectController();
        subjectController.getSubject(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        subjectController.parseJSONfromDiseaseResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Yorum Çekilme Yeri
        final CommentController commentController = new CommentController();
        commentController.getComment(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        commentController.showJSONfromCommentResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Kullanıcı Profil Fotoğraflarının Çekilme Yeri
        final ProfileSettingsController profileSettingsController = new ProfileSettingsController();
        profileSettingsController.getProfileImage(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        profileSettingsController.parseProfileImageJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Kullanıcıların Çekildiği Yer
        final UserController userController = new UserController();
        userController.getUser(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        userController.parseUserJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Kullanıcı Konu Takip Bilgisi Çekilme Yeri
        final SubjectConnectController subjectConnectController = new SubjectConnectController();
        subjectConnectController.getSubjectConnect(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        subjectConnectController.parseSubjectConnectJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Kullanıcı Takip-Takipçi İlişkisi Çekilme Yeri
        final UserConnectController userConnectController = new UserConnectController();
        userConnectController.getUserConnect(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        userConnectController.parseUserConnectJSON(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Mesajların Çekilme Yeri
        final MessageController messageController = new MessageController();
        messageController.getMessage(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        messageController.parseJSONfromMessageResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });

        //Hastalık Önerileri Çekilme Yeri
        final SuggestionController suggestionController = new SuggestionController();
        suggestionController.getSuggest(
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String  response) {
                        SuggestionController.showJSONfromSuggestResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });


        //Fragments Control
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


        //Session Kontrol
        session = new UserSessionManager(getApplicationContext());
        session.checkLogin();
        HashMap<String, String> user = session.getUserDetails();
        username = user.get(UserSessionManager.KEY_NAME);
        getSupportActionBar().setTitle(username);

        final FloatingActionButton fb_findLocation = (FloatingActionButton) findViewById(R.id.fb_findLocation);
        fb_findLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GPSTracker gps = new GPSTracker(MainActivity.this);
                if (gps.canGetLocation()) {
                    double latitude = gps.getLatitude();
                    double longitude = gps.getLongitude();
                    Location location = gps.getLocation();
                    if (location != null) {
                        Toast.makeText(getBaseContext(), "Konumununuz Bulunuyor...!", Toast.LENGTH_LONG).show();
                        LocationAddress locationAddress = new LocationAddress();
                        locationAddress.getAddressFromLocation(latitude, longitude, getApplicationContext(), new GeocoderHandler() {
                        });
                        Snackbar snackbar = Snackbar
                                .make(view, "Haritaya gitmek için 'GİT' e tıklayın !", Snackbar.LENGTH_LONG)
                                .setAction("GİT", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent mapIntent = new Intent(MainActivity.this, MapActivity.class);
                                        MainActivity.this.startActivity(mapIntent);
                                    }
                                });
                        snackbar.show();
                    }else{
                        Toast.makeText(getBaseContext(), "Konumunuz Bulunamadı. Tekrar deneyin !!", Toast.LENGTH_LONG).show();
                        Intent reMainActivity = new Intent(MainActivity.this, MainActivity.class);
                        MainActivity.this.startActivity(reMainActivity);

                    }
                } else {
                    gps.showSettingsAlert();
                }
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {
            Intent profileIntent = new Intent(MainActivity.this, ProfileActivity.class);
            MainActivity.this.startActivity(profileIntent);
        } else if (id == R.id.nav_manage) {
            //Çıkış ve session sıfırlama
            session.setUserName(MainActivity.this,null);
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            MainActivity.this.startActivity(loginIntent);
        }
        else if (id == R.id.nav_suggest) {
            //Hastalık Önerme
            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.suggest_part, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptsView);
            final EditText et_suggestName = (EditText) promptsView.findViewById(R.id.et_suggestName);
            final EditText et_suggestType = (EditText) promptsView.findViewById(R.id.et_suggestType);
            final EditText et_suggestDesc = (EditText) promptsView.findViewById(R.id.et_suggestDesc);
            alertDialogBuilder
                    .setCancelable(false)
                    .setPositiveButton("Yolla",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    String suggestName = et_suggestName.getText().toString();
                                    String suggestType = et_suggestType.getText().toString();
                                    String suggestDescription = et_suggestDesc.getText().toString();
                                    try {
                                        if (suggestName.length()!=0 && suggestType.length()!=0 && suggestDescription.length()!=0){
                                            addDiseaseSuggest(suggestName,suggestType,suggestDescription);
                                            //Intent intent = new Intent(MainActivity.this, MainActivity.class);
                                            //startActivity(intent);
                                        }
                                        else{
                                            Toast.makeText(MainActivity.this,"Hastalık Önerisi Yapılamadı !\nİçerikleri eksiksiz doldurunuz...",Toast.LENGTH_LONG).show();
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                    .setNegativeButton("İptal Et",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
        else if (id == R.id.nav_search) {
            //Search Filling Adapter
            searchList.clear();
            for (int i = 0; i< User.userList.size(); i++){
                if (!User.userList.get(i).getUsername().equals(username)){
                    searchList.add(User.userList.get(i).getUsername()+ " (KULLANICI)");
                }
            }
            for (int i = 0; i< Disease.diseaseList.size(); i++){
                searchList.add(Disease.diseaseList.get(i).getDiseaseName()+ " (HASTALIK)");
            }

            Collections.sort(searchList);

            LayoutInflater li = LayoutInflater.from(context);
            View promptsView = li.inflate(R.layout.search_part, null);
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
            alertDialogBuilder.setView(promptsView);

            //SearchView
            final SearchView searchView = (SearchView) promptsView.findViewById(R.id.searchView);
            final ListView lv_searchResult = (ListView) promptsView.findViewById(R.id.lv_searchResult);
            lv_searchResult.setVisibility(View.GONE);
            final ArrayAdapter<String> searchAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, searchList);
            lv_searchResult.setAdapter(searchAdapter);
            searchView.setQueryHint("Kelime Giriniz...");
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    //Toast.makeText(getBaseContext(), newText, Toast.LENGTH_LONG).show();
                    if(newText.length()!=0){
                        lv_searchResult.setVisibility(View.VISIBLE);
                    }
                    if(newText.length()==0){
                        lv_searchResult.setVisibility(View.GONE);
                    }
                    searchAdapter.getFilter().filter(newText);
                    return false;
                }
            });

            lv_searchResult.setOnItemClickListener (new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    final String  selectedText = (String) lv_searchResult.getItemAtPosition(position);

                    //Sonuc Kullanıcı ise
                    if (selectedText.contains("KULLANICI")){
                        final String resultUserName = selectedText.substring(0,selectedText.indexOf("(")-1);
                        followingUser = resultUserName;
                        for (int i = 0; i< User.userList.size(); i++){
                            if(User.userList.get(i).getUsername().equals(resultUserName)){
                                otherUserEmail = User.userList.get(i).getEmail();
                                otherUserGender = User.userList.get(i).getGender();
                                otherUserRelatedDisease = User.userList.get(i).getRelatedDisease();
                                otherUserProfileStatus=User.userList.get(i).getProfile_status();
                            }
                        }
                        for(int i = 0; i< UserConnection.userConnectionList.size(); i++){
                            if(UserConnection.userConnectionList.get(i).getFollowedUserName().equals(resultUserName)
                                    && UserConnection.userConnectionList.get(i).getFollowingUserName().equals(username)){
                                followStatus=1;
                            }
                        }
                        int totalMessage=0;
                        for(int i = 0; i < msj.messageList.size(); i++){
                            if(msj.messageList.get(i).getSender().equals(resultUserName)
                                    || msj.messageList.get(i).getReceiver().equals(resultUserName)){
                                totalMessage++;
                            }
                        }

                        LayoutInflater li = LayoutInflater.from(context);
                        View promptsView = li.inflate(R.layout.profile_display_part, null);
                        final TextView tv_other_email = (TextView) promptsView.findViewById(R.id.tv_other_profile_email);
                        final TextView tv_other_gender = (TextView) promptsView.findViewById(R.id.tv_other_profile_gender);
                        final TextView tv_other_profile_disease = (TextView) promptsView.findViewById(R.id.tv_other_profile_disease);
                        final TextView tv_message_number = (TextView) promptsView.findViewById(R.id.tv_message_number);
                        final Button bt_follow_status = (Button) promptsView.findViewById(R.id.bt_follow_status);
                        final Button bt_unfollow_status = (Button) promptsView.findViewById(R.id.bt_unfollow_status);
                        final Button bt_messageUser = (Button) promptsView.findViewById(R.id.bt_messageUser);

                        bt_messageUser.setOnClickListener(new View.OnClickListener(){
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                                intent.putExtra("username",username);
                                MainActivity.this.startActivity(intent);
                            }
                        });
                        String positiveButtonText;
                        if (followStatus==1){
                            bt_unfollow_status.setVisibility(View.GONE);
                            positiveButtonText="Takipten Çık";
                        }else {
                            bt_follow_status.setVisibility(View.GONE);
                            positiveButtonText="Takip Et";
                        }

                        if (otherUserProfileStatus.equals("Normal")){
                            tv_other_email.setText(otherUserEmail);
                            tv_other_gender.setText(otherUserGender);
                            tv_other_profile_disease.setText(otherUserRelatedDisease);
                        }else{
                            tv_other_email.setText("*Gizli Profil*");
                            tv_other_gender.setText("*Gizli Profil*");
                            tv_other_profile_disease.setText("*Gizli Profil*");
                        }

                        tv_message_number.setText(totalMessage + " mesaj");

                        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(context);
                        alertDialogBuilder.setView(promptsView);
                        alertDialogBuilder.setTitle("Kullanıcı: "+resultUserName);
                        alertDialogBuilder
                                .setCancelable(false)
                                .setPositiveButton(positiveButtonText,
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                if (followStatus==1){
                                                    findUsersActivity.setCurrentUser(username);
                                                    findUsersActivity.setFollowedUser(resultUserName);
                                                    try {
                                                        findUsersActivity.unfollowUser();
                                                        Toast.makeText(MainActivity.this,"Kullanıcıyı takip etmeyi bıraktınız !",Toast.LENGTH_LONG).show();
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                                else{
                                                    findUsersActivity.setCurrentUser(username);
                                                    findUsersActivity.setFollowedUser(resultUserName);
                                                    try {
                                                        findUsersActivity.followUser();
                                                        Toast.makeText(MainActivity.this,"Başarıyla takip ettiniz !",Toast.LENGTH_LONG).show();
                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }
                                        })
                                .setNegativeButton("Devam Et",
                                        new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog,int id) {
                                                dialog.cancel();
                                            }
                                        });
                        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();
                    }

                    //Sonuç Hastalık İse
                    if(selectedText.contains("HASTALIK")){
                        final String resultDiseaseName = selectedText.substring(0,selectedText.indexOf("(")-1);
                        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                        alertDialog.setTitle(resultDiseaseName+ " Hastalığı");
                        alertDialog.setMessage("Hastalık sayfasına gitmek ister misiniz?");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Git",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent diseaseActivity = new Intent(view.getContext(),DiseaseActivity.class);
                                        diseaseActivity.putExtra("diseaseName",resultDiseaseName);
                                        startActivity(diseaseActivity);
                                    }
                                });
                        alertDialog.show();
                    }
                }
            });
            alertDialogBuilder
                    .setCancelable(false)
                    .setNegativeButton("Kapat",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,int id) {
                                    dialog.cancel();
                                }
                            });
            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        } else if (id == R.id.nav_send) {
            Intent messageIntent = new Intent(MainActivity.this, MessageActivity.class);
            messageIntent.putExtra("username",username);
            MainActivity.this.startActivity(messageIntent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public class GeocoderHandler extends android.os.Handler {

        @Override
        public void handleMessage(android.os.Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }fullAdress=locationAddress;
            Toast.makeText(getApplicationContext(), "Konumunuz;\n" + fullAdress, Toast.LENGTH_LONG).show();
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimelineFragment(), "Zaman Tüneli");
        adapter.addFragment(new ForumFragment(), "Forum");
        viewPager.setAdapter(adapter);
    }

   class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }

        }

    public void addDiseaseSuggest(final String suggestName, final String suggestType, final String suggestDescription) throws JSONException {
        final Date date = new Date();
        final String dtStart = sdf.format(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            suggestDate = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, dsrp.SUGGEST_CREATE_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this,"Başarıyla hastalık önerisi yapıldı !",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            error.printStackTrace();
                        }
                    }
                }){

            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(dsrp.KEY_SUGGEST_NAME, suggestName);
                params.put(dsrp.KEY_SUGGEST_TYPE, suggestType);
                params.put(dsrp.KEY_SUGGEST_DESCRIPTION, suggestDescription);
                params.put(dsrp.KEY_SUGGEST_OWNER, username);
                params.put(dsrp.KEY_SUGGEST_DATE, dtStart);
                return params;
            }
        };

        DiseaseSuggestion ds = new DiseaseSuggestion(DiseaseSuggestion.suggestionList.size()+1,suggestName,suggestType,suggestDescription,username,dtStart,0);
        DiseaseSuggestion.suggestionList.add(ds);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
