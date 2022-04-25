package com.example.anneliseweek1;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText your_name;
    private EditText your_email;
    private EditText your_username;
    private EditText your_birthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onSubmit(View view) {

        String name = your_name.getText().toString();
        String email = your_email.getText().toString();
        String username = your_username.getText().toString();
        String birthday = your_birthday.getText().toString();

        Intent intent = new Intent(getApplicationContext(), StartPage.class);
        startActivity(intent);

    }

}