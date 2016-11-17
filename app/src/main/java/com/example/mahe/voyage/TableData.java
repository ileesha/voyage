package com.example.mahe.voyage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

/**
 * Created by Ileesha on 10/26/2016.
 */
public class TableData {

    public TableData()
    {

    }

   /* public static abstract class TableLogin implements BaseColumns
    {
        public static  final String USER_NAME = "user_name";
        public static  final String USER_PASS = "user_pass";
        public static  final String DATABASE_NAME = "user_info";
        public static  final String TABLE_NAME = "user_login";
    }*/
    public static abstract class TableRegister implements BaseColumns
    {
        public static  final String USER_ID = "user_id";
        public static  final String USER_FNAME = "user_fname";
        public static  final String USER_LNAME = "user_lname";
        public static  final String USER_PHONE = "user_phone";
        public static  final String USER_EMAIL = "user_email";
        public static  final String USER_NAME = "user_name";
        public static  final String USER_PASS = "user_pass";
        public static  final String DATABASE_NAME = "user_info";
        public static  final String TABLE_NAME = "user_register";
    }
}
