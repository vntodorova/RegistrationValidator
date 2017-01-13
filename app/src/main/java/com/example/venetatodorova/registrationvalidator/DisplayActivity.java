package com.example.venetatodorova.registrationvalidator;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

        Cursor cursor = RegistrationDbHelper.getCursor();
        ListView listView = (ListView) findViewById(R.id.list_view);
        ArrayList<User> list = new ArrayList<>();
        CustomListAdapter adapter = new CustomListAdapter(this, list);
        listView.setAdapter(adapter);

        cursor.moveToFirst();
        do {
            User user = new User();
            user.setUsername(cursor.getString(cursor
                    .getColumnIndex(RegistrationContract.RegistrationEntry.TABLE_COLUMN_USERNAME)));
            user.setFirstName(cursor.getString(cursor
                    .getColumnIndex(RegistrationContract.RegistrationEntry.TABLE_COLUMN_FIRSTNAME)));
            user.setLastName(cursor.getString(cursor
                    .getColumnIndex(RegistrationContract.RegistrationEntry.TABLE_COLUMN_LASTNAME)));
            user.setEmail(cursor.getString(cursor
                    .getColumnIndex(RegistrationContract.RegistrationEntry.TABLE_COLUMN_EMAIL)));
            adapter.add(user);
        } while (cursor.moveToNext());

        cursor.close();
    }
}
