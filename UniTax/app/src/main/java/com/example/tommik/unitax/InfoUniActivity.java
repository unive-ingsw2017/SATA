package com.example.tommik.unitax;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class InfoUniActivity extends AppCompatActivity {

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
    }
}
