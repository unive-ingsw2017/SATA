package com.example.tommik.unitax;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.unive.dais.cevid.datadroid.lib.parser.CsvRowParser;

public class GraphActivity extends AppCompatActivity {

    private static final String TAG = "GraphActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //crea la activity e prende il documento
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph);

        Intent intent = getIntent();
        int message = (int) intent.getExtras().get("proventiCosti");


        int other_tot = 0;
        List<PieEntry> pieEntries = new ArrayList<>();
        List<PieEntry> otherEntries = new ArrayList<>();

        //creazione del parser
        CsvRowParser totale = new CsvRowParser(new InputStreamReader(
                getResources().openRawResource(message)), true, ",");

        //creazione della tabella
        List<CsvRowParser.Row> tabella_totale = null;
        try {
            tabella_totale = totale.getAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        for (CsvRowParser.Row costo : tabella_totale){
            String descrizione = costo.get(0);
            int importo = Integer.parseInt(costo.get(1).replace(".",""));

            if(importo < 5000000) {
                other_tot+=importo;
                otherEntries.add(new PieEntry(importo,descrizione));
            }
            else{

                pieEntries.add(new PieEntry(importo,descrizione));
            };
        }

        pieEntries.add(new PieEntry(other_tot,"Altro"));

        //creazione grafico
        PieDataSet dataSet = new PieDataSet(pieEntries,totale.getHeader()[0] + " CaFoscari");
        PieData data = new PieData(dataSet);
        PieChart chart = (PieChart) findViewById(R.id.tot_chart);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
        chart.animateY(1500);
    }
}
