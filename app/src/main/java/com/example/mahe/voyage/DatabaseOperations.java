package com.example.mahe.voyage;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.mahe.voyage.TableData;

/**
 * Created by Ileesha on 10/27/2016.
 */
    public class DatabaseOperations  extends SQLiteOpenHelper
    {
        public static final int database_version = 1;
        //public String CREATE_QUERY="CREATE TABLE"+TableLogin.TABLE_NAME+"("+TableLogin.USER_NAME+" TEXT, "+TableLogin.USER_PASS+" TEXT);";
       // public String CREATE_QUERY="CREATE TABLE"+ TableData.TableRegister.TABLE_NAME+"(USER_ID INTEGER PRIMARY KEY AUTOINCREMENT, "+ TableData.TableRegister.USER_FNAME+" TEXT, "+ TableData.TableRegister.USER_LNAME+" TEXT, "+ TableData.TableRegister.USER_PHONE+" INTEGER, "+ TableData.TableRegister.USER_EMAIL+" TEXT, "+ TableData.TableRegister.USER_NAME+" TEXT, "+ TableData.TableRegister.USER_PASS+" TEXT);";

        public DatabaseOperations(Context context)
        {
            super(context, TableData.TableRegister.DATABASE_NAME, null ,database_version);
            Log.d("Database operations", "Database created");
        }

        @Override
        public void onCreate(SQLiteDatabase db0)
        {
            db0.execSQL("create table ");
            Log.d("Database operations", "Table created");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db0, int arg1, int arg2)
        {

        }

        public void putInformation(DatabaseOperations dop, String fname, String lname, String phnum, String email, String uname, String upass)
        {
            SQLiteDatabase sqLiteDatabase = dop.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(TableData.TableRegister.USER_FNAME,fname);
            contentValues.put(TableData.TableRegister.USER_LNAME,lname);
            contentValues.put(TableData.TableRegister.USER_PHONE,phnum);
            contentValues.put(TableData.TableRegister.USER_EMAIL,email);
            contentValues.put(TableData.TableRegister.USER_NAME,uname);
            contentValues.put(TableData.TableRegister.USER_PASS,upass);
            long res = sqLiteDatabase.insert(TableData.TableRegister.TABLE_NAME,null,contentValues);
            Log.d("Database operations", "One row inserted");
        }
    }

