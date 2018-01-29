package com.example.tommik.unitax;

//WORK IN PROGRESS

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class CalcoloTaxActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcolo_tax);

        //dichiaro gli spinner
        final Spinner tipoSpinner = (Spinner) findViewById(R.id.tipologiacorso);
        final Spinner annoSpinner = (Spinner) findViewById(R.id.anno);
        final Spinner cittSpinner = (Spinner) findViewById(R.id.cittadinanza);


        //dichiaro i valori degli spinner
        List<String> tipo = new ArrayList<String>();
        final List<String> anno = new ArrayList<String>();
        List<String> cittadinanza = new ArrayList<String>();


        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, tipo);

        final ArrayAdapter<String> annoAdapter=new ArrayAdapter<String>(this, R.layout.spinner_item,anno);;


        ArrayAdapter<String> cittAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cittadinanza);

        tipo.add("Laurea");
        tipo.add("Laurea - Studente Part-Time");
        tipo.add("Laurea magistrale / specialistica");
        tipo.add("Laurea magistrale / specialistica - Studente Part-Time");

        anno.add("1");
        anno.add("2");
        anno.add("3");
        anno.add("4 e oltre");




        cittadinanza.add("Italiana, UE");
        cittadinanza.add("extra UE con domicilio fiscale in Italia");
        cittadinanza.add("extra UE");

        tipoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tipoSpinner.setAdapter(tipoAdapter);
        annoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        annoSpinner.setAdapter(annoAdapter);
        cittAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cittSpinner.setAdapter(cittAdapter);



       tipoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //int item =tipoSpinner.getSelectedItemPosition();
                if(position==0||position==2){
                    anno.clear();
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4 e oltre");
                }
                else{
                    anno.clear();
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                    anno.add("5");
                    anno.add("6");
                }
                annoAdapter.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });












    }





}

