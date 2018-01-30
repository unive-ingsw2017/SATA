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

public class GraphWithTaxActivity extends AppCompatActivity {

    private static final String TAG = "GraphWithTaxActivity";

    private PieChart chart;
    List<PieData> data;
    List<PieEntry> pieEntries;
    List<Float> multiplier;
    Float tax;

    //variabili per il listListener
    ListView listView;
    private int itemPosition;
    private String  itemValue;

    private  static final int[] palette = {Color.rgb(0, 153, 0),Color.rgb(255, 217, 0),
            Color.rgb(51, 102, 255),Color.rgb(204, 0, 0),Color.rgb(0,153,0),
            Color.rgb(255,153,0),Color.rgb(51,204,253),Color.rgb(204,0,255)};

    private static final int[] COSTI = {R.raw.costi_totali1719,
            R.raw.costi_gestione_corrente1719, R.raw.costi_personale1719};

    private static final String[] DETAIL_COST_LIST = {"COSTI GESTIONE CORRENTE","COSTI PERSONALE"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        String temp_tax = (String) intent.getExtras().get("tax_val").toString();

        tax = new Float(Float.parseFloat(temp_tax));

        multiplier = new ArrayList<Float>();
        //creo un array con tutti i totali e li rapporto alle tasse
        for(int i=0;i<COSTI.length;i++){
            multiplier.add(calcolaTot(COSTI[i]));
        }

        for(int i = multiplier.size() - 1; i>=0; i--){
            multiplier.set(i, (multiplier.get(i)/ multiplier.get(0))*tax);
        }

        //assegnamento testo label
        TextView label_view = (TextView) findViewById(R.id.graph_label);
        label_view.setText("Distribuzione Tasse");

        data = new ArrayList<>();

        for(int i = 0; i< COSTI.length; i++){
            data.add(setupPieData(COSTI[i], multiplier.get(i)));
        }

        setupChart(data.get(0));
    }



    /*crea i PieData, ogni PieData è un grafico che per essere visualizzato verrà aggiunto
    * al grafico dalla setupchart*/
    public PieData setupPieData(int resource, float standard){
        pieEntries = new ArrayList<>();
        List<String> descrizioni =  new ArrayList<>();
        List<Float> importi = new ArrayList<>();
        float totale = 0;

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

        //creazione lista per il grafico
        for (CsvRowParser.Row entry : rows){

            descrizioni.add(entry.get(0));
            importi.add(Float.parseFloat(entry.get(1).replace(".","")));
            totale += Float.parseFloat(entry.get(1).replace(".",""));
        }

        //converto in valori decimali
        for(int i = 0; i<importi.size();i++){
            float temp = importi.get(i)*standard;
            importi.set(i,temp);
        }

        for(int i = 0; i<importi.size();i++){
            pieEntries.add(new PieEntry(importi.get(i),descrizioni.get(i)));
        }

        //creazione grafico
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(palette);
        PieData data = new PieData(dataSet);

        return data;
    }


    //Dato un oggetto di tipo PieData ne crea il grafico
    private void setupChart(PieData data){

        if(chart != null)
            chart.clear();

        chart = (PieChart) findViewById(R.id.tot_chart);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setWordWrapEnabled(true);
        chart.setDrawEntryLabels(false);
        chart.setEntryLabelColor(Color.rgb(0,0,0));
        chart.setHoleRadius(40);
        chart.animateY(1500);
        setListListener(DETAIL_COST_LIST);
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

                // ListView Clicked item index
                itemPosition     = position;

                // ListView Clicked item value
                itemValue    = (String) listView.getItemAtPosition(position);

                setupChart(data.get(position + 1));

                // Show Alert
                //Toast.makeText(getApplicationContext(), "Hai selezionato: " +itemValue , Toast.LENGTH_LONG).show();

            }

        });
    }

    public Float calcolaTot(int resource){
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
}
