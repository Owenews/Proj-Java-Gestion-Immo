package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Equipement;
import com.example.gestionimmobilier.modele.EquipementDAO;
import com.example.gestionimmobilier.modele.Reservation;
import com.example.gestionimmobilier.modele.ReservationDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;

public class EquipementsConsultActivity extends AppCompatActivity {
    Button retour, ajouter;
    ListView listeEquipementView;

    int idV, idE;
    private Villa laVilla;
    private VillaDAO villaRecup = new VillaDAO(this);
    private Equipement lEquip;


    Equipement equi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipements_consult);
        idV=getIntent().getIntExtra("id",0);
        laVilla = villaRecup.getVilla(idV);
        retour = findViewById(R.id.buttonRetourEquipementsConsult);
        ajouter = findViewById(R.id.buttonAjouterEquipementsConsult);
        listeEquipementView =  findViewById(R.id.listViewEquipementsConsult);

        ArrayList<Equipement> EquipementArrayList = new ArrayList<Equipement>();

        EquipementDAO lesEquipements = new EquipementDAO(this);
        EquipementArrayList = lesEquipements.recupEquipement();

        ArrayAdapter monAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, EquipementArrayList);
        listeEquipementView.setAdapter(monAdapter);

        listeEquipementView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(EquipementsConsultActivity.this, EquipementsModifierSupprimerActivity.class);
                Equipement clickItem = (Equipement) listeEquipementView.getAdapter().getItem(position);

                i.putExtra("id",clickItem.getId());
                i.putExtra("nom",clickItem.getNom());

                i.putExtra("description",clickItem.getDescription());
                i.putExtra("caution",clickItem.getCaution());

                startActivity(i);
            }
        });



        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(EquipementsConsultActivity.this, EquipementsAjoutActivity.class);
                i2.putExtra("id", idV);
                i2.putExtra("id",idE);
                startActivity(i2);

            }
        });


        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(EquipementsConsultActivity.this, TypeVillaConsultActivity.class);
                startActivity(intent2);


            }
        });
    }
}