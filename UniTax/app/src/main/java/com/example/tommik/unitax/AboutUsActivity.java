package com.example.tommik.unitax;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;

public class AboutUsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        View aboutPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.sata)
                .setDescription("SATA\n" +
                        "\n" +
                        "Susanna, Andrea, Tommaso, Alessandro.\n" +
                        "Siamo quattro ragazzi dell'Università Ca' Foscari iscritti al Corso di Laurea di Informatica!\n" +
                        "Quest'applicazione è il frutto del corso Ingegneria del Software, tenuto dal prof. Cortesi.\n" +
                        "Il nostro obiettivo è creare un'app che permetta a chiunque di avere maggiori informazioni sul bilancio economico dell'università, rapportato all'importo delle tasse.\n" +
                        "Questo progetto è stato realizzato utilizzando gli Open Data forniti dall'Ateneo.")
                .addItem(new Element().setTitle("Versione 1.0"))
                .addGroup("Connettiti con noi")
                .addEmail("856329@stud.unive.it","Email")
                .addFacebook("BusattoAle","Seguici su facebook")
                .addTwitter("busattoale96","Seguici su twitter")
                .addGitHub("busattoale","Aiutaci su gitHub")

                .create();

        setContentView(aboutPage);
    }
}
