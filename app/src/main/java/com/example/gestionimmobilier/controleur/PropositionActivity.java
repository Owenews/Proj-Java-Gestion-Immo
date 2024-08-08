package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.gestionimmobilier.R;


public class PropositionActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private Button villa, locataire, reservation, retour;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_proposition);

        // Lien avec les fichiers de l'interface XML
        villa = findViewById(R.id.buttonVillaMain);
        locataire = findViewById(R.id.buttonLocatairesMain);
        reservation = findViewById(R.id.buttonReservationsMain);
        retour = findViewById(R.id.buttonRetourMain);
        image = findViewById(R.id.imageViewMain);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        // Gestion du bouton 'Villa' qui renvoie vers la page 'TypeVillaConsultActivity'
        villa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent villa = new Intent(PropositionActivity.this,
                        TypeVillaConsultActivity.class);
                startActivity(villa);

            }
        });

        // Gestion du bouton 'Locataire' qui renvoie vers la page 'LocatairesConsultActivity'
        locataire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loc = new Intent(PropositionActivity.this,
                        LocatairesConsultActivity.class);
                startActivity(loc);


            }
        });

        // Gestion du bouton 'Reservation' qui renvoie vers la page 'ReservationsConsultActivity'
        reservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reserv = new Intent(PropositionActivity.this,
                        ReservationsConsultActivity.class);
                startActivity(reserv);

            }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'MainActivity'
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ret = new Intent(PropositionActivity.this, MainActivity.class);
                startActivity(ret);

            }
        });
    }
}