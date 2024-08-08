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
import com.example.gestionimmobilier.modele.Reservation;
import com.example.gestionimmobilier.modele.TypeVilla;
import com.example.gestionimmobilier.modele.TypeVillaDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;

public class VillaAjoutActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private int idTypeVilla, anneeConstru;
    private String nom, adresse, descriptionV, descriptionP, superficie;
    private float caution;
    private Spinner spinTypeVilla;
    private Button btnAjout, btnRetour;
    private Villa uneVilla;
    private ArrayList<TypeVilla> listeTypeVilla;
    private EditText editTextNomVillaAjout, editTextAdresseVillaAjout, editTextDescriptionVillaAjout,
            editTextDescriptionPiecesVillaAjout, editTextSuperficieVillaAjout, editTextAnneeVillaAjout,
            editTextCautionVillaAjout;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_villa_ajout);

        // Lien avec les fichiers de l'interface XML
        btnAjout = (Button) findViewById(R.id.buttonAjouterVillaAjout);
        btnRetour = (Button) findViewById(R.id.buttonRetourVillaAjout);
        editTextNomVillaAjout = (EditText) findViewById(R.id.editTextNomVillaAjout);
        editTextAdresseVillaAjout = (EditText) findViewById(R.id.editTextAdresseVillaAjout);
        editTextDescriptionVillaAjout = (EditText) findViewById(R.id.editTextDescriptionVillaAjout);
        editTextDescriptionPiecesVillaAjout = (EditText) findViewById(R.id.editTextDescriptionPiecesVillaAjout);
        editTextSuperficieVillaAjout = (EditText) findViewById(R.id.editTextSuperficieVillaAjout);
        editTextAnneeVillaAjout = (EditText) findViewById(R.id.editTextAnneeVillaAjout);
        editTextCautionVillaAjout = (EditText) findViewById(R.id.editTextCautionVillaAjout);
        spinTypeVilla = (Spinner) findViewById(R.id.spinnerTypeDeVillaAjout);
        image = findViewById(R.id.imageView6);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        //Création d'objet de la classe VillaDAO
        VillaDAO villaAcces = new VillaDAO(this);
        //Création d'objet de la classe TypeVillaDAO
        TypeVillaDAO typeVillaAcces = new TypeVillaDAO(this);

        // Récupération de la liste des types de villa
        listeTypeVilla = typeVillaAcces.getTypeVilla();

        // Création d'un adapteur pour les types de villa
        ArrayAdapter<TypeVilla> spinTypeVillaAdapter = new ArrayAdapter<TypeVilla>(this.getBaseContext(),
                android.R.layout.simple_spinner_item);

        // Parcours des type de villa et ajout de ceux-ci dans l'adaoteur
        for(int i=0;i<listeTypeVilla.size();i++){
            spinTypeVillaAdapter.add(listeTypeVilla.get(i));
        }
        // Applique l'adapteur au spinner des TypeVillas
        spinTypeVilla.setAdapter(spinTypeVillaAdapter);
        Log.d("log","test");

        // Gestion de la valeur du spinner TypeVilla
        spinTypeVilla.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                idTypeVilla=listeTypeVilla.get(arg2).getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'TypeVillaConsultActivity'
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(VillaAjoutActivity.this, TypeVillaConsultActivity.class);
                startActivity(intent3);
            }
        });

        // Gestion du boutton 'Ajouter'
        btnAjout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                nom=editTextNomVillaAjout.getText().toString();
                adresse=editTextAdresseVillaAjout.getText().toString();
                descriptionV=editTextDescriptionVillaAjout.getText().toString();
                descriptionP=editTextDescriptionPiecesVillaAjout.getText().toString();
                superficie=editTextSuperficieVillaAjout.getText().toString();
                anneeConstru=Integer.parseInt(editTextAnneeVillaAjout.getText().toString());
                caution= Float.parseFloat(editTextCautionVillaAjout.getText().toString());

                // Création d'une nouvelle villa
                uneVilla = new Villa(villaAcces.dernierId(), nom, adresse, descriptionV, descriptionP,
                        superficie, anneeConstru, caution, idTypeVilla);

                // Insertion dans la base de données de la nouvelle villa
                villaAcces.addVilla(uneVilla);

                // Si la champ du nom est vide, reste sur la page + message d'erreur s'affiche
                if (uneVilla.getNom().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir un nom.",Toast.LENGTH_LONG).show();

                }
                /* Si l'ajout s'est bien réalisée, retour sur la page 'TypeVillaConsultActivity'
                 + message d'ajout
                */
                else {
                    Intent i = new Intent (VillaAjoutActivity.this, TypeVillaConsultActivity.class );
                    villaAcces.addVilla(uneVilla);
                    Toast.makeText(getApplicationContext(), "La  villa " + uneVilla.getNom() + " a été ajoutée.",
                            Toast.LENGTH_LONG).show();
                    startActivity(i);
                }
            }
        });
    }
}