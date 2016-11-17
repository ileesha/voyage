package com.example.mahe.voyage;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main4Activity extends AppCompatActivity {

    EditText fname,lname,phnum,email,uname,upass,confpass;
    Button create;
    Context ctx = this;
    Users users = new Users();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        fname = (EditText) findViewById(R.id.txtFirstName);
        lname = (EditText) findViewById(R.id.txtLastName);
        phnum = (EditText) findViewById(R.id.txtPhone);
        email = (EditText) findViewById(R.id.txtEmail);
        uname = (EditText) findViewById(R.id.txtUser);
        upass = (EditText) findViewById(R.id.txtPass);
        confpass = (EditText) findViewById(R.id.txtConfPass);
        create = (Button) findViewById(R.id.buttonCreateAccount);
    }
    SQLiteDatabase db;
    public void CreateAccount(View v) {
        users.setF_name(fname.getText().toString());
        users.setL_name(lname.getText().toString());
        users.setPhnumb(phnum.getText().toString());
        users.setEmail(email.getText().toString());
        users.setU_name(uname.getText().toString());
        users.setU_pass(upass.getText().toString());
        if((upass.getText().toString()).equals(confpass.getText().toString()))
        {
            MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext());
            myDBHelper.usersInsert(users);
            Toast.makeText(this,"Registration successful",Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(this,Main3Activity.class);
            Main4Activity.this.startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Passwords don't match. Retry.", Toast.LENGTH_SHORT).show();
            confpass.setText(" ");
            upass.setText(" ");
        }
    }
}
