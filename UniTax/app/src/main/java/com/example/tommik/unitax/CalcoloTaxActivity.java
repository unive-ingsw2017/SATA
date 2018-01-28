package com.example.tommik.unitax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CalcoloTaxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcolo_tax);

        Spinner tipoSpinner = (Spinner) findViewById(R.id.tipologiacorso);
        List<String> tipo = new ArrayList<String>();
        tipo.add("Laurea");
        tipo.add("Laurea - Studente Part-Time");
        tipo.add("Laurea magistrale / specialistica");
        tipo.add("Laurea magistrale / specialistica - Studente Part-Time");

        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipo);

        tipoSpinner.setAdapter(tipoAdapter);
    }
}
