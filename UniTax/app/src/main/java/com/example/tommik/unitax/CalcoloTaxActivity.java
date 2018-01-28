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

        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, tipo);
        tipoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        tipoSpinner.setAdapter(tipoAdapter);

        Spinner cittSpinner = (Spinner) findViewById(R.id.cittadinanza);
        List<String> cittadinanza = new ArrayList<String>();
        cittadinanza.add("Italiana, UE");
        cittadinanza.add("extra UE con domicilio fiscale in Italia");
        cittadinanza.add("extra UE");

        ArrayAdapter<String> cittAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cittadinanza);
        cittAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        cittSpinner.setAdapter(cittAdapter);
    }


}
