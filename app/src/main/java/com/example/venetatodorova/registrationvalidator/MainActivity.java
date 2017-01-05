package com.example.venetatodorova.registrationvalidator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText firstNameEditText;
    private EditText lastNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;

    private TextView usernameError;
    private TextView firstNameError;
    private TextView lastNameError;
    private TextView emailError;
    private TextView passwordError;
    private TextView confirmPasswordError;

    private String usernameErrMsg;
    private String firstNameErrMsg;
    private String lastNameErrMsg;
    private String emailErrMsg;
    private String passwordErrMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void validate(View view) {
        String username = usernameEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String confirmPassword = confirmPasswordEditText.getText().toString();

        if (!validUsername(username)) {
            usernameError.setText(usernameErrMsg);
        } else {
            usernameError.setText(R.string.empty_msg);
        }
        if (!validFirstName(firstName)) {
            firstNameError.setText(firstNameErrMsg);
        } else {
            firstNameError.setText(R.string.empty_msg);
        }
        if (!validLastName(lastName)) {
            lastNameError.setText(lastNameErrMsg);
        } else {
            lastNameError.setText(R.string.empty_msg);
        }
        if (!validEmail(email)) {
            emailError.setText(emailErrMsg);
        } else {
            emailError.setText(R.string.empty_msg);
        }
        if (password.equals(confirmPassword)) {
            confirmPasswordError.setText(R.string.empty_msg);
            if (!validPassword(password)) {
                passwordError.setText(passwordErrMsg);
            } else {
                passwordError.setText(R.string.empty_msg);
            }
        } else {
            confirmPasswordError.setText(R.string.confirm_password_error);
        }
    }

    private boolean validUsername(String username) {
        boolean isValid = true;
        usernameErrMsg = "";
        if (username.length() < 3 || username.length() > 30){
            usernameErrMsg = usernameErrMsg + (getString(R.string.username_length_err));
            isValid = false;
        }

        if (!username.matches(getString(R.string.alphanumeric_regex))) {
            usernameErrMsg = usernameErrMsg + (getString(R.string.username_alphanum_err));
            isValid = false;
        }
        return isValid;
    }

    private boolean validFirstName(String name) {
        boolean isValid = true;
        firstNameErrMsg = "";
        if (name.length() < 3 || name.length() > 30) {
            firstNameErrMsg = firstNameErrMsg + (getString(R.string.name_length_err));
            isValid = false;
        }
        if (!name.matches(getString(R.string.alpha_regex))) {
            firstNameErrMsg = firstNameErrMsg + (getString(R.string.name_letters_err));
            isValid = false;
        }
        return isValid;
    }

    private boolean validLastName(String name) {
        boolean isValid = true;
        lastNameErrMsg = "";
        if (name.length() < 3|| name.length() > 30) {
            lastNameErrMsg = lastNameErrMsg + (getString(R.string.name_length_err));
            isValid = false;
        }
        if (!name.matches(getString(R.string.alpha_regex))) {
            lastNameErrMsg = lastNameErrMsg + (getString(R.string.name_letters_err));
            isValid = false;
        }
        return isValid;
    }

    private boolean validEmail(String email) {
        emailErrMsg = "";
        if (!email.contains("@") || email.indexOf("@") <= 1 || email.indexOf("@") == email.length() - 1
                || !email.contains(".") || email.lastIndexOf(".") < email.indexOf("@")) {
            emailErrMsg = emailErrMsg + (getString(R.string.invalid_email));
            return false;
        }
        return true;
    }

    private boolean validPassword(String password) {
        boolean isValid = true;
        passwordErrMsg = "";
        if (password.length() < 6 || password.length() > 30) {
            passwordErrMsg = passwordErrMsg + (getString(R.string.password_length_err));
            isValid = false;
        }
        if (password.equals(password.toLowerCase())) {
            passwordErrMsg = passwordErrMsg + (getString(R.string.password_capitalLetter_err));
            isValid = false;
        }
        if (!password.matches(getString(R.string.containsNumerical_regex))) {
            passwordErrMsg = passwordErrMsg + (getString(R.string.password_number_err));
            isValid = false;
        }
        if (!password.matches(getString(R.string.nonAlphanumerical_regex))) {
            passwordErrMsg = passwordErrMsg + (getString(R.string.password_nonAlphanumeric_err));
            isValid = false;
        }
        return isValid;
    }


    private void init() {
        usernameEditText = (EditText) findViewById(R.id.username_editText);
        firstNameEditText = (EditText) findViewById(R.id.firstName_editText);
        lastNameEditText = (EditText) findViewById(R.id.lastName_editText);
        emailEditText = (EditText) findViewById(R.id.email_editText);
        passwordEditText = (EditText) findViewById(R.id.password_editText);
        confirmPasswordEditText = (EditText) findViewById(R.id.confirmPassword_editText);

        usernameError = (TextView) findViewById(R.id.usernameError);
        firstNameError = (TextView) findViewById(R.id.firstNameError);
        lastNameError = (TextView) findViewById(R.id.lastNameError);
        emailError = (TextView) findViewById(R.id.emailError);
        passwordError = (TextView) findViewById(R.id.passwordError);
        confirmPasswordError = (TextView) findViewById(R.id.confirmPasswordError);
    }
}
