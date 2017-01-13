package com.example.venetatodorova.registrationvalidator;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class FindActivity extends AppCompatActivity {

    EditText username;
    EditText firstName;
    EditText lastName;
    EditText email;
    SQLiteDatabase database;
    RegistrationDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);
        init();
    }

    private void init() {
        dbHelper = new RegistrationDbHelper(getApplicationContext());
        database = dbHelper.getWritableDatabase();

        username = (EditText) findViewById(R.id.username);
        firstName = (EditText) findViewById(R.id.firstName);
        lastName = (EditText) findViewById(R.id.lastName);
        email = (EditText) findViewById(R.id.email);
    }

    public void search(View view) {
        setDbCursor();
        Cursor cursor = RegistrationDbHelper.getCursor();
        if(cursor!=null){
            if (!cursor.moveToFirst()) {
                new AlertDialog.Builder(this)
                        .setTitle("Error")
                        .setMessage("No user found!")
                        .show();
            } else {
                Intent intent = new Intent(this, DisplayActivity.class);
                startActivity(intent);
            }
        }

    }

    private void setDbCursor() {
        String[] tableColumns = new String[]{
                RegistrationContract.RegistrationEntry.TABLE_COLUMN_USERNAME,
                RegistrationContract.RegistrationEntry.TABLE_COLUMN_FIRSTNAME,
                RegistrationContract.RegistrationEntry.TABLE_COLUMN_LASTNAME,
                RegistrationContract.RegistrationEntry.TABLE_COLUMN_EMAIL};

        StringBuilder whereClause = new StringBuilder();
        List<String> args = new ArrayList<>();

        if (!username.getText().toString().equals("")) {
            whereClause.append(RegistrationContract.RegistrationEntry.TABLE_COLUMN_USERNAME).append(" = ?");
            args.add(username.getText().toString());
        }

        if (!firstName.getText().toString().equals("")) {
            if (args.size() > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(RegistrationContract.RegistrationEntry.TABLE_COLUMN_FIRSTNAME).append(" = ?");
            args.add(firstName.getText().toString());
        }
        if (!lastName.getText().toString().equals("")) {
            if (args.size() > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(RegistrationContract.RegistrationEntry.TABLE_COLUMN_LASTNAME).append(" = ?");
            args.add(lastName.getText().toString());
        }
        if (!email.getText().toString().equals("")) {
            if (args.size() > 0) {
                whereClause.append(" AND ");
            }
            whereClause.append(RegistrationContract.RegistrationEntry.TABLE_COLUMN_EMAIL).append(" = ?");
            args.add(email.getText().toString());
        }

        String[] whereArgs = new String[args.size()];
        args.toArray(whereArgs);

        if(args.size() == 0) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Enter user information!")
                    .show();
            dbHelper.setCursor(null);
        } else {
            Cursor cursor = database.query(RegistrationContract.RegistrationEntry.TABLE_NAME, tableColumns, whereClause.toString(), whereArgs, null, null, null);
            dbHelper.setCursor(cursor);
        }
    }

}
