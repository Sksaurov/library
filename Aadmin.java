package com.example.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Aadmin extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText logEmail, logPass;
    private Button logButton;

    private TextView admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aadmin);
        auth = FirebaseAuth.getInstance();
        logEmail = findViewById(R.id.admin_email);
        logPass = findViewById(R.id.admin_password);

        logButton = findViewById(R.id.admin_btn);

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = logEmail.getText().toString().trim();
                String password = logPass.getText().toString().trim();


                if (!email.equals("admin0101@gmail.com") || !password.equals("Admin0101")) {
                    Toast.makeText(Aadmin.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                    return;
                }


                auth.signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                Toast.makeText(Aadmin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(Aadmin.this, AdminChoose.class));
                                finish();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(Aadmin.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}
