package com.patho.messenger.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.patho.messenger.R;
import com.patho.messenger.controller.AppController;
import com.patho.messenger.controller.MessageController;
import com.patho.messenger.dao.MessageRetrieveAndPostRequest;
import com.patho.messenger.model.Message;

import org.json.JSONException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eren on 26.03.2017.
 */

public class MessageActivity extends AppCompatActivity {

    String username, receiverName;
    ArrayList<String> sampleUserlist = new ArrayList<>();
    ArrayList<String> sampleMessageContent= new ArrayList<>();
    MessageRetrieveAndPostRequest mr;
    final Context context = this;
    private static final DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    Date messageDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        Bundle extras = getIntent().getExtras();
        username=extras.getString("username");

        final ListView listView = (ListView) findViewById(R.id.lv_messageUserList);
        final EditText et_historyMessage = (EditText) findViewById(R.id.et_messageHistory);
        final Button bt_answerMessage = (Button) findViewById(R.id.bt_answerMessage);
        bt_answerMessage.setVisibility(View.GONE);
        et_historyMessage.setEnabled(false);

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


        for (int i = 0; i< Message.messageList.size(); i++){
            if (username.equals(Message.messageList.get(i).getReceiver().toString())&&
                    !sampleUserlist.contains(Message.messageList.get(i).getSender().toString())){
                sampleUserlist.add(Message.messageList.get(i).getSender().toString());
            }
            else if(username.equals(Message.messageList.get(i).getSender())&&
                    !sampleUserlist.contains(Message.messageList.get(i).getReceiver().toString())){
                sampleUserlist.add(Message.messageList.get(i).getReceiver().toString());
            }
        }


        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, sampleUserlist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                bt_answerMessage.setVisibility(View.VISIBLE);
                String  itemValue    = (String) listView.getItemAtPosition(position);
                receiverName = itemValue.toString();
                sampleMessageContent = findMessages(itemValue.toString());
                StringBuilder sb = new StringBuilder(itemValue.toString()+" İLE OLAN SOHBETİNİZ  \n");
                sb.append("\n");
                for (int i=0;i<sampleMessageContent.size();i++){
                    sb.append(sampleMessageContent.get(i).toString()+"\n");
                    sb.append("\n");
                }
                et_historyMessage.setText(sb);
            }
        });

        bt_answerMessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater li = LayoutInflater.from(context);
                View promptsView = li.inflate(R.layout.message_part, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView.findViewById(R.id.et_Message);
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Gönder",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        try {
                                            if (userInput.getText().toString().length()!=0){
                                                sendMessage(username,receiverName,userInput.getText().toString());
                                                Intent intent = new Intent(MessageActivity.this, MessageActivity.class);
                                                intent.putExtra("username",username);
                                                startActivity(intent);
                                            }
                                            else{
                                                Toast.makeText(MessageActivity.this,"Mesaj Gönderilemedi !\nMesaj içeriği boş...",Toast.LENGTH_LONG).show();
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
        });
    }

    private ArrayList<String> findMessages(String selecteduser){
        ArrayList<String> messageContent= new ArrayList<>();
        for (int i = 0; i< Message.messageList.size(); i++){
            if (selecteduser.equals(Message.messageList.get(i).getReceiver().toString())){
                messageContent.add(Message.messageList.get(i).getMessageDate().toString()+">>"+username+": " + Message.messageList.get(i).getMessageContent().toString());
            }
            else if(selecteduser.equals(Message.messageList.get(i).getSender())){
                messageContent.add(Message.messageList.get(i).getMessageDate().toString()+"<<"+selecteduser+": " + Message.messageList.get(i).getMessageContent().toString());
            }
        }
        return messageContent;
    }

    private void sendMessage(String sender, String  receiver, String content) throws JSONException {
        final String messageContent = content;
        final String senderName = sender;
        final String receiverName = receiver;
        final Date date = new Date();

        final String dtStart = sdf.format(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            messageDate = format.parse(dtStart);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        StringRequest stringRequest = new StringRequest(Request.Method.POST, mr.MESSAGE_CREATE_REQUEST_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MessageActivity.this,"Başarıyla Mesajınız Yollandı!",Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        if(volleyError.networkResponse != null && volleyError.networkResponse.data != null){
                            VolleyError error = new VolleyError(new String(volleyError.networkResponse.data));
                            volleyError = error;
                        }
                        Log.v("Subject alınan hata",volleyError.toString());
                    }
                }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put(mr.KEY_MESSAGE_SENDER, senderName);
                params.put(mr.KEY_MESSAGE_RECEIVER, receiverName);
                params.put(mr.KEY_MESSAGE_CONTENT, messageContent);
                params.put(mr.KEY_MESSAGE_DATE, dtStart);
                return params;
            }
        };
        Message msj = new Message(Message.messageList.size()+1,senderName,receiverName,messageContent,dtStart);
        Message.messageList.add(msj);
        AppController.getInstance().addToRequestQueue(stringRequest);
    }
}
