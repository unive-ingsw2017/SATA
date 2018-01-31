package com.example.tommik.unitax;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by Alessandro on 31/01/2018.
 */

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Note here that we DO NOT use setContentView();


        // Instead of fragments, you can also use our default slide
        // Just set a title, description, background and image. AppIntro will do the rest.
        addSlide(AppIntroFragment.newInstance("Seleziona Università","", "Clicca sul nome della tua università, si apriranno le informazioni generali", "",R.drawable.sc1, Color.RED,Color.WHITE,Color.WHITE));
        addSlide(AppIntroFragment.newInstance("Info Università","", "Qui puoi cliccare su uno dei bottoni per vedere le informazioni generali sulla tua università. Inoltre se conosci l'importo delle tue tasse puoi inserirlo e vedere come vengono utilizzate, se non lo conosci premi sul bottone e ti si aprirà una pagina per il calcolo delle tasse", "",R.drawable.sc2, Color.RED,Color.WHITE,Color.WHITE));
        addSlide(AppIntroFragment.newInstance("Visualizzazione Grafici","", "Questo è un esempio generale per le pagine con grafici, sotto il grafico hai dei pulsanti per cambiare i dati, è possibile far ruotare il grafico", "",R.drawable.sc4, Color.RED,Color.WHITE,Color.WHITE));
        addSlide(AppIntroFragment.newInstance("Calcolo tasse","", "Qui puoi inserire tutti i dati per calcolare le tasse.Se non conosci l'importo dell'ISEE, clicca sul bottone per calcolarlo.Inoltre c'è un link alle informzioni generali per le tasse della tua università", "",R.drawable.sc3, Color.RED,Color.WHITE,Color.WHITE));

        // OPTIONAL METHODS
        // Override bar/separator color.
        setBarColor(Color.parseColor("#FF8E0000"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        showSkipButton(true);
        setProgressButtonEnabled(true);

        setDoneText("Capito!");
        setSkipText("Salta");
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
       finish();
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
}