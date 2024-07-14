package com.example.library;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetailActivity extends AppCompatActivity {
    TextView title, name, price;
    ImageView img;
    Button order;
    EditText phoneNumberEditText, addressEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = findViewById(R.id.detailTitle);
        name = findViewById(R.id.detailName);
        img = findViewById(R.id.detailImage);
        price = findViewById(R.id.detailPrice);
        order = findViewById(R.id.order);
        phoneNumberEditText = findViewById(R.id.phoneNumberEditText);
        addressEditText = findViewById(R.id.addressEditText);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            name.setText(bundle.getString("Name"));
            title.setText(bundle.getString("Title"));
            price.setText(bundle.getString("Price"));
            Glide.with(this).load(bundle.getString("Image")).into(img);
        }

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String itemName = name.getText().toString();
                String itemTitle = title.getText().toString();
                String itemPrice = price.getText().toString();
                String phoneNumber = phoneNumberEditText.getText().toString();
                String address = addressEditText.getText().toString();


                if (phoneNumber.isEmpty() || address.isEmpty()) {

                    Toast.makeText(DetailActivity.this, "Please enter your phone number and address", Toast.LENGTH_SHORT).show();
                    return;
                }


                saveOrderToFirebase(itemName, itemPrice, phoneNumber, address);


                Intent intent = new Intent(DetailActivity.this, Cart.class);
                intent.putExtra("itemName", itemName);

                intent.putExtra("itemPrice", itemPrice);
                startActivity(intent);
            }
        });
    }

    private void saveOrderToFirebase(String itemName, String itemPrice, String phoneNumber, String address) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ordersRef = database.getReference("orders");

        DatabaseReference orderRef = ordersRef.child(itemName);


        Order order = new Order(itemName, itemPrice, phoneNumber, address);


        orderRef.setValue(order);
    }
}
