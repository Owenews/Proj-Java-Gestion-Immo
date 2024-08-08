package com.example.gestionimmobilier.controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.gestionimmobilier.R;
import com.example.gestionimmobilier.modele.Equipement;
import com.example.gestionimmobilier.modele.EquipementDAO;
import com.example.gestionimmobilier.modele.Equiper;
import com.example.gestionimmobilier.modele.EquiperDAO;
import com.example.gestionimmobilier.modele.Villa;
import com.example.gestionimmobilier.modele.VillaDAO;

import java.util.ArrayList;


public class EquipementsAjoutActivity extends AppCompatActivity {

    private ListView listeEquipement;
    private Button bouton;
    private EquiperDAO equiperDAO=new EquiperDAO(this);
    private Equiper equipement;

    private EquipementDAO equipementDAO=new EquipementDAO(this);
    private ArrayList<Equipement> lesEquipements=new ArrayList<Equipement>();
    private int idV, idE;
    private String etat;
    private Villa laVilla;
    private VillaDAO villaRecup = new VillaDAO(this);
    private Equipement lEquipement;
    private EquipementDAO equipementRecup = new EquipementDAO(this);

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipements_ajout);
        idV=getIntent().getIntExtra("id",0);
        laVilla = villaRecup.getVilla(idV);
        idE=getIntent().getIntExtra("id",0);
        lEquipement = equipementRecup.getEquipement(idE);
        listeEquipement=(ListView) findViewById(R.id.list);
        bouton=(Button)findViewById(R.id.bouton) ;
        lesEquipements=equipementDAO.recupEquipement();

        ArrayAdapter monAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice,lesEquipements);
        listeEquipement.setChoiceMode(listeEquipement.CHOICE_MODE_MULTIPLE);
        listeEquipement.setAdapter(monAdapter);

        bouton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int j=0;
                String etat= "bon";
                long id;
                ArrayList<Equipement> listeChoix=new ArrayList<Equipement>();
                int len=listeEquipement.getCount();
                SparseBooleanArray checked=listeEquipement.getCheckedItemPositions();
                for (int i=0; i<len;i++){
                    if (checked.get(i)) {

                        listeChoix.add(new Equipement(j,listeEquipement.getItemAtPosition(i).toString()));
                        idV=equiperDAO.getNomEquipement(listeEquipement.getItemAtPosition(i).toString());
                        j++;
                        equipement=new Equiper(idV, idE, etat);
                        Log.d("info","***" +equipement);
                        id =  equiperDAO.addEquipement(equipement);
                        if(id!=-1) {
                            Log.d("message", "ok*****");
                        }

                    }
                }
                for(Equipement choix:listeChoix){
                    Log.d("choix","********"+choix.getNom()+""+choix.getId());
                }
            }
        });


    }
}