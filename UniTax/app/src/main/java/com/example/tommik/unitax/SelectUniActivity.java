package com.example.tommik.unitax;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class SelectUniActivity extends Activity {

    private ListView listView ;
    private int itemPosition;
    private String  itemValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_uni);



        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);

        // Defined Array values to show in ListView
        String[] values = new String[] {
                "UniVE",
                "UniPD",
                "UniVR"
        };

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, values){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);


                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                tv.setTextColor(Color.BLACK);
                tv.setTextSize(22);



                // Generate ListView Item using TextView
                return view;
            }
        };


        // Assign adapter to ListView
        listView.setAdapter(adapter);

        // ListView Item Click Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                // ListView Clicked item index
                itemPosition     = position;

                // ListView Clicked item value
                itemValue    = (String) listView.getItemAtPosition(position);

                Intent intent = new Intent(SelectUniActivity.this, InfoUniActivity.class);
                intent.putExtra("NOME_UNI",itemValue);
                startActivity(intent);

                // Show Alert
                //Toast.makeText(getApplicationContext(), "Hai selezionato: " +itemValue , Toast.LENGTH_LONG).show();

            }

        });
    }

}