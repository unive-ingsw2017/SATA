package com.example.tommik.unitax;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.concurrent.ExecutionException;

import it.unive.dais.cevid.datadroid.lib.parser.CsvRowParser;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final Handler handler;
        final int TIME_OUT = 4000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream is = getResources().openRawResource(R.raw.costi_personale1719);

        CsvRowParser parser = new CsvRowParser(new InputStreamReader(is), true, ",");
        List<CsvRowParser.Row> rows = null;
        try {
            rows = parser.getAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(rows != null) {

            for (CsvRowParser.Row row : rows) {

                String id =  row.get("costi del personale");
                String nome = row.get("2017");
                Log.d(TAG,nome + ":   " + id);
            }


        }



        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, SelectUniActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
