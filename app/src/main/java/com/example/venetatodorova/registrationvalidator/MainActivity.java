package com.example.venetatodorova.registrationvalidator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText usernameEdit;
    private EditText firstNameEdit;
    private EditText lastNameEdit;
    private EditText emailEdit;
    private EditText passwordEdit;

    RegistrationValidator registrationValidator;
    Registration registration;
    Button registerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        registrationValidator = new RegistrationValidator(this);

        usernameEdit = (EditText) findViewById(R.id.username);
        firstNameEdit = (EditText) findViewById(R.id.firstName);
        lastNameEdit = (EditText) findViewById(R.id.lastName);
        emailEdit = (EditText) findViewById(R.id.email);
        passwordEdit = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.registrationButton);
    }

    public void register(View view) {
        if (registrationValidator.isRegistrationValid()) {
            registration = new Registration();
            registration.setUsername(usernameEdit.getText().toString());
            registration.setFirstName(firstNameEdit.getText().toString());
            registration.setLastName(lastNameEdit.getText().toString());
            registration.setEmail(emailEdit.getText().toString());
            registration.setPassword(passwordEdit.getText().toString());
        }
    }
}
