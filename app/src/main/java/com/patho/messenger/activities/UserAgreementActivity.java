package com.patho.messenger.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.patho.messenger.R;

/**
 * Created by eren on 16.02.2017.
 */


public class UserAgreementActivity extends AppCompatActivity  {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_aggrement);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.commit();
        }else{
            Intent loginIntent = new Intent(UserAgreementActivity.this, LoginActivity.class);
            UserAgreementActivity.this.startActivity(loginIntent);
        }

        final CheckBox cb_confirm = (CheckBox) findViewById(R.id.cb_confirm);
        final Button bt_close = (Button) findViewById(R.id.bt_close);
        final Button bt_forward = (Button) findViewById(R.id.bt_forward);


        bt_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pid = android.os.Process.myPid();
                android.os.Process.killProcess(pid);
                System.exit(0);
            }
        });

        bt_forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_confirm.isChecked()) {

                    Intent loginIntent = new Intent(UserAgreementActivity.this, LoginActivity.class);
                    UserAgreementActivity.this.startActivity(loginIntent);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(UserAgreementActivity.this);
                    builder.setMessage("İlerlemek İçin Kullanıcı Sözleşmesini Onaylamanız Gerekmektedir !")
                            .create()
                            .show();
                }
            }
        });


    }

}
