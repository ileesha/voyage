package com.example.mahe.voyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main7Activity extends AppCompatActivity {

    String placeName;
    String[] placeNames;
    String[] placeInfo;
    TextView placesInfoDisplay;
    MyDBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        Intent intent = getIntent();
        placeName = intent.getStringExtra("p");
        Log.i("main7activity",placeName);
        placeNames = placeName.split("\n");

        for(String w:placeNames)
            Log.i("main7activity",w);

        placesInfoDisplay = (TextView) findViewById(R.id.tdPlaceDetils);
        helper = new MyDBHelper(getApplicationContext());
        placeInfo = helper.getPlaceInfo(placeNames);

        placeName = "";
        for(String w:placeInfo)
        {
            placeName = placeName+w+"\n\n\n";
        }
        placesInfoDisplay.setMovementMethod(new ScrollingMovementMethod());
        placesInfoDisplay.setText(placeName);
        Log.i("main7activity","setText");
    }

    public void goBack (View v) {
        Intent mainIntent = new Intent(Main7Activity.this,Main6Activity.class);
        Main7Activity.this.startActivity(mainIntent);
    }
}