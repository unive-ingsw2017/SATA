package com.example.tommik.unitax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoUniActivity extends AppCompatActivity {
    Button cal, prov_button, cost_button;

    private static final String TAG = "InfoUniActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_uni);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getExtras().getString("NOME_UNI");

        // Capture the layout's TextView and set the string as its text
        TextView textView = (TextView) findViewById(R.id.NameUni);
        textView.setText(message);

        cal = (Button) findViewById(R.id.tassescon);
        cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, CalcoloTaxActivity.class);
                startActivity(intent);
            }
        });



        prov_button = (Button) findViewById(R.id.provbut);
        prov_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, GraphActivity.class);
                intent.putExtra("proventiCosti", R.raw.proventi_totali1719);
                startActivity(intent);
            }
        });

        cost_button = (Button) findViewById(R.id.costbut);
        cost_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InfoUniActivity.this, GraphActivity.class);
                intent.putExtra("proventiCosti", R.raw.costi_totali1719);
                startActivity(intent);
            }
        });
    }
}
