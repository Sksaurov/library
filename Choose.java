package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Choose extends AppCompatActivity {
    Button admin;
    Button client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        client = findViewById(R.id.client);
        admin = findViewById(R.id.addd);
        admin.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             startActivity(new Intent(Choose.this, Aadmin.class));
         }
     });
     client.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

             startActivity(new Intent(Choose.this,Login.class));
         }
     });

    }

}