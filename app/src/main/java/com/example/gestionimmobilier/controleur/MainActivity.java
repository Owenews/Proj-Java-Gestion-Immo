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
import com.example.gestionimmobilier.modele.Role;
import com.example.gestionimmobilier.modele.RoleDAO;
import com.example.gestionimmobilier.modele.Utilisateur;
import com.example.gestionimmobilier.modele.UtilisateurDAO;


public class MainActivity extends AppCompatActivity {

    //Déclaration des variables de classe
    private EditText txtLogin, txtMdp;
    private Button btnConnecter;
    private ImageView image;
    private String typeRole;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Méthode permettant de récupérer l'activité en cas de crash de l'application
        super.onCreate(savedInstanceState);
        // Méthode associant cette page au fichier XML choisi
        setContentView(R.layout.activity_main);

        // Lien avec les fichiers de l'interface XML
        txtLogin = findViewById(R.id.editTextIdLogin);
        txtMdp = findViewById(R.id.editTextMotDePasseLogin);
        btnConnecter = findViewById(R.id.buttonConnexionLogin);
        image = findViewById(R.id.imageView13);

        //Permet l'affichage de l'image choisi dans le fichier XML
        image.setImageResource(R.drawable.logovillepau);

        //Création d'objet de la classe UtilisateurDAO
        UtilisateurDAO unUtilisateurDAO = new UtilisateurDAO(this);

        // Gestion du bouton 'Se Connecter'
        btnConnecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Récupération des valeurs contenues dans les EditText
                String login=txtLogin.getText().toString();
                String motDePasse=txtMdp.getText().toString();
                Log.d("test connexion","******"+login+ " " +motDePasse);
                Utilisateur utilisateurConnexion=null;

                try{
                    // Test si l'identifiant et le mot de passe est correct
                    utilisateurConnexion= unUtilisateurDAO.seConnecter(login,motDePasse);

                    /*
                        Si l'identifiant et le mot de passe sont faux : reste sur la page
                        + message d'erreur
                        Sinon passage à la page d'accueil + message de connexion
                     */
                    if(utilisateurConnexion!=null){
                        Intent intent = new Intent(MainActivity.this,
                                PropositionActivity.class);
                        Log.d("connexion","connexion");
                        startActivity(intent);


                        Toast.makeText(getApplicationContext(), "Bienvenue à toi !",
                                Toast.LENGTH_LONG).show();

                    }else{

                        Toast.makeText(getApplicationContext(), "Identifiant ou mot de " +
                                "passe incorrect", Toast.LENGTH_LONG).show();


                    }
                }catch(Exception e){
                    Log.d("testConnexion",e.getMessage());
                }
            }
        });
    }
}
