package com.example.mahe.voyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class Main6Activity extends AppCompatActivity {

    String src,dest;
    TextView edSource,edDestination,edDistance,edRoute;
    MyDBHelper myDBHelper;
    String placeName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        edSource = (TextView) findViewById(R.id.src);
        edDestination = (TextView) findViewById(R.id.dest);
        edDistance= (TextView) findViewById(R.id.dist);
        edRoute = (TextView) findViewById(R.id.route);

        Intent intent = getIntent();

        src = intent.getStringExtra("source");
        dest = intent.getStringExtra("destination");

        edSource.setText(src);
        edDestination.setText(dest);

        myDBHelper = new MyDBHelper(this);

        int srcID = myDBHelper.searchID(src);
        int destID = myDBHelper.searchID(dest);
        int distance = myDBHelper.getDistance(srcID, destID);

        edDistance.setText(distance+"");

        placeName="";
        for(int j=srcID; j<=destID; j++)
        {
            placeName = placeName + myDBHelper.searchPlaces(j)+"\n";
        }

        edRoute.setText(placeName);
    }

    public void goBack (View v) {
        Intent mainIntent = new Intent(Main6Activity.this,Main5Activity.class);
        Main6Activity.this.startActivity(mainIntent);
    }

    public void gotoMaps(View v)
    {
        //make proper intent
        Intent intent = new Intent();
        intent.putExtra("source",src);
        intent.putExtra("destination", dest);
    }

    public void Explore (View v) {
        Intent mainIntent = new Intent(Main6Activity.this,Main7Activity.class);
        mainIntent.putExtra("p",placeName);
        Log.i("main6activity",placeName);
        Main6Activity.this.startActivity(mainIntent);

    }
}
