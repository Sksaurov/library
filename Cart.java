package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cart extends AppCompatActivity {
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        String itemName = getIntent().getStringExtra("itemName");
        String itemPrice = getIntent().getStringExtra("itemPrice");
        TextView itemNameTextView = findViewById(R.id.itemNameTextView);
        TextView itemTitleTextView = findViewById(R.id.itemTitleTextView);
        btn = findViewById(R.id.okay);
        itemNameTextView.setText("Item Name: " + itemName);
        itemTitleTextView.setText("Item Price: " + itemPrice);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Cart.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
