package com.example.mahe.voyage;

        import android.content.ContentValues;
        import android.content.Intent;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.support.annotation.IdRes;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener{

    Button btProceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();

    }
    public void init(){
        btProceed=(Button)findViewById(R.id.btProceed2);
        btProceed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent mainIntent = new Intent(Main2Activity.this,Main3Activity.class);
        Main2Activity.this.startActivity(mainIntent);
        Main2Activity.this.finish();
    }
}
