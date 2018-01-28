package com.example.tommik.unitax;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.unive.dais.cevid.datadroid.lib.parser.CsvRowParser;

public class InfoUniActivity extends AppCompatActivity {
    Button cal;

    private static final String TAG = "InfoUniActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_uni);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getExtras().getString("NOME_UNI");

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.NameUni);
        textView.setText(message);

        cal = (Button) findViewById(R.id.tassescon);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, CalcoloTaxActivity.class);
                startActivity(intent);
            }
        });

        setupPieChart();
    }



    private void setupPieChart() {

        List<PieEntry> pieEntries = new ArrayList<>();
        List<PieEntry> otherEntries = new ArrayList<>();
        int total = 0, otherTotal = 0;
        //prende in input il csv e crea l InputStream
        InputStream is = getResources().openRawResource(R.raw.costi_totali1719);

        //crea il parser per l is, l array per le pienetries per il grafico e una liste di righe
        CsvRowParser parser = new CsvRowParser(new InputStreamReader(is), true, ",");

        List <CsvRowParser.Row> rows = null;
        try {
            rows = parser.getAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        //ogni valore e descrizione vengono inseriti nel grafico
        for (CsvRowParser.Row row : rows) {

            String descrizione = row.get(0);
            int importo = Integer.parseInt(row.get(1).replace(".",""));

            if(importo < 2000000) {
                otherTotal+=importo;
                otherEntries.add(new PieEntry(importo,descrizione));
            }
            else{

                pieEntries.add(new PieEntry(importo,descrizione));
            }

            total+=importo;

        }
        pieEntries.add(new PieEntry(otherTotal,"Altro"));

        //creazione grafico
        PieDataSet dataSet = new PieDataSet(pieEntries,"Costi totali CaFoscari");
        PieData data = new PieData(dataSet);
        PieChart chart = (PieChart) findViewById(R.id.chart);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
        chart.animateY(1500);
    }
}
