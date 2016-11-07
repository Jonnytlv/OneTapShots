package com.example.jonny.validationform;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class RegisterActivity extends AppCompatActivity {

    private static final int REQUEST_LOGIN = 0;

    @InjectView(R.id.input_name) EditText nameText;
    @InjectView(R.id.input_email2) EditText emailText;
    @InjectView(R.id.input_password2) EditText passwordText;
    @InjectView(R.id.btn_signup) Button createLogin;
    @InjectView(R.id.link_login) TextView loginLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.btn_signup);
        setSupportActionBar(toolbar);

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(RegisterActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        createLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                validate();
                if (validate()){
                    Toast.makeText(getApplicationContext(),"Profile made", Toast.LENGTH_LONG).show();
                }
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the Login activity
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivityForResult(intent, REQUEST_LOGIN);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = nameText.getText().toString();
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(RegisterActivity.this);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (name.isEmpty()){
            nameText.setError("Enter a valid name");
            valid = false;
        }
        else {
            editor.putString ("nameVal", nameText.getText().toString());
            editor.commit();
            nameText.setError(null);
        }

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            editor.putString ("emailVal", emailText.getText().toString());
            editor.commit();
            emailText.setError(null);
        }

        if (password.isEmpty()) {
            passwordText.setError("Incorrect Password/Nothing typed in");
            valid = false;
        }
        else if (password.length() <= 1){
            passwordText.setError("Password not long enough");
            valid = false;
        }
        else if (password.length() > 10){
            passwordText.setError("Password is too long");
            valid = false;
        }
        else {
            editor.putString ("passVal", passwordText.getText().toString());
            editor.commit();
            passwordText.setError(null);
        }
        return valid;
    }

}
