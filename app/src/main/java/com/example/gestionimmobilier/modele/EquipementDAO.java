package com.example.gestionimmobilier.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class EquipementDAO {

    // Classe permettant d'accéder aux données des villas de la BDD
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public EquipementDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    //Fonction qui récupère un équipement en fonction de l'id récupéré
    public Equipement getEquipement(int id) {
        Equipement unEquipement = null;
        Log.d("idequip", "id" + id);
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from equipements where id= "
                + id + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            unEquipement = new Equipement(id, curseur.getString(1), curseur.getString(2),
                    curseur.getFloat(3));
            Log.d("unEquipement", "*********" + unEquipement.toString());
        }
        return unEquipement;
    }

    //Fonction qui permet de supprimer un équipement de la villa choisi
    public long supprimerEquipement(Equipement unEquipement){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "nom ='"+unEquipement.getNom()+"'";
        Log.d("equipements supprimer", condition);
        ret = bd.delete("equipements", condition ,null);
        return ret;
    }

    //Fonction qui permet de modifier un équipement de la villa choisi
    public int modifierEquipement(Equipement nvEquipement, Equipement ancEquipement) {
        int ret;

        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("nom", nvEquipement.getNom());
        value.put("description", nvEquipement.getDescription());
        value.put("caution", nvEquipement.getCaution());

        String condition = "id =" + ancEquipement.getId()+ "";
        ret = bd.update("equipements", value, condition, null);
        return ret;
    }

    //Fonction qui récupère une collection d'équipements
    public ArrayList<Equipement> recupEquipement(){
        Cursor curseur;
        String req = "select * from equipements";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToEquipementArrayList(curseur);
    }

    /*
    Fonction qui récupère un curseur et implémente les informations liées aux équipements en
    fonction du contenu du curseur
    Retourne une collection d'équipements
    */
    private ArrayList<Equipement> cursorToEquipementArrayList(Cursor curseur){
        ArrayList<Equipement> listeEquip = new ArrayList<Equipement>();
        int id;
        String nom;
        String description;
        float caution;
        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = curseur.getInt(0);
            nom = curseur.getString(1);
            description= curseur.getString(2);
            caution = curseur.getFloat(3);
            listeEquip.add(new Equipement(id,nom,description,caution));
            curseur.moveToNext();
        }
        return listeEquip;
    }
}
