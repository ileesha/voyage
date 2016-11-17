package com.example.mahe.voyage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Main5Activity extends AppCompatActivity {


    Spinner sfrom,dto;
    EditText edFrom, edTo;
    Button btSearch;
    String source,destination;
    int distance;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        sfrom = (Spinner) findViewById(R.id.txtFrom);
        dto = (Spinner) findViewById(R.id.txtTo);

        adapter = ArrayAdapter.createFromResource(this,R.array.dropdown_names,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sfrom.setAdapter(adapter);
        dto.setAdapter(adapter);

        sfrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                source = sfrom.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        dto.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                destination = dto.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btSearch = (Button) findViewById(R.id.buttonSearch);
    }

    public void Search(View v) {
        Intent mainIntent = new Intent(Main5Activity.this,Main6Activity.class);
        mainIntent.putExtra("source", source);
        mainIntent.putExtra("destination", destination);
        Main5Activity.this.startActivity(mainIntent);
    }

}
