package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DashboardActivity extends AppCompatActivity {

    private TextView tvWelcome, tvEmail;
    private Button btnLogout;
    private ApiClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        apiClient = new ApiClient(this);

        tvWelcome = findViewById(R.id.tvWelcome);
        tvEmail = findViewById(R.id.tvEmail);
        btnLogout = findViewById(R.id.btnLogout);

        // Get user details from session
        UserModel user = apiClient.getUserDetails();
        if (user != null) {
            tvWelcome.setText("Welcome, " + user.getUsername() + "!");
            tvEmail.setText(user.getEmail());
        }

        btnLogout.setOnClickListener(v -> {
            apiClient.logout();
            startActivity(new Intent(DashboardActivity.this, MainActivity.class));
            finish();
        });
    }
}
