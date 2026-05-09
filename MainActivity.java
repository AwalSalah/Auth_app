package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        apiClient = new ApiClient(this);

        // Session check: If not logged in, go to Login
        if (!apiClient.isLoggedIn()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
            return;
        }

        setContentView(R.layout.activity_main);

        TextView tvWelcome = findViewById(R.id.tvWelcome);
        TextView tvEmail = findViewById(R.id.tvEmail);
        Button btnLogout = findViewById(R.id.btnLogout);

        UserModel user = apiClient.getUserDetails();
        tvWelcome.setText("Hello, " + user.getUsername());
        tvEmail.setText(user.getEmail());

        btnLogout.setOnClickListener(v -> {
            apiClient.logout();
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            finish();
        });
    }
}
