package com.example.tommik.unitax;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SelectUniActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_uni);

        // When we select this spinner item, a Toast message will be displayed
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.uni, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        addListenerOnButton();
    }


    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.buttonSelectUni);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SelectUniActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : " +
                                String.valueOf(spinner.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(SelectUniActivity.this,
                "OnClickListener : " +
                        "\nSpinner 1 : " +
                        String.valueOf(spinner.getSelectedItem()),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //TODO Auto-generated method stub
    }
}