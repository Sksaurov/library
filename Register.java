package com.example.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText regEmail, regPassword;
            private TextView regOtp;
    private Button reg;
    private TextView login;
    private String generatedOtp;


    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }


    private boolean isValidPassword(String password) {

        Pattern pattern = Pattern.compile("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@#$%^&+=]).{6,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        auth = FirebaseAuth.getInstance();
        regEmail = findViewById(R.id.reg_email);
        regPassword = findViewById(R.id.reg_password);
        regOtp = findViewById(R.id.reg_otp);
        reg = findViewById(R.id.reg_btn);
        login = findViewById(R.id.regi);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = regEmail.getText().toString();
                String password = regPassword.getText().toString();

                if (user.isEmpty()) {
                    regEmail.setError("Email cannot be empty");
                } else if (!isValidEmail(user)) {
                    regEmail.setError("Invalid email format");
                } else if (password.isEmpty()) {
                    regPassword.setError("Password cannot be empty");
                } else if (!isValidPassword(password)) {
                    regPassword.setError("Password must be at least 6 characters long");
                } else {

                    generatedOtp = generateOtp();
                    sendOtpToEmail(user, generatedOtp);
                    Toast.makeText(Register.this, "OTP sent to your email", Toast.LENGTH_SHORT).show();


                    regOtp.setVisibility(View.VISIBLE);
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Navigate to the login activity
                startActivity(new Intent(Register.this, Login.class));
            }
        });
    }


    private String generateOtp() {
        Random random = new Random();
        int otp = random.nextInt(9000) + 1000; // Generate a 4-digit OTP
        return String.valueOf(otp);
    }


    private void sendOtpToEmail(String email, String otp) {

        auth.createUserWithEmailAndPassword(email, "dummyPassword")
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            sendVerificationEmail();
                        } else {

                            Toast.makeText(Register.this, "User creation failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void sendVerificationEmail() {
        FirebaseUser user = auth.getCurrentUser();

        if (user != null) {
            user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "Verification email sent to " + user.getEmail(), Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(Register.this, Login.class));
                        finish();
                    } else {
                        Toast.makeText(Register.this, "Failed to send verification email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }


    private void createUserAccount() {
        String user = regEmail.getText().toString();
        String password = regPassword.getText().toString();

        auth.createUserWithEmailAndPassword(user, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(Register.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Register.this, Login.class));
                } else {
                    Toast.makeText(Register.this, "Sign up failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
