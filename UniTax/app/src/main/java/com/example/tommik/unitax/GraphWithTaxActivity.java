package com.example.tommik.unitax;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class GraphWithTaxActivity extends AppCompatActivity {

    private static final String TAG = "GraphWithTaxActivity";

    private PieChart chart;
    private Float totale_assoluto;
    private Float tasse_assolute;
    private Float altro_totale;
    private PieData altro_data;

    //variabili per il listListener
    ListView listView;

    private  static final int[] palette = {Color.rgb(0, 153, 0),Color.rgb(255, 217, 0),
            Color.rgb(51, 102, 255),Color.rgb(204, 0, 0),Color.rgb(0, 204, 153),
            Color.rgb(255,153,0),Color.rgb(51,204,253),Color.rgb(204,0,255)};

    private static final int[] COSTI = {R.raw.costi_totali1719,
            R.raw.costi_gestione_corrente1719, R.raw.costi_personale1719};

    private static final String[] DETAIL_COST_LIST = {"COSTI TOTALI","COSTI GESTIONE CORRENTE","COSTI PERSONALE","ALTRO"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Intent intent = getIntent();
        String temp_tax = intent.getExtras().get("tax_val").toString();

        tasse_assolute = Float.parseFloat(temp_tax);


        totale_assoluto = sum(COSTI[0]);
        creaPieData(COSTI[0]);
        setListListener(DETAIL_COST_LIST);
    }




    /*dato un resource file e i totali delle spese e quelli relativi delle tase,
    * crea i PieData che verranno caricati nel grafico*/
    public void creaPieDataApp(int resource, Float totale, Float tasse){
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

        List<PieEntry> altroEntries = new ArrayList<>();
        altro_totale = 0f;

        for (CsvRowParser.Row entry : rows){

            String descrizione = entry.get(0);
            Float importo = (Float.parseFloat(entry.get(1).replace(".","")));
            Log.d(TAG,Float.toString(importo));
            Log.d(TAG, Float.toString(0.05f*totale));


            if(importo < 0.05*totale){
                altroEntries.add(new PieEntry((importo/totale)*tasse,descrizione.toUpperCase()));
                altro_totale += (importo/totale)*tasse;
            }
            else
                pieEntries.add(new PieEntry((importo/totale)*tasse,descrizione.toUpperCase()));
        }
        if(altroEntries.size() > 1){
            pieEntries.add(new PieEntry(altro_totale, "ALTRO"));
            PieDataSet dataSet = new PieDataSet(altroEntries,"");
            dataSet.setColors(palette);
            altro_data = new PieData(dataSet);
        }
        else if(pieEntries.size() == 1){
            pieEntries.add(altroEntries.get(0));
        }

        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(palette);

        setupChart(new PieData(dataSet));
    }





    //calcola i totali RELATIVI alle tasse e crea i piedata con la funzione creaPieDataApp();
    public void creaPieData(int resource){

        //calcolo il totale della tabella attuale
        Float totale_relativo = sum(resource);
        Float tasse_relative = (totale_relativo/totale_assoluto)*tasse_assolute;

        //assegnamento testo label
        TextView label_view = (TextView) findViewById(R.id.graph_label);
        label_view.setText("Distribuzione Tasse");

        creaPieDataApp(resource,totale_relativo,tasse_relative);

    }




    //Dato un oggetto di tipo PieData ne crea il grafico
    private void setupChart(PieData data){

        chart = (PieChart) findViewById(R.id.chart);

        if(chart != null)
            chart.clear();

        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setWordWrapEnabled(true);
        chart.setDrawEntryLabels(false);
        chart.setEntryLabelColor(Color.rgb(0,0,0));
        chart.setHoleRadius(40);
        chart.animateY(1500);
        chart.invalidate();
    }




    //somma gli elementi di una colonna di una resource
    public Float sum(int resource){
        //creazione del parser
        Float totale = new Float(0);
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

        //creazione lista per il grafico
        for (CsvRowParser.Row entry : rows){
            totale += Float.parseFloat(entry.get(1).replace(".",""));
        }

        return totale;
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

                if(position == 3 && altro_data != null)
                    setupChart(altro_data);
                else
                    creaPieData(COSTI[position]);
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
                Intent intent = new Intent(GraphWithTaxActivity.this, IntroActivity.class);
                startActivity(intent);
                break;
            case R.id.MENU_2:
                Intent intent2 = new Intent(GraphWithTaxActivity.this, AboutUsActivity.class);
                startActivity(intent2);
                break;
        }
        return false;
    }
}
