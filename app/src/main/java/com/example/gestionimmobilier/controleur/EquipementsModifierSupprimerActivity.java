package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Equipement;
import com.example.gestionimmobilier.modele.EquipementDAO;


public class EquipementsModifierSupprimerActivity extends AppCompatActivity {
    private Button retour, supprimer, modifier;
    EditText nomE, descriptionE, cautionE;
    int id;
    private Equipement unEquipement;
    private EquipementDAO recupEquipe;
    String nom, description;
    Float caution;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipements_modifier_supprimer);
        retour = (Button) findViewById(R.id.buttonRetourEquipementsModifierSupprimer);
        modifier = (Button) findViewById(R.id.buttonModifierEquipementsModifierSupprimer);
        supprimer = (Button) findViewById(R.id.buttonSupprimerEquipementsModifierSupprimer);

        recupEquipe = new EquipementDAO(this);
        nomE = (EditText) findViewById(R.id.editTextNomEquipementsModifierSupprimer);
        descriptionE = (EditText) findViewById(R.id.editTextDescriptionEquipementsModifierSupprimer);
        cautionE = (EditText) findViewById(R.id.editTextCautionEquipementsModifierSupprimer);

        Log.d("recupid","id "+ id);
        id = getIntent().getIntExtra("id",1);
        nom = getIntent().getStringExtra("nom");
        description = getIntent().getStringExtra("description");
        caution = getIntent().getFloatExtra("caution", -1);

        unEquipement = recupEquipe.getEquipement(id);

        Log.d("un equipement","id "+ unEquipement);
        //idE.setText(String.valueOf(id));
        nomE.setText(nom);
        descriptionE.setText(description);
        cautionE.setText(String.valueOf(caution));

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                nom=nomE.getText().toString();
                description=descriptionE.getText().toString();
                caution= Float.parseFloat(cautionE.getText().toString());

                Equipement newEquipement = new Equipement(id, nom, description, caution);
                Log.d("message","*****"+ newEquipement.toString());
                int res = recupEquipe.modifierEquipement(newEquipement, unEquipement);


                if (unEquipement.getNom().equals("")){
                    Toast.makeText(getApplicationContext(),"Veuillez saisir un nom pour cette equipement.",Toast.LENGTH_LONG).show();
                }
                else{
                    Intent i = new Intent(EquipementsModifierSupprimerActivity.this, EquipementsConsultActivity.class);
                    recupEquipe.modifierEquipement(newEquipement, unEquipement);
                    Toast.makeText(getApplicationContext(),"L'équipement " + unEquipement.getNom() + " a été modifiée.",Toast.LENGTH_LONG).show();
                    startActivity(i);

                }
            }
        });

        supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long res = recupEquipe.supprimerEquipement(unEquipement);
                if (res != 0){
                    Intent i2 = new Intent(EquipementsModifierSupprimerActivity.this, EquipementsConsultActivity.class);
                    Toast.makeText(getApplicationContext(),"L'équipement " + unEquipement.getNom() +  " a été supprimée.", Toast.LENGTH_LONG).show();
                    startActivity(i2);
                }else{
                    Toast.makeText(getApplicationContext(),"Echec de la suppression !",Toast.LENGTH_LONG).show();
                }

            }
        });


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(EquipementsModifierSupprimerActivity.this, EquipementsConsultActivity.class);
                startActivity(intent2);


            }
        });
    }
}