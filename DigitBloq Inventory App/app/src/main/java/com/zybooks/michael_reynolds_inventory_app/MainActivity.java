package com.zybooks.michael_reynolds_inventory_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_REQUEST_SEND_SMS = 1;
    private EditText usernameField;
    private EditText passwordField;
    private AuthDatabase authDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameField = findViewById(R.id.editTextUsername);
        passwordField = findViewById(R.id.editTextPassword);
        authDB = new AuthDatabase(MainActivity.this);

        // Check if the app has SMS permission
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            // Request the permission
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.SEND_SMS}, PERMISSION_REQUEST_SEND_SMS);
        } else {
            // Permission is already granted
            sendSMS("(650) 555-1212", "Test SMS");
        }
    }

    public void onLogin(View view){
        if(authDB.validateUser(usernameField.getText().toString(), passwordField.getText().toString())){
            Intent intent = new Intent(this, InventoryActivity.class);
            startActivity(intent);

            Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Invalid Combination", Toast.LENGTH_SHORT).show();
        };
    }

    public void onRegister(View view){
        UserModel regUser = new UserModel(usernameField.getText().toString(), passwordField.getText().toString());

        authDB.addUser(regUser);
        Toast.makeText(MainActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSION_REQUEST_SEND_SMS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted
                String phoneNumber = "(650) 555-1212";
                String message = "Hello, this is a test SMS!";
                sendSMS(phoneNumber, message);
            } else {
                // Permission denied
                Toast.makeText(this, "SMS permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void sendSMS(String phoneNumber, String message) {
        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, message, null, null);
            Toast.makeText(this, "SMS sent successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "SMS sending failed", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}