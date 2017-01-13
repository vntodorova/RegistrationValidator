package com.example.venetatodorova.registrationvalidator;

import android.provider.BaseColumns;

class RegistrationContract {

    private RegistrationContract() {
    }

    static class RegistrationEntry implements BaseColumns {
        static final String TABLE_NAME = "user";
        static final String TABLE_COLUMN_USERNAME = "username";
        static final String TABLE_COLUMN_FIRSTNAME = "firstName";
        static final String TABLE_COLUMN_LASTNAME = "lastName";
        static final String TABLE_COLUMN_EMAIL = "email";
        static final String TABLE_COLUMN_PASSWORD = "password";
    }

    static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + RegistrationEntry.TABLE_NAME + " (" +
                    RegistrationEntry._ID + " INTEGER PRIMARY KEY," +
                    RegistrationEntry.TABLE_COLUMN_USERNAME + " TEXT," +
                    RegistrationEntry.TABLE_COLUMN_FIRSTNAME + " TEXT," +
                    RegistrationEntry.TABLE_COLUMN_LASTNAME + " TEXT," +
                    RegistrationEntry.TABLE_COLUMN_EMAIL + " TEXT," +
                    RegistrationEntry.TABLE_COLUMN_PASSWORD + " TEXT)";

    static final String SQL_DELETE_ENTRY =
            "DROP TABLE IF EXISTS " + RegistrationEntry.TABLE_NAME;
}
