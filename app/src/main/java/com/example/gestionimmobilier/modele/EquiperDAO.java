package com.example.gestionimmobilier.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class EquiperDAO {

    // Classe permettant d'accéder aux données des villas de la BDD
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public EquiperDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    /*
        Fonction qui récupère un équipement de la villa en fonction de l'id de la villa et de
        l'équipement récupéré
     */
    public Equiper getEquiper(int idV, int idE) {
        Equiper unEquiper = null;
        Log.d("idequip", "idVilla + idEquipements" + idV + idE);
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from equiper where idVilla="
                + idV + " AND idEquipements = " + idE + ";", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            unEquiper = new Equiper(idV, idE, curseur.getString(2));
            Log.d("unEquipement", "*********" + unEquiper.toString());
        }
        return unEquiper;
    }

    // Fonction qui supprime une equipement de la villa dans la BDD
    public long supprimerEquipement(Equiper unEquipement){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "idVilla = '" + unEquipement.getIdEquipements() +
                "' AND idEquipements = '" +unEquipement.getIdVilla() +"';";
        Log.d("equipement supprimer", condition);
        ret = bd.delete("equiper", condition ,null);
        return ret;
    }

    //Fonction qui récupère une collection des équipement de la villa chosie
    public ArrayList<Equiper> recupEquiper(){
        Cursor curseur;
        String req = "select * from equiper";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToEquiperArrayList(curseur);
    }

    /*
        Fonction qui récupère un curseur et implémente les informations liées aux equipements de la
         villa en fonction du contenu du curseur
        Retourne une collection des equipements de la villa
    */
    private ArrayList<Equiper> cursorToEquiperArrayList(Cursor curseur){
        ArrayList<Equiper> listeEquiper = new ArrayList<Equiper>();
        int idVilla;
        int idEquipements;
        String etat;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            idVilla = curseur.getInt(0);
            idEquipements = curseur.getInt(1);
            etat= curseur.getString(2);
            listeEquiper.add(new Equiper(idVilla,idEquipements,etat));
            curseur.moveToNext();
        }
        return listeEquiper;
    }

    // Fonction qui ajoute une equipement de la villa dans la BDD
    public long addEquipement(Equiper equipement){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("idVilla", equipement.getIdVilla());
        value.put("idEquipements",equipement.getIdEquipements());
        value.put("etat", equipement.getEtat());

        ret = bd.insert("equiper", null, value);

        return ret;
    }

    /*
        Fonction qui récupère le nom d'un équipement et vérifie qu'il ne se trouve pas déjà dans le
        villa concerné
     */
    public int getNomEquipement(String nom){
        int idEquip=-1;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from equipements " +
                " where nom = '" +nom+ "';",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            idEquip = curseur.getInt(0);
        }
         return idEquip;
    }
}