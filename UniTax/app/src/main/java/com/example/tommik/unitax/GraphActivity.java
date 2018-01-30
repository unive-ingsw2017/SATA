package com.example.tommik.unitax;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
    String graph_label;
    List<PieEntry> pieEntries;
    List<PieData> cost_data;
    List<PieData> prov_data;


    private  static final int[] palette = {Color.rgb(0, 153, 0),Color.rgb(255, 217, 0),
            Color.rgb(51, 102, 255),Color.rgb(204, 0, 0),Color.rgb(0,153,0),
            Color.rgb(255,153,0),Color.rgb(51,204,253),Color.rgb(204,0,255)};

    private static final int[] DETTAGLI_COSTI = {R.raw.costi_totali1719,
            R.raw.costi_gestione_corrente1719, R.raw.costi_personale1719};
    private static final int[] DETTAGLI_PROVENTI = {R.raw.proventi_totali1719,
            R.raw.proventi_propri1719, R.raw.proventi_contributi1719};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        prov_data = new ArrayList<>();
        cost_data = new ArrayList<>();

        //crea la activity e prende il documento
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        graph_label = (String) intent.getExtras().get("graph_label");

        for(int i = 0; i<DETTAGLI_PROVENTI.length;i++){
            prov_data.add(setupPieData(DETTAGLI_PROVENTI[i]));
        }

        for(int i = 0; i<DETTAGLI_COSTI.length;i++){
            cost_data.add(setupPieData(DETTAGLI_COSTI[i]));
        }

        setupChart(cost_data.get(1));
    }

    private void setupChart(PieData data){

        //Label del grafico
        TextView label_view = (TextView) findViewById(R.id.graph_label);
        label_view.setText(graph_label);

        chart = (PieChart) findViewById(R.id.tot_chart);
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setWordWrapEnabled(true);
        chart.setEntryLabelTextSize(8);
        chart.setEntryLabelColor(Color.rgb(0,0,0));
        chart.animateY(1500);
    }

    /*crea i PieData, ogni PieData deve quindi solo essere aggiunto al chart per essere visualizzato*/

    public PieData setupPieData(int resource){
        pieEntries = new ArrayList<>();

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

            String descrizione = entry.get(0);
            int importo = Integer.parseInt(entry.get(1).replace(".",""));



            pieEntries.add(new PieEntry(importo,descrizione.toUpperCase()));
        }

        //creazione grafico
        PieDataSet dataSet = new PieDataSet(pieEntries,"");
        dataSet.setColors(palette);
        PieData data = new PieData(dataSet);

        return data;
    }
}


