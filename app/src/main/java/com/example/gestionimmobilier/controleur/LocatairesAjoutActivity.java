package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Locataire;
import com.example.gestionimmobilier.modele.LocataireDAO;

public class LocatairesAjoutActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private ImageView image;
    private Button boutonValider;
    private Button boutonRetour;
    private String nom, prenom, adresse, tel, email, com;
    private EditText editNom, editPrenom, editAdresse, editTel, editEmail, editCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_locataires_ajout);

        //Création d'objet de la classe LocataireDAO
        LocataireDAO accesLocataire=new LocataireDAO(this);

        // Lien avec les fichiers de l'interface XML
        editNom=findViewById(R.id.editTextNomLocatairesAjout);
        editPrenom=findViewById(R.id.editTextPrenomLocatairesAjout);
        editAdresse=findViewById(R.id.editTextAdresseLocatairesAjout);
        editTel=findViewById(R.id.editTextTelephoneLocatairesAjout);
        editEmail=findViewById(R.id.editTextEmailLocatairesAjout);
        editCom=findViewById(R.id.editTextCommentaireLocatairesAjout);
        boutonValider=findViewById(R.id.buttonAjouterLocatairesAjout);
        boutonRetour=findViewById(R.id.buttonRetourLocatairesAjout);
        image = findViewById(R.id.imageView9);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        // Gestion du bouton 'Valider'
        boutonValider.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nom=editNom.getText().toString();
                prenom=editPrenom.getText().toString();
                adresse=editAdresse.getText().toString();
                tel=editTel.getText().toString();
                email=editEmail.getText().toString();
                com=editCom.getText().toString();

                // Création d'une nouvelle réservation
                Locataire unLocataire=new Locataire(nom,prenom,adresse,tel,email,com);

                // Insertion dans la base de données de la nouvelle réservation
                accesLocataire.addLocataire(unLocataire);

                // Renvoi vers la page 'LocatairesConsultActivity' lors du clic sur le boutton
                Intent intent=new Intent(LocatairesAjoutActivity.this,
                        LocatairesConsultActivity.class);

                startActivity(intent);

            }
        });

        // Gestion du bouton 'Retour' qui renvoie vers la page 'LocatairesAjoutActivity'
        boutonRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LocatairesAjoutActivity.this,
                        LocatairesConsultActivity.class);

                startActivity(intent);
            }
        });
    }
}