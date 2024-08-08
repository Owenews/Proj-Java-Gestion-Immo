package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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


import java.util.ArrayList;

public class ReservationsConsultDetailsActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private Button retour, supprimer, modifier;
    private EditText txtDateArriveeRecu, txtDateDepartRecu, txtNbAdultesRecu, txtNbEnfantsRecu,
            txtDateReservationRecu, txtOptionMenageRecu, txtMontantRRecu, txtIdVillaRRecu;
    private Spinner spinLocataire;
    private ReservationDAO recupReservation;
    private LocataireDAO locataireAcces = new LocataireDAO(this);
    private ArrayList<Locataire> listeLocataire;
    private Reservation laReservation;
    private ImageView image;
    private String dateArrivee, dateDepart, dateReservation;
    private int idR, nbAdultes, nbEnfants, idLocataire, idVilla;
    private float montantR;
    private boolean optionMenage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_reservations_consult_details);

        // Lien avec les fichiers de l'interface XML
        txtDateArriveeRecu = findViewById(R.id.editTextDateArriveeReservationsConsultDetails);
        txtDateDepartRecu = findViewById(R.id.editTextDateDepartReservationsConsultDetails);
        txtNbAdultesRecu = findViewById(R.id.editTextNombreAdultesReservationsConsultDetails);
        txtNbEnfantsRecu = findViewById(R.id.editTextNombreEnfantsReservationConsultDetails);
        txtDateReservationRecu = findViewById(R.id.editTextDateReservationsConsultDetails);
        txtOptionMenageRecu = findViewById(R.id.editTextOptionMenageReservationsConsultDetails);
        txtMontantRRecu = findViewById(R.id.editTextMontantRReservationsConsultDetails);
        txtIdVillaRRecu = findViewById(R.id.editTextIdVillaReservationsConsultDetails);
        retour = findViewById(R.id.buttonRetourReservationsConsultDetails);
        supprimer = findViewById(R.id.buttonSupprimerReservationsConsultDetails);
        modifier = findViewById(R.id.buttonModifierReservationsConsultDetails);
        spinLocataire = findViewById(R.id.spinnerLocatairesReservationsConsultDetails);
        image = findViewById(R.id.imageView11);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        // Récupération des informations de la réservation cliquée au préalable
        recupReservation = new ReservationDAO(this);
        idR = getIntent().getIntExtra("id",1);
        dateArrivee = getIntent().getStringExtra("dateArrivee");
        dateDepart = getIntent().getStringExtra("dateDepart");
        nbAdultes = getIntent().getIntExtra("nbAdultes",-1);
        nbEnfants = getIntent().getIntExtra("nbEnfants",-1);
        dateReservation = getIntent().getStringExtra("dateReservation");
        optionMenage = getIntent().getBooleanExtra("optionMenage",true);
        montantR = getIntent().getFloatExtra("montantR",-1);
        idLocataire = getIntent().getIntExtra("idLocataire",0);
        idVilla = getIntent().getIntExtra("idVilla",0);

        // Récupération de l'id de la réservation cliquée au préalable
        laReservation = recupReservation.getReservations(idR);

        // Modification des données de la réservation en chaine de caractère
        txtDateArriveeRecu.setText(dateArrivee);
        txtDateDepartRecu.setText(dateDepart);
        txtNbAdultesRecu.setText(String.valueOf(nbAdultes));
        txtNbEnfantsRecu.setText(String.valueOf(nbEnfants));
        txtDateReservationRecu.setText(dateReservation);
        txtOptionMenageRecu.setText(String.valueOf(optionMenage));
        txtMontantRRecu.setText(String.valueOf(montantR));
        txtIdVillaRRecu.setText(String.valueOf(idVilla));

        //Création d'objet de la classe LocataireDAO
        LocataireDAO locaAcces = new LocataireDAO(this);
        // Récupération de la liste des locataires
        listeLocataire = locaAcces.recupLocataire();

        // Création d'un adapteur pour les locataires
        ArrayAdapter<Locataire> spinLocataireAdapter = new ArrayAdapter<Locataire>(this,
                android.R.layout.simple_spinner_item);

        // Parcours des locataires et ajout de ceux-ci dans l'adaoteur
        for (int i=0; i<listeLocataire.size(); i++){
            spinLocataireAdapter.add(listeLocataire.get(i));
        }
        // Applique l'adapteur au spinner des Locataires
        spinLocataire.setAdapter(spinLocataireAdapter);

        // Gestion de la valeur du spinner locataire
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


        // Gestion du boutton 'Modifier'
        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dateArrivee=txtDateArriveeRecu.getText().toString();
                dateDepart=txtDateDepartRecu.getText().toString();
                nbAdultes=Integer.parseInt(txtNbAdultesRecu.getText().toString());
                nbEnfants=Integer.parseInt(txtNbEnfantsRecu.getText().toString());
                dateReservation=txtDateReservationRecu.getText().toString();
                optionMenage= Boolean.parseBoolean(txtOptionMenageRecu.getText().toString());
                montantR= Float.parseFloat(txtMontantRRecu.getText().toString());

                // Création de la nouvelle réservation modifiée
                Reservation newReservation = new Reservation(idR, dateArrivee,dateDepart, nbAdultes,
                        nbEnfants, dateReservation, optionMenage, montantR, idVilla, idLocataire);
                Log.d("message","*****"+ newReservation.toString());

                // Modification dans la BDD grâce à la fonction prévue
                int res = recupReservation.modifierReservation(newReservation, laReservation);

                /*
                    Si la modification a échoué, reste sur le page + message d'erreur s'affiche
                    Sinon si modification réussie, retour sur la page 'ReservationConsultActivity'
                    + message de suppréssion
                 */
                if (res != 1){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir une date de " +
                            "réservation.",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i = new Intent(ReservationsConsultDetailsActivity.this,
                            ReservationsConsultActivity.class);
                    recupReservation.modifierReservation(newReservation, laReservation);
                    Toast.makeText(getApplicationContext(),"Votre réservation a été modifiée.",
                            Toast.LENGTH_LONG).show();
                    startActivity(i);

                }
            }
        });

        // Gestion du boutton 'Supprimer'
        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Suppréssion dans la BDD grâce à la fonction prévue
                long res = recupReservation.supprimerReservation(laReservation);

                /*
                    Si la suppréssion a échoué, reste sur le page + message d'erreur s'affiche
                    Sinon si suppréssion réalisée, retour sur la page 'ReservationConsultActivity'
                    + message de suppréssion
                 */
                if (res != 0){
                    Intent i2 = new Intent(ReservationsConsultDetailsActivity.this,
                            ReservationsConsultActivity.class);
                    Toast.makeText(getApplicationContext(),"La réservation du "
                            + laReservation.getDateReservation() +
                            " a été supprimée.", Toast.LENGTH_LONG).show();
                    startActivity(i2);
                }else{
                    Toast.makeText(getApplicationContext(),"Echec de la suppression !",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'ReservationConsultActivity'
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (ReservationsConsultDetailsActivity.this,
                        ReservationsConsultActivity.class );
                startActivity(i);
            }
        });
    }
}