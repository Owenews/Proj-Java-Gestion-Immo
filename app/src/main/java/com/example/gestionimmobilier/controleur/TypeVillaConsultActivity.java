package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.TypeVilla;
import com.example.gestionimmobilier.modele.TypeVillaDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;


import java.util.ArrayList;

public class TypeVillaConsultActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private ListView listViewVillaConsult;
    private Spinner spinTypeVilla;
    private Button retour,ajouter;
    private ListView listeTypeVillaView;
    private ArrayList<TypeVilla> listeTypeVilla;
    private ArrayList<Villa> listeVilla;
    private ImageView image;

    /*
        Méthode permettant d'afficher la liste des villas en fonction de type de la Villa choisi
        dans le spinner
        Actualise la liste des villas à chaque changement du type de la Villa dabs le soinner
     */
    public void actualiser(int id){

        TypeVillaDAO typeVillaAcces = new TypeVillaDAO(this);
        listeVilla = typeVillaAcces.getTypeVillaStudio(id);
        ArrayAdapter<String> monAdapteur = new ArrayAdapter(this,android.R.layout.simple_list_item_1,listeVilla);
        listeTypeVillaView.setAdapter(monAdapteur);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_type_villa_consult);

        // Lien avec les fichiers de l'interface XML
        retour = findViewById(R.id.buttonRetourTypeVillaConsult);
        ajouter = findViewById(R.id.buttonAjouterTypeVillaConsult);
        listeTypeVillaView =  findViewById(R.id.listViewTypaVillaConsult);
        spinTypeVilla = (Spinner) findViewById(R.id.spinnerTypeVillaConsult);
        image = findViewById(R.id.imageView);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        // Gestion du bouton 'Retour' qui renvoie vers la page 'PropositionActivity'
        retour.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(TypeVillaConsultActivity.this, PropositionActivity.class);
                startActivity(i);
            }
        });

        // Gestion du bouton 'Ajouter' qui renvoie vers la page 'VillaAjoutActivity'
        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TypeVillaConsultActivity.this, VillaAjoutActivity.class);
                startActivity(i);
            }
        });

        //Création d'objet de la classe TypeVillaDAO
        TypeVillaDAO TypeVillaAccesDAO = new TypeVillaDAO(this);
        //Création d'objet de la classe VillaDAO
        VillaDAO villaAcess = new VillaDAO(this);

        // Récupération de la liste des typeVillas
        listeTypeVilla = TypeVillaAccesDAO.getTypeVilla();
        // Récupération de la liste des villas
        listeVilla = villaAcess.recupVilla();

        // Création d'un adapteur pour les typeVillas
        ArrayAdapter<TypeVilla> spinTypeVillaAdapter = new ArrayAdapter<TypeVilla>(this.getBaseContext(),
                android.R.layout.simple_spinner_item);

        // Parcours des typeVillas et ajout de ceux-ci dans l'adaoteur
        for(int i=0;i<listeTypeVilla.size();i++){
            spinTypeVillaAdapter.add(listeTypeVilla.get(i));
        }
        // Applique l'adapteur au spinner des TypeVillas
        spinTypeVilla.setAdapter(spinTypeVillaAdapter);


        // Gestion de la valeur du spinner typeVillas
        spinTypeVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TypeVilla typeVilla = (TypeVilla) spinTypeVilla.getSelectedItem();
                Toast.makeText(getApplicationContext(), "Voici les villa de type : "
                        + typeVilla.getNom() , Toast.LENGTH_SHORT).show();
                actualiser(typeVilla.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        // Applique l'adapteur à la listView des Villas
        listViewVillaConsult = findViewById(R.id.listViewTypaVillaConsult);

        // Création d'un adapteur pour les villas
        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listeVilla);

        // Affichage d'une listView contenant toutes les réservation de la BDD
        listeTypeVillaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(parent.getContext(), VillaConsultActivity.class);
                Villa clickItem = (Villa) listeTypeVillaView.getAdapter().getItem(position);
                // Récupère toutes les informations de la réservation quand on clique dessus
                i.putExtra("id",clickItem.getId());
                i.putExtra("nom",clickItem.getNom());
                i.putExtra("adresse",clickItem.getAdresse());
                i.putExtra("descriptionV",clickItem.getDescriptionV());
                i.putExtra("descriptionP",clickItem.getDescriptionP());
                i.putExtra("superficie",clickItem.getSuperficie());
                i.putExtra("anneesConstru",clickItem.getAnneeConstru());
                i.putExtra("caution",clickItem.getCaution());
                i.putExtra("idTypeVilla", clickItem.getIdTypeVilla());
                startActivity(i);
            }
        });
    }
}