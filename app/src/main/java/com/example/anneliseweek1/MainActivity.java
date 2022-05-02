package com.example.anneliseweek1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.content.Intent;
import android.widget.Toast;
import android.util.Patterns;


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

        //checks that nothing is null/everything is valid
        if (name.equals("") || email.equals("") || username.equals("") || birthday == null) {

            Toast.makeText(getApplicationContext(), getString(R.string.invalidInfoError), Toast.LENGTH_LONG).show();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), getString(R.string.emailError),
                    Toast.LENGTH_LONG).show();
            return;
        }

        Intent intent = new Intent(getApplicationContext(), StartPage.class);
        intent.putExtra(Constants.USERNAME_KEY, name);
        intent.putExtra(Constants.AGE_KEY, birthday);
        startActivity(intent);
        startActivity(intent);

    }

    public void selectBirthday(View view) {
        DialogFragment newFragment = new DateFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public static class DateFragment extends DialogFragment {
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), (MainActivity) getActivity(),
                    year, month, day);
    }

}