package com.example.mahe.voyage;

        import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main3Activity extends AppCompatActivity {
    private MyDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        helper = new MyDBHelper(this);
    }

    public void Login(View v){

        EditText username=(EditText)findViewById(R.id.txtUserName);
        EditText password=(EditText)findViewById(R.id.txtPassword);
        Intent intent=new Intent(this,Main5Activity.class);
        String upass = helper.searchUpass(username.getText().toString());

       if((password.getText().toString().equals(upass)))
        {
            startActivity(intent);
        }
        else
            Toast.makeText(this,"Wrong Username/Password",Toast.LENGTH_SHORT).show();
    }

    public void Signup(View v) {
        Intent mainIntent = new Intent(Main3Activity.this,Main4Activity.class);
        Main3Activity.this.startActivity(mainIntent);
        Main3Activity.this.finish();
    }
}

