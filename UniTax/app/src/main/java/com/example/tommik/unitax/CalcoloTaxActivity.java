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

        Spinner annoSpinner = (Spinner) findViewById(R.id.anno);
        List<String> anno = new ArrayList<String>();
        anno.add("1");
        anno.add("2");
        anno.add("3");
        anno.add("4");
        anno.add("5");
        anno.add("6");

        ArrayAdapter<String> annoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, anno);
        annoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        annoSpinner.setAdapter(annoAdapter);


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

/*public class CalcoloTaxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcolo_tax);

        final Spinner tipoSpinner = (Spinner) findViewById(R.id.tipologiacorso);
        final List<String> tipo = new ArrayList<String>();
        tipo.add("Laurea");
        tipo.add("Laurea - Studente Part-Time");
        tipo.add("Laurea magistrale / specialistica");
        tipo.add("Laurea magistrale / specialistica - Studente Part-Time");


        ArrayAdapter<String> tipoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, tipo);
        tipoAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        final Spinner annoSpinner = (Spinner) findViewById(R.id.anno);
        final List<String> anno = new ArrayList<String>();

        tipoSpinner.setAdapter(tipoAdapter);
        tipoSpinner.getOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int val=tipoSpinner.getSelectedItemPosition();
                if(val==0||val==2){
                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4 e oltre");
                }
                else{

                    anno.add("1");
                    anno.add("2");
                    anno.add("3");
                    anno.add("4");
                    anno.add("5");
                    anno.add("6");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




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
*/

/*public class CalcoloTaxActivity extends AppCompatActivity {

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

        Spinner annoSpinner = (Spinner) findViewById(R.id.anno);

        final ArrayAdapter<String> annoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item);
        annoSpinner.setAdapter(annoAdapter);
        final ArrayList<String> anno0=new ArrayList<String>();
        anno0.add("1");
        anno0.add("2");
        anno0.add("3");
        anno0.add("oltre 4");
        final ArrayList<String> anno1=new ArrayList<String>();
        anno1.add("1");
        anno1.add("2");
        anno1.add("3");
        anno1.add("4");
        anno1.add("5");
        anno1.add("6");

        tipoSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                annoAdapter.clear();
                if(position==0||position==2)
                    annoAdapter.addAll(anno0);
                else
                    annoAdapter.addAll(anno1);
            }
        });

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
/*

/*public class CalcoloTaxActivity extends AppCompatActivity {

    private AnnoFreq anno=new AnnoFreq();
    private  List<String> cittadinanza = new ArrayList<String>();
    private Spinner cittSpinner = (Spinner) findViewById(R.id.cittadinanza);
    private Spinner tipoSpinner = (Spinner) findViewById(R.id.tipologiacorso);
    private Spinner annoSpinner = (Spinner) findViewById(R.id.anno);
    private ArrayAdapter<String> cittAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, cittadinanza);
    private  ArrayAdapter<String> tipoAdapter=new ArrayAdapter<String>(this, R.layout.spinner_item);
    private ArrayAdapter<String> annoAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calcolo_tax);

        tipoAdapter.addAll(anno.getTipo());
        tipoSpinner.setAdapter(tipoAdapter);
        annoSpinner.setAdapter(annoAdapter);
        tipoSpinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String s= String.valueOf(tipoSpinner.getSelectedItem());
                updateAnno(s);
            }
        });

        cittadinanza.add("Italiana, UE");
        cittadinanza.add("extra UE con domicilio fiscale in Italia");
        cittadinanza.add("extra UE");


        cittAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);

        cittSpinner.setAdapter(cittAdapter);
    }

    private void updateAnno(String tipo)
    {
        ArrayList<String> l=(ArrayList<String>) anno.getAnno(tipo);
        annoAdapter.clear();
        annoAdapter.addAll(l);
    }

}

*/



/*public class CalcoloTaxActivity extends AppCompatActivity {

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
*/