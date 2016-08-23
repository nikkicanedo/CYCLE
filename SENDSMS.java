package com.nikkay.canedo.merge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SENDSMS extends AppCompatActivity implements View.OnClickListener {

    Button button;
    EditText phone;
    EditText message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sendsms);

        phone = (EditText)findViewById(R.id.Phone);
        message = (EditText)findViewById(R.id.Message);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        String num = phone.getText().toString();
        String msg = message.getText().toString();

        try {
            SmsManager manager = SmsManager.getDefault();
            manager.sendTextMessage(num, null, msg, null, null);
            Toast.makeText(getApplicationContext(), "Message Sent Successfully", Toast.LENGTH_LONG).show();
        }catch(Exception e) {
            Toast.makeText(getApplicationContext(), "Message Sent UNSUCCESSFUL", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }


    }
}
