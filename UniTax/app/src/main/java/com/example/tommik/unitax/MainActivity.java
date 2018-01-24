package com.example.tommik.unitax;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

<<<<<<< HEAD
import java.io.File;
import java.io.FileNotFoundException;
=======
>>>>>>> master
import java.io.FileReader;
import java.util.List;

import it.unive.dais.cevid.datadroid.lib.parser.CsvRowParser;

<<<<<<< HEAD
;

=======
>>>>>>> master
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final int TIME_OUT = 4000;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

<<<<<<< HEAD
        FileReader file = null;
        try {
            file = new FileReader("be1618.csv");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        CsvRowParser parser = new CsvRowParser(file, true, ";");
=======
        CsvRowParser parser = new CsvRowParser(new FileReader("be1618.csv"), true, ";");
>>>>>>> master
        List<CsvRowParser.Row> rows = parser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR).get();
        for (CsvRowParser.Row row : rows) {
            String id = row.get("ID"), nome = row.get("NAME");
            // fai qualcosa con id e nome
            }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, TutorialActivity.class);
                startActivity(i);
                finish();
            }
        }, TIME_OUT);
    }
}
