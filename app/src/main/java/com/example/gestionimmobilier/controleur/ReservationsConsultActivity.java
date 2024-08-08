package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Reservation;
import com.example.gestionimmobilier.modele.ReservationDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;


import java.util.ArrayList;

public class ReservationsConsultActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private Spinner spinVilla;
    private ArrayList<Villa> listeVilla;
    private int idVilla;
    private ListView listeViewRervationConsult;
    private Button retour, ajouter;
    private ArrayList<Reservation> listeReservation;
    private ImageView image;

    /*
        Méthode permettant d'afficher la liste des réservations en fonction de l'id  de la villa
        choisi dans le spinner
        Actualise la liste des réservations à chaque changement d'id dabs le soinner
     */
    public void actualiser(int id){
        ReservationDAO rAcces = new ReservationDAO(this);
        listeReservation = rAcces.getVillaFiltre(id);
        for (Reservation uneReservation : listeReservation){
            Log.d("reservation","*********" + uneReservation.toString());
        }
        ArrayAdapter<String> monAdapteur = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,listeReservation);
        listeViewRervationConsult.setAdapter(monAdapteur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_reservations_consult);

        // Lien avec les fichiers de l'interface XML
        ajouter = findViewById(R.id.buttonAjouterReservationsConsult);
        retour = findViewById(R.id.buttonRetourReservationsConsult);
        listeViewRervationConsult = findViewById(R.id.listViewReservationsAjout);
        spinVilla = findViewById(R.id.spinnerChoixVillaReservationsConsult);
        image = findViewById(R.id.imageView10);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        // Gestion du bouton 'Ajouter' qui renvoie vers la page 'ReservationAjoutActivity'
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (ReservationsConsultActivity.this,
                        ReservationsAjoutActivity.class );
                startActivity(i);
            }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'PropositionActivity'
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent (ReservationsConsultActivity.this,
                        PropositionActivity.class );
                startActivity(i2);
            }
        });

        //Création d'objet de la classe VillaDAO
        VillaDAO villaAcces = new VillaDAO(this);
        //Création d'objet de la classe ReservationDAO
        ReservationDAO reservationAcces = new ReservationDAO(this);

        // Récupération de la liste des villas
        listeVilla = villaAcces.recupVilla();
        // Récupération de la liste des réservations
        listeReservation = reservationAcces.getLesReservations();

        // Création d'un adapteur pour les villas
        ArrayAdapter<Villa> spinVillaAdapter = new ArrayAdapter<Villa>(this,
                android.R.layout.simple_spinner_item);

        // Parcours des villas et ajout de ceux-ci dans l'adaoteur
        for (int i=0; i<listeVilla.size(); i++){
            spinVillaAdapter.add(listeVilla.get(i));
            Log.d("message","***" +listeVilla.get(i));
        }
        // Applique l'adapteur au spinner des Villas
        spinVilla.setAdapter(spinVillaAdapter);

        // Gestion de la valeur du spinner villa
        spinVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                       long id) {
                Villa uneVilla = (Villa) spinVilla.getSelectedItem();
                Toast.makeText(getApplicationContext(), "Voici les réservations de la "
                        + uneVilla.getNom(),  Toast.LENGTH_SHORT).show();
                actualiser(uneVilla.getId());
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        // Création d'un adapteur pour les réservations
        ArrayAdapter lesReservations = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, listeReservation);

        // Applique l'adapteur à la listView des Réservations
        listeViewRervationConsult.setAdapter(lesReservations);

        // Affichage d'une listView contenant toutes les réservation de la BDD
        listeViewRervationConsult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(ReservationsConsultActivity.this,
                        ReservationsConsultDetailsActivity.class);
                Reservation clickedItem = (Reservation) listeViewRervationConsult.getAdapter().
                        getItem(position);
                // Récupère toutes les informations de la réservation quand on clique dessus
                i.putExtra("id", clickedItem.getId());
                i.putExtra("dateArrivee", clickedItem.getDateArrivee());
                i.putExtra("dateDepart", clickedItem.getDateDepart());
                i.putExtra("nbAdultes", clickedItem.getNbAdultes());
                i.putExtra("nbEnfants", clickedItem.getNbEnfants());
                i.putExtra("dateReservation", clickedItem.getDateReservation());
                i.putExtra("optionMenage", clickedItem.getOptionMenage());
                i.putExtra("montantR", clickedItem.getMontantR());
                i.putExtra("idVilla", clickedItem.getIdVilla());
                i.putExtra("idLocataires", clickedItem.getIdLocataires());
                startActivity(i);
            }
        });
    }
}