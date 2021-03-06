package com.example.tommik.unitax;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InfoUniActivity extends AppCompatActivity {
    Button cal, prov_button, cost_button, view_tax_button;
    EditText edit_tax;

    private static final String TAG = "InfoUniActivity";
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_uni);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        // Dichiaro gli elementi del layout
        cal = (Button) findViewById(R.id.tassescon);
        edit_tax=(EditText)findViewById(R.id.edit_tax);
        prov_button = (Button) findViewById(R.id.provbut);
        cost_button = (Button) findViewById(R.id.costbut);

        // Ricavo il nome dell'università dall'intent passato dalla pagina precedente
        Intent intent = getIntent();
        String message = intent.getExtras().getString("NOME_UNI");


        //Modifico la text view con il nome dell'università
        TextView textView = (TextView) findViewById(R.id.NameUni);
        textView.setText(message);
        edit_tax.setTextColor(Color.BLACK);


        //Imposto i Listener per quando vengono cliccati i vari bottoni

        //Apre l'activity per il calcolo delle tasse e si aspetta un valore come ritorno che verrà messo nell edit text
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, CalcoloTaxActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });

        //Apre la label per il grafico proventi
        prov_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, GraphActivity.class);
                intent.putExtra("graph_label", "Proventi");
                startActivity(intent);
            }
        });

        //Apre la label per il grafico costi
        cost_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, GraphActivity.class);
                intent.putExtra("graph_label","Costi");
                startActivity(intent);
            }
        });

        view_tax_button = (Button) findViewById(R.id.viewinfotax);
        view_tax_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float tax=0;
                if(tryParseFloat(edit_tax.getText().toString()))
                    tax=Float.parseFloat(edit_tax.getText().toString());
                else
                    tax=-10;
                if(tax<0){
                    Toast.makeText(InfoUniActivity.this, "Inserisci tutti i parametri", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(InfoUniActivity.this, GraphWithTaxActivity.class);
                    intent.putExtra("tax_val", edit_tax.getText());
                    startActivity(intent);
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

    //metodo per ricevere i dati di ritorno e settare il valore nella edit text
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // check that it is the SecondActivity with an OK result
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) { // Activity.RESULT_OK

                // get String data from Intent
                String tax = data.getStringExtra("TAX");
                edit_tax.setText(tax);
            }
        }
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
                Intent intent = new Intent(InfoUniActivity.this, IntroActivity.class);
                startActivity(intent);
                break;
            case R.id.MENU_2:
                Intent intent2 = new Intent(InfoUniActivity.this, AboutUsActivity.class);
                startActivity(intent2);
                break;
        }
        return false;
    }
}
