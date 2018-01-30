package com.example.tommik.unitax;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InfoUniActivity extends AppCompatActivity {
    Button cal, prov_button, cost_button;
    EditText edit_tax;

    private static final String TAG = "InfoUniActivity";
    private static final int SECOND_ACTIVITY_REQUEST_CODE = 0;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_uni);

        edit_tax=(EditText)findViewById(R.id.edit_tax);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getExtras().getString("NOME_UNI");


        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.NameUni);
        textView.setText(message);
        edit_tax.setTextColor(Color.BLACK);




        cal = (Button) findViewById(R.id.tassescon);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //Intent intent = new Intent(InfoUniActivity.this, CalcoloTaxActivity.class);
               //startActivity(intent);
                Intent intent = new Intent(InfoUniActivity.this, CalcoloTaxActivity.class);
                startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE);
            }
        });



        prov_button = (Button) findViewById(R.id.provbut);
        prov_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, GraphActivity.class);
                intent.putExtra("graph_label", "Proventi");
                startActivity(intent);
            }
        });

        cost_button = (Button) findViewById(R.id.costbut);
        cost_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, GraphActivity.class);
                intent.putExtra("graph_label","Costi");
                startActivity(intent);
            }
        });
    }

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
}
