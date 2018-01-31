package com.example.tommik.unitax;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class CalcoloTaxActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcolo_tax);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //Inizializzo gli elementi del layout
        final TextView tv_merito=(TextView)findViewById(R.id.tv_merito);
        final TextView tv_isee=(TextView)findViewById(R.id.tv_isee);
        final TextView tv_info=(TextView)findViewById(R.id.tv_infotasse);
        final CheckBox merito=(CheckBox) findViewById(R.id.checkMerito);
        final EditText isee=(EditText)findViewById(R.id.edit_ISEE);
        final Button calISEE=(Button)findViewById(R.id.calISEE);
        final Button calcola=(Button)findViewById(R.id.Calcola);
        final Spinner tipoSpinner = (Spinner) findViewById(R.id.tipologiacorso);
        final Spinner annoSpinner = (Spinner) findViewById(R.id.anno);
        final Spinner cittSpinner = (Spinner) findViewById(R.id.cittadinanza);

        //Creo gli array che conterranno i valori degli spinner
        final List<String> tipo = new ArrayList<String>();
        tipo.add("Laurea");
        tipo.add("Laurea - Studente Part-Time");
        tipo.add("Laurea magistrale / specialistica");
        tipo.add("Laurea magistrale / specialistica - Studente Part-Time");
        final List<String> anno = new ArrayList<String>();
        final List<String> cittadinanza = new ArrayList<String>();
        cittadinanza.add("Italiana, UE");
        cittadinanza.add("extra UE con domicilio fiscale in Italia");
        cittadinanza.add("extra UE");

        //Creo adapter per gli spinner
        final ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, tipo);
        final ArrayAdapter<String> annoAdapter=new ArrayAdapter<String>(this, R.layout.spinner_item,anno);;
        final ArrayAdapter<String> cittAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cittadinanza);

        tipoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        tipoSpinner.setAdapter(tipoAdapter);
        annoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        annoSpinner.setAdapter(annoAdapter);
        cittAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        cittSpinner.setAdapter(cittAdapter);

        isee.setTextColor(R.color.black);
        //isee.setText("-10");
        isee.setText("");
        tv_merito.setVisibility(View.INVISIBLE);
        merito.setVisibility(View.INVISIBLE);

        //faccio un override per i metodi on click così quando clicco su un elemento fa quello che voglio

        //Se seleziono un item sul tipo di corso cambia il valore degli anni
        tipoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    anno.clear();
                    anno.add("Seleziona anno");
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                    anno.add("oltre 4");
                }
                if(position==1){
                    anno.clear();
                    anno.add("Seleziona anno");
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                    anno.add("5");
                    anno.add("6");
                }
                if(position==2){
                    anno.clear();
                    anno.add("Seleziona anno");
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("oltre 3");
                }
                if(position==3){
                    anno.clear();
                    anno.add("Seleziona anno");
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                }
                annoAdapter.notifyDataSetChanged();
                annoSpinner.setSelection(0);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        //Se seleziono un valore sullo spinner cambia la checkbox di merito
        annoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              int ctipo=tipoSpinner.getSelectedItemPosition();
                int canno=annoSpinner.getSelectedItemPosition();
                int ccit=position;

                if(ctipo==0 && canno==1 || ctipo==1 && canno==1){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("voto esame di stato da 98 a 100/100 oppure da 58 a 60/60");
                }
                if(ctipo==0 && canno==2){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                // LAUREA
                if(ctipo==0 && canno==3){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 108 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==0 && canno>=4){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }

                //LAUREA PART - TIME
                if(ctipo==1 && canno==2){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 27 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==1 && canno==3){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }

                if(ctipo==1 && canno==4){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 81 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }

                if(ctipo==1 && canno==5){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 108 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==1 && canno>=6){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }

                // MAGISTRALE
                if(ctipo==2 && canno==1 || ctipo==3 && canno==1){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("ha un voto di laurea che consente l'accesso di almeno 108/110 per i corsi di Laurea magistrale di area economica e scientifica, 110/110 per i corsi di Laurea magistrale di area linguistica e umanistica");
                }

                if(ctipo==2 && canno==2){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 29/30 per i corsi di laurea di area economica, 30/30 per i corsi di laurea di area linguistica, scientifica o umanistica" );
                }
                if(ctipo==2 && canno>=3){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }
                //PART-TIME
                if(ctipo==3 && canno==2){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 27 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==3 && canno==3){
                    tv_merito.setVisibility(View.VISIBLE);
                    merito.setVisibility(View.VISIBLE);

                    merito.setText("conseguiti almeno 54 crediti entro il 30/09/2017 con una media ponderata di almeno: 28/30 per i corsi di laurea di area economica, 29/30 per i corsi di laurea di area linguistica o scientifica, 30/30 per i corsi di laurea di area umanistica" );
                }
                if(ctipo==3 && canno>=4){
                    tv_merito.setVisibility(View.INVISIBLE);
                    merito.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Another interface callback
            }
        });

        //Se seleziono extraeuropeo scompare l'inserimento dell'isee e il bottone per calcolarlo
        cittSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 2){
                    isee.setText("");
                    isee.setVisibility(View.INVISIBLE);
                    tv_isee.setVisibility(View.INVISIBLE);
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

        //Se premo sul bottone calcolca isee mi si apre la pagina con il simulatore per il calcolo dell'isee
        calISEE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.pmi.it/impresa/contabilita-e-fisco/approfondimenti/143049/calcolo-isee-simulatore-inps-online.html");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        //Se premo sulla textview mi si apre la pagina con le info
        tv_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri=Uri.parse("http://www.unive.it/pag/7964/");
                startActivity(new Intent(Intent.ACTION_VIEW,uri));
            }
        });

        //una volta inseriti tutti i parametri se premo su calcola ritorno il valore all'Activity precedente
        calcola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                float val_isee;
                int tipo=tipoSpinner.getSelectedItemPosition();
                int citt=cittSpinner.getSelectedItemPosition();
                boolean mer=merito.isChecked();
                float importo=0;
                if(tryParseFloat(isee.getText().toString()))
                    val_isee=Float.parseFloat(isee.getText().toString());
                else
                    val_isee=-10;
                if(val_isee<0) {
                    if (citt != 2)
                        Toast.makeText(CalcoloTaxActivity.this, "Inserisci tutti i parametri", Toast.LENGTH_SHORT).show();
                    else {
                        importo = calcolaExtra(tipo, mer);
                        Toast.makeText(CalcoloTaxActivity.this, "Tasse calcolate: " + importo, Toast.LENGTH_SHORT).show();
                        intent.putExtra("TAX", Float.toString(importo));
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                }
                else {
                    importo = calcola(val_isee, tipo, citt, mer);
                    Toast.makeText(CalcoloTaxActivity.this, "Tasse calcolate: " + importo, Toast.LENGTH_SHORT).show();
                    intent.putExtra("TAX", Float.toString(importo));
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
    }


    boolean tryParseFloat(String value) {
        try {
            Float.parseFloat(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Matematica per calcolare l'importo delle tasse
    public float calcolaExtra(int tipo, boolean merito){
        float importo=1900;
        if(tipo==2||tipo==3)
            importo=2100;
        if(tipo==1 || tipo==3){
            importo=(importo/100)*65;
        }
        if(merito && importo>0)
            if(importo-315<0)
                importo=0;
            else
                importo=importo-315;

        return Math.round(importo);
    }

    public float calcola(float isee, int tipo, int citt,boolean merito){
        float importo=0;
        float VCMax;
        float VCMin;
        float VMaxIsee;
        float VMinIsee;


        if(isee>0&&isee<=1300){
            importo=166+16;
        }

        if(isee>13000&&isee<=25500){
            VMaxIsee=25500;
            VMinIsee=13001;
            VCMin=0;
            VCMax=875;
            importo=VCMax-((VCMax-VCMin)*(VMaxIsee-isee)/(VMaxIsee-VMinIsee));
            if(tipo==1 || tipo==3){
                importo=(importo/100)*65;
            }
            importo=importo+166+16;

        }
        if(isee>25500&&isee<=30000){
            VMaxIsee=30000;
            VMinIsee=25501;
            VCMin=875;
            VCMax=1020;
            importo=VCMax-((VCMax-VCMin)*(VMaxIsee-isee)/(VMaxIsee-VMinIsee));
            if(tipo==1 || tipo==3){
                importo=(importo/100)*65;
            }
            importo=importo+166+16;

        }
        if(isee>30000&&isee<=50000){
            VMaxIsee = 50000;
            VMinIsee = 30001;
            if(tipo==0 || tipo==1) {
                VCMin = 1040;
                VCMax = 1661;
            }
            else{
                VCMin = 1165;
                VCMax = 1878;
            }
            importo = VCMin + ((((VMaxIsee - VMinIsee) - (VMaxIsee - isee)) * (VCMax - VCMin)) / (VMaxIsee - VMinIsee));
            if(tipo==1 || tipo==3){
                importo=(importo/100)*65;
            }
            importo=importo+166+16;
        }

        if(isee>50000){
            importo=1844;
            if(tipo==2 || tipo==3)
                importo=2061;
            if(tipo==1 || tipo==3){
                importo=(((importo-166-16)/100)*65)+166+16;
            }


        }
        if(merito && importo>0)
            if(importo-315<0)
                importo=0;
            else
                importo=importo-315;

        return Math.round(importo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id=item.getItemId();
        switch(id)
        {
            case R.id.MENU_1:
                Intent intent = new Intent(CalcoloTaxActivity.this, IntroActivity.class);
                startActivity(intent);
                break;
            case R.id.MENU_2:

                Intent intent2 = new Intent(CalcoloTaxActivity.this, AboutUsActivity.class);
                startActivity(intent2);
                break;
        }
        return false;
    }


}

