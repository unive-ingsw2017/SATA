package com.example.tommik.unitax;

//WORK IN PROGRESS

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CalcoloTaxActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcolo_tax);

        final TextView tv_merito=(TextView)findViewById(R.id.tv_merito);

        final CheckBox merito=(CheckBox) findViewById(R.id.checkMerito);

        final EditText isee=(EditText)findViewById(R.id.edit_ISEE);

        final Button calISEE=(Button)findViewById(R.id.calISEE);



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


        final ArrayAdapter<String> cittAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cittadinanza);

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
                if(position==0){
                    anno.clear();
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                    anno.add("oltre 4");
                }
                if(position==1){
                    anno.clear();
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                    anno.add("5");
                    anno.add("6");
                }
                if(position==2){
                    anno.clear();
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("oltre 3");
                }
                if(position==3){
                    anno.clear();
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                }
                annoAdapter.notifyDataSetChanged();


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });
        annoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              int ctipo=tipoSpinner.getSelectedItemPosition();
                int canno=annoSpinner.getSelectedItemPosition();
                int ccit=position;

                if(ctipo==0 && canno==0 || ctipo==1 && canno==0){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("voto esame di stato da 98 a 100/100 oppure da 58 a 60/60");
                }
                if(ctipo==0 && canno==1){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                // LAUREA
                if(ctipo==0 && canno==2){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 108 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==0 && canno>=3){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }

                //LAUREA PART - TIME
                if(ctipo==1 && canno==1){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 27 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==1 && canno==2){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }

                if(ctipo==1 && canno==3){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 81 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }

                if(ctipo==1 && canno==4){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 108 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==1 && canno>=5){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }

                // MAGISTRALE
                if(ctipo==2 && canno==0 || ctipo==3 && canno==0){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("ha un voto di laurea che consente l'accesso di almeno 108/110 per i corsi di Laurea magistrale di area economica e scientifica, 110/110 per i corsi di Laurea magistrale di area linguistica e umanistica");
                }

                if(ctipo==2 && canno==1){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 29/30 per i corsi di laurea di area economica, 30/30 per i corsi di laurea di area linguistica, scientifica o umanistica" );
                }
                if(ctipo==2 && canno>=2){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }
                //PART-TIME
                if(ctipo==3 && canno==1){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 27 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==1 && canno==2){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==3 && canno>=3){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        cittSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    isee.setVisibility(View.INVISIBLE);
                    calISEE.setVisibility(View.INVISIBLE);
                }
                else{
                    isee.setVisibility(View.VISIBLE);
                    calISEE.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        calISEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.pmi.it/impresa/contabilita-e-fisco/approfondimenti/143049/calcolo-isee-simulatore-inps-online.html");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });











    }





}

