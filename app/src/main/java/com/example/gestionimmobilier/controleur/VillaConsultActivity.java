package com.example.gestionimmobilier.controleur;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;

public class VillaConsultActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private EditText nomV, adresseV,descriptionV, descriptionP, superficieV, anneesConstructionV, cautionV;
    private String nom,adresse,descriptionVilla,descriptionPiece,superficie;
    private Integer anneesConstruction;
    private Float caution;
    private Button retour, buttonModifier,buttonSupprimer, voirEquip;
    private Villa villaRecup;
    private VillaDAO recupVilla;
    private ImageView image;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_villa_consult);

        // Lien avec les fichiers de l'interface XML
        retour = findViewById(R.id.buttonRetourVillaConsult);
        buttonModifier = findViewById(R.id.buttonModifierConsult);
        buttonSupprimer = findViewById(R.id.buttonSupprimerConsult);
        voirEquip = findViewById(R.id.buttonVoirEquipementsVillaConsult);
        nomV = findViewById(R.id.editTextNomVillaConsult);
        adresseV = findViewById(R.id.editTextAdresseVillaConsult);
        descriptionV = findViewById(R.id.editTextDescriptionVillaConsult);
        descriptionP = findViewById(R.id.editTextDescriptionPieceVillaConsult);
        superficieV = findViewById(R.id.editTextSuperficieVillaConsult);
        anneesConstructionV = findViewById(R.id.editTextAnneeVillaConsult);
        cautionV = findViewById(R.id.editTextCautionVillaConsult);
        image = findViewById(R.id.imageVillaConsult);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                startActivity(i);
            }
        });

        int idV = getIntent().getIntExtra("id",0);
        Log.d("idVilla recup","id"+idV);

        //Création d'objet de la classe VillaDAO
        VillaDAO VillaAccesDAO = new VillaDAO(this);

        // Récupération des informations de la villa cliquée au préalable
        villaRecup = VillaAccesDAO.recupVillaId(idV);
        Log.d("idVilla recup","id"+villaRecup.toString());
        nom = villaRecup.getNom();
        adresse = villaRecup.getAdresse();
        descriptionVilla = villaRecup.getDescriptionV();
        descriptionPiece = villaRecup.getDescriptionP();
        superficie = villaRecup.getSuperficie();
        anneesConstruction = villaRecup.getAnneeConstru();
        caution = villaRecup.getCaution();

        // Modification des données de la villa en chaine de caractère
        nomV.setText(nom);
        adresseV.setText(adresse);
        descriptionV.setText(descriptionVilla);
        descriptionP.setText(descriptionPiece);
        superficieV.setText(superficie);
        anneesConstructionV.setText(anneesConstruction.toString());
        cautionV.setText(caution.toString());

        // Récupération de la liste des villas
        recupVilla = new VillaDAO(this);

        // Gestion du boutton 'Modifier'
        buttonModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nomNv = nomV.getText().toString();
                String adresseNv = adresseV.getText().toString();
                String descriptionNv = descriptionV.getText().toString();
                String descriptionPieceNv = descriptionP.getText().toString();
                String superficieNv = superficieV.getText().toString();
                int anneesConstructionNv = Integer.parseInt(anneesConstructionV.getText().toString());
                float cautionNv = Float.parseFloat(cautionV.getText().toString());
                // Création de la nouvelle villa modifiée
                Villa laNouvelleVilla = new Villa(nomNv, adresseNv, descriptionNv, descriptionPieceNv,
                        superficieNv, anneesConstructionNv, cautionNv);
                // Modification dans la BDD grâce à la fonction prévue
                int res = recupVilla.modifierVilla(laNouvelleVilla, villaRecup);
                /*
                    Si la modification a échoué :  message d'erreur s'affiche
                    Sinon si modification réussie, retour sur la page 'TypeVillaConsultActivity'
                    + message de suppréssion
                 */
                if (res != 1) {
                    Toast.makeText(getApplicationContext(), "Echec de la modification !", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Modification réussi !", Toast.LENGTH_LONG).show();
                }
                Intent i = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                startActivity(i);
            }
        });

        // Gestion du boutton 'Supprimer'
        buttonSupprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Suppréssion dans la BDD grâce à la fonction prévue
                long res = recupVilla.supprimerVilla(villaRecup);

                /*
                    Si la suppréssion a échoué, message d'erreur s'affiche
                    Sinon si suppréssion réalisée, retour sur la page 'TypeVillaConsultActivity'
                    + message de suppréssion
                 */
                if (res != 0){
                    Toast.makeText(getApplicationContext(),"Suppression réussi !",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Echec de la suppression !",Toast.LENGTH_LONG).show();
                }

            }
        });

        // Gestion du bouton 'VoirEquip' qui renvoie vers la page 'EquipementsConsultActivity'
        voirEquip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(VillaConsultActivity.this, EquipementsConsultActivity.class);
                intent2.putExtra("id",idV);
                startActivity(intent2);
            }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'TypeVillaConsultActivity'
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(VillaConsultActivity.this, TypeVillaConsultActivity.class);
                startActivity(intent2);
            }
        });
    }
}
