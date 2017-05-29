package com.patho.messenger.controller;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by eren on 22.03.2017.
 */

public class LoginController extends AppCompatActivity{

    private Context context;
    public LoginController(Context context){
        this.context=context;
    }


    public void showInternetSettingsAlert() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle("Internet Sorunu");
        alertDialog.setMessage("Telefonunuzda aktif bir internet bağlantısı yok !\nInternet ayarlarına gitmek ister misiniz?");
        alertDialog.setPositiveButton("Ayarlar", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                context.startActivity(intent);
            }
        });
        alertDialog.setNegativeButton("İptal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public void showWrongUsernamePaswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Giriş Başarısız");
                builder.setMessage("Kullanıcı adınızı ve şifrenizi doğru girdiğinizden emin olunuz !")
                                    .setNegativeButton("Tekrar Dene", null)
                                    .create()
                                    .show();

    }
}

