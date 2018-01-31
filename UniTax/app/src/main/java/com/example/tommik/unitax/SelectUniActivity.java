package com.example.tommik.unitax;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SelectUniActivity extends AppCompatActivity {

    //Dichiaro gli elementi del layout
    private ListView lv ;
    private int itemPosition;
    private String  itemValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_uni);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        // Assegno la ListView
        lv = (ListView) findViewById(R.id.list);

        // Definisco gli elementi della listview
        String[] values = new String[] {
                "UniVE",
                "UniPD",
                "UniVR"
        };


        //Definisco un nuovo Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, values){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                //Prendo gli item dalla listview
                View view = super.getView(position, convertView, parent);
                // Inizializzo una textview per ogni elemento della listview
                TextView tv = (TextView) view.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLACK);
                tv.setTextSize(22);
                //ritorno la listview generata
                return view;
            }
        };


        //Assegno l'adapter alla listview
        lv.setAdapter(adapter);

        //Setto il listener per la listview
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Indice dell'item selezionato
                itemPosition = position;
                //Valore dell'item selezionato
                itemValue    = (String) lv.getItemAtPosition(position);

                //Passo il valore all'activity successiva
                if(position==0) {
                    Intent intent = new Intent(SelectUniActivity.this, InfoUniActivity.class);
                    intent.putExtra("NOME_UNI", itemValue);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(SelectUniActivity.this, "Dati non ancora disponibili", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.MENU_1:
                Intent intent = new Intent(SelectUniActivity.this, IntroActivity.class);
                startActivity(intent);
                break;
            case R.id.MENU_2:
                Intent intent2 = new Intent(SelectUniActivity.this, AboutUsActivity.class);
                startActivity(intent2);
                break;
        }
        return false;
    }

}