package com.example.tommik.unitax;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.util.Log;
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

    private PieChart chart;
    String graph_label;
    PieData altrodata;
    int altroTotale;
    TextView label_view;
    //variabili per il listListener
    ListView listView;
    private int itemPosition;
    private String  itemValue;

    //Costanti che contengono colori, file e nomi dei file
    private  static final int[] palette = {Color.rgb(0, 153, 0),Color.rgb(255, 217, 0),
            Color.rgb(51, 102, 255),Color.rgb(204, 0, 0),Color.rgb(0,153,0),
            Color.rgb(255,153,0),Color.rgb(51,204,253),Color.rgb(204,0,255)};

    private static final int[] COSTI = {R.raw.costi_totali1719,
            R.raw.costi_gestione_corrente1719, R.raw.costi_personale1719};
    private static final int[] PROVENTI = {R.raw.proventi_totali1719,
            R.raw.proventi_propri1719, R.raw.proventi_contributi1719};

    private static final String[] DETAIL_COST_LIST = {"COSTI TOTALI","COSTI GESTIONE CORRENTE","COSTI PERSONALE","ALTRO"};
    private static final String[] DETAIL_PROV_LIST = {"PROVENTI TOTALI","PROVENTI PROPRI","PROVENTI CONTRIBUTI","ALTRO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int[] res_csv;

        //crea la activity e prende il documento
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        graph_label = (String) intent.getExtras().get("graph_label");
        if(graph_label.equals("Proventi")){
            setupPieData(PROVENTI[0],DETAIL_PROV_LIST[0]);
            setListListener(DETAIL_PROV_LIST);
        }
        else{
            setupPieData(COSTI[0],DETAIL_COST_LIST[0]);
            setListListener(DETAIL_COST_LIST);
        }
    }



    //Dato un oggetto di tipo PieData ne crea il grafico
    private void setupChart(PieData data){

        //Label del grafico

        if(chart != null)
            chart.clear();

        chart = (PieChart) findViewById(R.id.chart);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setWordWrapEnabled(true);
        chart.setDrawEntryLabels(false);
        chart.setEntryLabelColor(Color.rgb(0,0,0));
        chart.setHoleRadius(40);
        chart.animateY(1500);
    }



    /*crea i PieData, ogni PieData è un grafico che per essere visualizzato verrà aggiunto
    * al grafico dalla setupchart*/
    public void setupPieData(int resource,String label){
        int totale = 0;
        List<PieEntry> pieEntries = new ArrayList<>();

        //creazione del parser
        CsvRowParser parser = new CsvRowParser(new InputStreamReader(
                getResources().openRawResource(resource)), true, ",");

        //creazione della Lista di righe, ogni riga è una entry della tabella
        List<CsvRowParser.Row> rows = null;
        try {
            rows = parser.getAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (CsvRowParser.Row entry : rows){
            totale += Integer.parseInt(entry.get(1).replace(".",""));
        }

        List<PieEntry> altroEntries = new ArrayList<>();
        altroTotale = 0;

        //creazione lista per il grafico
        for (CsvRowParser.Row entry : rows){

            String descrizione = entry.get(0);
            int importo = Integer.parseInt(entry.get(1).replace(".",""));

            if(importo < 0.05*totale){
                altroEntries.add(new PieEntry(importo,descrizione));
                altroTotale += importo;
            }
            else
                pieEntries.add(new PieEntry(importo,descrizione.toUpperCase()));
        }
        if(altroEntries.size() > 1){
            pieEntries.add(new PieEntry(altroTotale, "ALTRO"));
            PieDataSet dataSet = new PieDataSet(altroEntries,"");
            dataSet.setColors(palette);
            altrodata = new PieData(dataSet);
        }
        else if(pieEntries.size() == 1){
            pieEntries.add(altroEntries.get(0));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(palette);

        label_view = (TextView) findViewById(R.id.graph_label);
        label_view.setText(label);

        setupChart(new PieData(dataSet));
    }



    //crea i listener per la lista dettagli
    public void setListListener(String[] values){
        listView = (ListView) findViewById(R.id.details_list);

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

                Log.d(TAG, Integer.toString(position));

                if(position == 3 && altrodata != null) {
                    label_view.setText("ALTRO");
                    setupChart(altrodata);
                }
                else{
                    if (graph_label.equals("Costi"))
                        setupPieData(COSTI[position], DETAIL_COST_LIST[position]);
                    else {
                        setupPieData(PROVENTI[position], DETAIL_PROV_LIST[position]);
                    }
                }

                // Show Alert
                //Toast.makeText(getApplicationContext(), "Hai selezionato: " +itemValue , Toast.LENGTH_LONG).show();

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
                Intent intent = new Intent(GraphActivity.this, IntroActivity.class);
                startActivity(intent);
                break;
            case R.id.MENU_2:
                Intent intent2 = new Intent(GraphActivity.this, AboutUsActivity.class);
                startActivity(intent2);
                break;
        }
        return false;
    }

}


