package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Locataire;
import com.example.gestionimmobilier.modele.LocataireDAO;
import com.example.gestionimmobilier.modele.Reservation;
import com.example.gestionimmobilier.modele.ReservationDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;

public class ReservationsAjoutActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private Button ajouter, retour;
    private ArrayList<Locataire> listeLocataire;
    private ArrayList<Villa> listeVilla;
    private Spinner spinVilla, spinLocataire;
    private EditText txtDateArrivee, txtDateDepart, txtNbAdultes, txtNbEnfants, txtDateReservation,
            txtOptionMenage, txtMontantR;
    private int idVilla,idLocataire;
    private Reservation uneReservation;
    private String dateArrivee, dateDepart, dateReservation;
    private int nbAdultes, nbEnfants;
    private float montantR;
    private boolean optionMenage;
    private ImageView image;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_reservations_ajout);

        // Lien avec les fichiers de l'interface XML
        txtDateArrivee = findViewById(R.id.editTextDateArriveeReservationsAjout);
        txtDateDepart = findViewById(R.id.editTextDateDepartReservationsAjout);
        txtNbAdultes = findViewById(R.id.editTextNombreAdultesReservationsAjout);
        txtNbEnfants = findViewById(R.id.editTextNombreEnfantsReservationsAjout);
        txtDateReservation = findViewById(R.id.editTextDateReservationsAjout);
        txtOptionMenage = findViewById(R.id.editTextOptionMenageReservationsAjout);
        txtMontantR = findViewById(R.id.editTextMontantReservationsAjout);
        spinLocataire = findViewById(R.id.spinnerAjoutLoc);
        spinVilla = findViewById(R.id.spinnerAjoutVilla);
        ajouter = findViewById(R.id.buttonAjouterReservationsAjout);
        retour = findViewById(R.id.buttonRetourReservationsAjout);
        image = findViewById(R.id.imageView12);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        //Création d'objet de la classe ReservationDAO
        ReservationDAO reservationAcces = new ReservationDAO(this);
        //Création d'objet de la classe LocataireDAO
        LocataireDAO locaAcces = new LocataireDAO(this);
        //Création d'objet de la classe VillaDAO
        VillaDAO villaAcces = new VillaDAO(this);

        // Récupération de la liste des locataires
        listeLocataire = locaAcces.recupLocataire();
        // Récupération de la liste des villas
        listeVilla = villaAcces.recupVilla();

        // Création d'un adapteur pour les locataires
        ArrayAdapter<Locataire> spinLocataireAdapter = new ArrayAdapter<Locataire>(this,
                android.R.layout.simple_spinner_item);

        // Parcours des locataires et ajout de ceux-ci dans l'adaoteur
        for (int i=0; i<listeLocataire.size(); i++){
            spinLocataireAdapter.add(listeLocataire.get(i));
        }
        // Applique l'adapteur au spinner des Réservations
        spinLocataire.setAdapter(spinLocataireAdapter);

        // Création d'un adapteur pour les villas
        ArrayAdapter<Villa> spinVillaAdapter = new ArrayAdapter<Villa>(this,
                android.R.layout.simple_spinner_item);

        // Parcours des villas et ajout de ceux-ci dans l'adaoteur
        for (int i=0; i<listeVilla.size(); i++){
            spinVillaAdapter.add(listeVilla.get(i));
        }
        // Applique l'adapteur au spinner des Villas
        spinVilla.setAdapter(spinVillaAdapter);

        // Gestion de la valeur du spinner Locataire
        spinLocataire.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                       long id) {
                    idLocataire=listeLocataire.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Gestion de la valeur du spinner Villa
        spinVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                       long id) {
                idVilla=listeVilla.get(position).getId();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Gestion du boutton 'Ajouter'
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                dateArrivee=txtDateArrivee.getText().toString();
                dateDepart=txtDateDepart.getText().toString();
                nbAdultes=Integer.parseInt(txtNbAdultes.getText().toString());
                nbEnfants=Integer.parseInt(txtNbEnfants.getText().toString());
                dateReservation=txtDateReservation.getText().toString();
                optionMenage= Boolean.parseBoolean(txtOptionMenage.getText().toString());
                montantR= Float.parseFloat(txtMontantR.getText().toString());

                // Création d'une nouvelle réservation
                uneReservation = new Reservation(reservationAcces.dernierId(), dateArrivee,
                        dateDepart, nbAdultes, nbEnfants, dateReservation, optionMenage, montantR,
                        idVilla, idLocataire);

                // Insertion dans la base de données de la nouvelle réservation
                reservationAcces.addReservation(uneReservation);

                /* Si la champ de la date d'arrivée est vide, reste sur la page + message d'erreur
                 s'affiche
                */
                if (uneReservation.getDateArrivee().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir une date de" +
                            " réservation.",Toast.LENGTH_LONG).show();
                    Intent i = new Intent (ReservationsAjoutActivity.this,
                            ReservationsConsultActivity.class );
                }
                /* Si l'ajout s'est bien réalisée, retour sur la page 'ReservationConsultActivity'
                 + message d'ajout
                */
                else {
                    Intent i = new Intent (ReservationsAjoutActivity.this,
                            ReservationsConsultActivity.class );
                    reservationAcces.addReservation(uneReservation);
                    Toast.makeText(getApplicationContext(), "La  réservation du " +
                            uneReservation.getDateReservation() + " a été ajoutée.",
                            Toast.LENGTH_LONG).show();
                    startActivity(i);                }
                }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'ReservationConsultActivity'
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (ReservationsAjoutActivity.this,
                        ReservationsConsultActivity.class );
                startActivity(i);
            }
        });
    }
}