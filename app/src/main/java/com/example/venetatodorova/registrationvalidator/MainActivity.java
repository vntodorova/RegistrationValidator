package com.example.venetatodorova.registrationvalidator;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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
    User user;
    Button registerButton;

    RegistrationDbHelper dbHelper;
    SQLiteDatabase database;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        registrationValidator = new RegistrationValidator(this);
        dbHelper = new RegistrationDbHelper(getApplicationContext());
        database = dbHelper.getWritableDatabase();
        values = new ContentValues();

        usernameEdit = (EditText) findViewById(R.id.username);
        firstNameEdit = (EditText) findViewById(R.id.firstName);
        lastNameEdit = (EditText) findViewById(R.id.lastName);
        emailEdit = (EditText) findViewById(R.id.email);
        passwordEdit = (EditText) findViewById(R.id.password);
        registerButton = (Button) findViewById(R.id.registrationButton);
    }

    public void register(View view) {
        if (registrationValidator.isRegistrationValid()) {
            user = new User();
            user.setUsername(usernameEdit.getText().toString());
            user.setFirstName(firstNameEdit.getText().toString());
            user.setLastName(lastNameEdit.getText().toString());
            user.setEmail(emailEdit.getText().toString());
            user.setPassword(passwordEdit.getText().toString());

            addToDatabase(user);
            registrationValidator.clearTextFields();
        }
    }

    private void addToDatabase(User user) {
        values.put(RegistrationContract.RegistrationEntry.TABLE_COLUMN_USERNAME, user.getUsername());
        values.put(RegistrationContract.RegistrationEntry.TABLE_COLUMN_FIRSTNAME, user.getFirstName());
        values.put(RegistrationContract.RegistrationEntry.TABLE_COLUMN_LASTNAME, user.getLastName());
        values.put(RegistrationContract.RegistrationEntry.TABLE_COLUMN_EMAIL, user.getEmail());
        values.put(RegistrationContract.RegistrationEntry.TABLE_COLUMN_PASSWORD, user.getPassword());

        database.insert(RegistrationContract.RegistrationEntry.TABLE_NAME, null, values);
    }

    public void find(View view) {
        Intent intent = new Intent(this, FindActivity.class);
        startActivity(intent);
    }
}
