package com.example.library;

import android.os.Bundle;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Show extends AppCompatActivity {

    TextView ordersTextView; // Add a TextView to display orders

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        ordersTextView = findViewById(R.id.ordersTextView);

        // Get a reference to the "orders" node in Firebase
        DatabaseReference ordersRef = FirebaseDatabase.getInstance().getReference("orders");

        // Attach a listener to read the data from Firebase
        ordersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                StringBuilder ordersText = new StringBuilder();
                for (DataSnapshot orderSnapshot : dataSnapshot.getChildren()) {
                    // Get the Order object from the snapshot
                    Order order = orderSnapshot.getValue(Order.class);

                    // Format the order details into a string and append it to ordersText
                    String orderLine = "Item Name: " + order.getItemName() +
                            ", Price: " + order.getItemPrice() +
                            ", Phone Number: " + order.getPhoneNumber() +
                            ", Address: " + order.getAddress() + "\n\n"; // Add an extra newline character
                    ordersText.append(orderLine);
                }

                // Set the formatted ordersText to the ordersTextView
                ordersTextView.setText(ordersText.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
            }
        });
    }
}
