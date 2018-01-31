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
