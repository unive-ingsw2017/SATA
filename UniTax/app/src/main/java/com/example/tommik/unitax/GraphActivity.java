package com.example.tommik.unitax;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.unive.dais.cevid.datadroid.lib.parser.CsvRowParser;

public class GraphActivity extends AppCompatActivity{

    private static final String TAG = "GraphActivity";
    PieChart chart;
    String label;
    List<PieEntry> pieEntries;
    List<PieEntry> otherEntries;
    List<String> graph_values;
    List<String> details_list;
    private ListView listView ;
    private int itemPosition=-1;
    private String itemValue;


    private  static final int[] palette = {Color.rgb(0, 153, 0),Color.rgb(255, 217, 0),
            Color.rgb(51, 102, 255),Color.rgb(204, 0, 0),Color.rgb(0,153,0),
            Color.rgb(255,153,0),Color.rgb(51,204,253),Color.rgb(204,0,255)};

    private static final int[] dettagli_costi = {R.raw.costi_gestione_corrente1719, R.raw.costi_personale1719};
    private static final int[] dettagli_proventi = {R.raw.proventi_propri1719, R.raw.proventi_contributi1719};

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //crea la activity e prende il documento
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        int message = (int) intent.getExtras().get("proventiCosti");

        label = message == R.raw.proventi_totali1719 ? "Proventi Totali" : "Costi Totali";

        setupChart(message);
    }

    private void setupChart(int message){

        int other_tot = 0, tot = 0;
        otherEntries = new ArrayList<>();
        pieEntries = new ArrayList<>();
        graph_values = new ArrayList<>();
        details_list = new ArrayList<>();

        //creazione del parser
        CsvRowParser totale = new CsvRowParser(new InputStreamReader(
                getResources().openRawResource(message)), true, ",");

        //Label del grafico
        TextView graph_label = (TextView) findViewById(R.id.graph_label);
        graph_label.setText(label);

        //creazione della Lista di righe, ogni riga è una entry della tabella
        List<CsvRowParser.Row> tabella_totale = null;
        try {
            tabella_totale = totale.getAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //creazione lista per il grafico
        for (CsvRowParser.Row entry : tabella_totale){
            String descrizione = entry.get(0);
            int importo = Integer.parseInt(entry.get(1).replace(".",""));
            graph_values.add(entry.get(1));

            if(importo < 5000000) {
                other_tot+=importo;
                otherEntries.add(new PieEntry(importo,descrizione.toUpperCase()));
            }
            else{

                pieEntries.add(new PieEntry(importo,descrizione.toUpperCase()));
            }

            tot+=importo;
        }

        pieEntries.add(new PieEntry(other_tot,"ALTRO"));
        details_list.add("Altro");

        //creazione grafico
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        PieData data = new PieData(dataSet);
        chart = (PieChart) findViewById(R.id.tot_chart);
        dataSet.setColors(palette);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setWordWrapEnabled(true);
        chart.setEntryLabelTextSize(8);
        chart.setEntryLabelColor(Color.rgb(0,0,0));


        listView = (ListView) findViewById(R.id.details_list);

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, details_list){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);


                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);


                tv.setTextColor(Color.BLACK);
                tv.setTextSize(14);



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

                // Show Alert
                //Toast.makeText(getApplicationContext(), "Hai selezionato: " +itemValue , Toast.LENGTH_LONG).show();

            }

        });
        adapter.notifyDataSetChanged();


        chart.animateY(1500);
    }


}


