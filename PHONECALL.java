package com.nikkay.canedo.merge;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PHONECALL extends AppCompatActivity {

    EditText number;
    Button call, contact;
    private static final int RESULT_PICK_CONTACT = 85500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phonecall);

        number = (EditText) findViewById(R.id.etPhoneNum);
        call = (Button) findViewById(R.id.btnCall);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + number.getText().toString()));
                    startActivity(callIntent);
                } catch (SecurityException e) {
                    Toast.makeText(PHONECALL.this, "Call Rejected", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
