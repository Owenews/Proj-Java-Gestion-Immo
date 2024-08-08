package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Locataire;
import com.example.gestionimmobilier.modele.LocataireDAO;

import java.util.ArrayList;


public class LocatairesConsultActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private Button buttonRetourLocatairesConsult;
    private Button  buttonAjouterLocatairesConsult;
    private ListView listViewLocatairesConsult;
    private ArrayList<Locataire> listeLocataire;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_locataires_consult);

        // Lien avec les fichiers de l'interface XML
        buttonRetourLocatairesConsult = (Button) findViewById(R.id.buttonRetourLocatairesConsult);
        buttonAjouterLocatairesConsult = (Button) findViewById(R.id.buttonAjouterLocatairesConsult);
        listViewLocatairesConsult = (ListView) findViewById(R.id.listViewLocatairesConsult);
        image = findViewById(R.id.imageView7);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        //Création d'objet de la classe LocataireDAO
        LocataireDAO locaAcess = new LocataireDAO(getApplicationContext());
        // Récupération de la liste des locataires
        listeLocataire = locaAcess.recupLocataire();


        // Création d'un adapteur pour les locataires
        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listeLocataire);

        // Applique l'adapteur à la listView des Locataires
        listViewLocatairesConsult.setAdapter(monAdapter);

        // Affichage d'une listView contenant tous les locataires de la BDD
        listViewLocatairesConsult.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Locataire clickedItem = (Locataire) listViewLocatairesConsult.getAdapter().getItem(position);
                Log.d("message","locataire"+clickedItem.getNom());
                Intent i = new Intent(LocatairesConsultActivity.this, LocatairesConsultDetailsActivity.class);
                Toast.makeText(LocatairesConsultActivity.this, "Le client choisi", Toast.LENGTH_LONG).show();
                // Récupère toutes les informations du locataire quand on clique dessus
                i.putExtra("id", clickedItem.getId());
                i.putExtra("nom", clickedItem.getNom());
                i.putExtra("prenom", clickedItem.getPrenom());
                i.putExtra("adresse", clickedItem.getAdresse());
                i.putExtra("numeroTelephone", clickedItem.getNumTel());
                i.putExtra("email", clickedItem.getEmail());
                i.putExtra("commentaire", clickedItem.getCommentaire());
                startActivity(i);
            }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'PropositionActivity'
        buttonRetourLocatairesConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), PropositionActivity.class);
                startActivity(intent);
            }
        });

        // Gestion du bouton 'Ajouter' qui renvoie vers la page 'LocatairesAjoutActivity'
        buttonAjouterLocatairesConsult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LocatairesAjoutActivity.class);
                startActivity(intent);
            }
        });
    }
}