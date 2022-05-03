package com.example.anneliseweek1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import android.content.Intent;
import android.widget.Toast;
import android.util.Patterns;

import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;




public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText your_name;
    private EditText your_email;
    private EditText your_username;
    private TextView birthdayText;
    private int birthYear = 0;
    private int birthMon = 0;
    private int birthDay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        your_name = findViewById(R.id.your_name);
        your_email = findViewById(R.id.your_email);
        your_username = findViewById(R.id.your_username);
        //irthdayText = findViewById(R.id.birthdayText);
    }

    public void onSubmit(View view) {

        String name = your_name.getText().toString();
        String email = your_email.getText().toString();
        String username = your_username.getText().toString();
        String birthday = birthdayText.getText().toString();

        //checks that nothing is null/everything is valid
        if (name.equals("") || email.equals("") || username.equals("") || birthDay == 0 || birthMon == 0 ||birthYear == 0) {

            Toast.makeText(getApplicationContext(), getString(R.string.invalidInfoError), Toast.LENGTH_LONG).show();
            return;
        }

        //checking proper email form
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(getApplicationContext(), getString(R.string.emailError),
                    Toast.LENGTH_LONG).show();
            return;
        }

        //checking age requirement
        LocalDate currentDate = LocalDate.now();
        LocalDate dateOfBirth = LocalDate.of(birthYear, birthMon, birthDay);
        int years = Period.between(dateOfBirth, currentDate).getYears();

        if (years < 18) {
            Toast.makeText(getApplicationContext(), getString(R.string.ageError),
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

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Constants.SELECTED_DOB, birthdayText.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.containsKey(Constants.SELECTED_DOB)) {
            birthdayText.setText(savedInstanceState.getString(Constants.SELECTED_DOB));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        your_name.setText("");
        your_username.setText("");
        your_email.setText("");
        birthdayText.setText("");

        birthYear = 0;
        birthDay = 0;
        birthMon = 0;
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {
        // Do something with the date chosen by the user
        month = month + 1;
        birthYear = year;
        birthMon = month;
        birthDay = day;
        birthdayText.setText(getString(R.string.date, month, day, year));
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

}