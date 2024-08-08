package com.example.gestionimmobilier.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class VillaDAO {

    // Classe permettant d'accéder aux données des villas de la BDD
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public VillaDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    //Fonction qui récupère une collection de villas
    public ArrayList<Villa> recupVilla(){
        Cursor curseur;
        String req = "select * from villa";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToVillaArrayList(curseur);
    }

    /*
        Fonctoin qui récupère l'id le plus grand des villa et lui ajoute +1 dans la BDD
        Retourne l'id le plus petit disponible pour les villas
    */
    public int dernierId() {
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select max(id)+1 from Villa",
                null);
        curseur.moveToFirst();
        int lastId = curseur.getInt(0);
        return lastId;
    }

    //Fonction qui récupère une villa en fonction de l'id récupéré
    public Villa recupVillaId(int id) {
        Log.d("*****","id recup"+id);
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("Select * from Villa where id =" +
                " " + id + " ; ", null);
        Villa  uneVilla=null;

        int idV, anneesConstruction, idTypeVilla;
        String nomV, adresse, descriptionV, descriptionP, superficie;
        float caution;


        if(curseur.getCount()>0){
            curseur.moveToFirst();
            idV = curseur.getInt(0);
            nomV = curseur.getString(1);
            Log.d("nom villa","nom recup"+nomV);
            adresse = curseur.getString(2);
            descriptionV = curseur.getString(3);
            descriptionP = curseur.getString(4);
            superficie = curseur.getString(5);
            anneesConstruction = curseur.getInt(6);
            caution = curseur.getFloat(7);
            idTypeVilla = curseur.getInt(8);
            uneVilla=new Villa(idV, nomV, adresse, descriptionV, descriptionP, superficie,
                    anneesConstruction, caution, idTypeVilla);
            Log.d("villa recup","nom"+uneVilla.getNom());
        }
        return uneVilla;
    }

    // Fonction qui ajoute une villa dans la BDD
    public long addVilla(Villa uneVilla) {
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();

        value.put("id", uneVilla.getId());
        value.put("nom", uneVilla.getNom());
        value.put("adresse", uneVilla.getAdresse());
        value.put("descriptionV", uneVilla.getDescriptionV());
        value.put("descriptionP", uneVilla.getDescriptionP());
        value.put("superficie", uneVilla.getSuperficie());
        value.put("anneesConstru", uneVilla.getAnneeConstru());
        value.put("caution", uneVilla.getCaution());
        value.put("idTypeVilla", uneVilla.getIdTypeVilla());

        ret = bd.insert("villa", null, value);
        Log.d("ajout", "ok");
        return ret;
    }

    // Fonction qui supprime une villa de la BDD
    public long supprimerVilla (Villa uneVilla){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "id =" + uneVilla.getId() + "";
        Log.d("villa supprimer", condition);
        ret = bd.delete("villa", condition, null);
        return ret;
    }

    //Fonction qui récupère une réservation en fonction de l'id récupéré
    public Villa getVilla(int id) {
        Villa laVilla = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from villa where id='"
                + id + "';", null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            laVilla = new Villa(id, curseur.getString(1), curseur.getString(2),
                    curseur.getString(3), curseur.getString(4), curseur.getString(5),
                    curseur.getInt(6), curseur.getFloat(7), curseur.getInt(8));
            Log.d("******", "*********" + laVilla.toString());
        }
        return laVilla;
    }

    // Fonction qui modifie une villa de la BDD
    public int modifierVilla (Villa newVilla,Villa uneVilla){
        int ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put("nom", newVilla.getNom());
        value.put("adresse", newVilla.getAdresse());
        value.put("descriptionV", newVilla.getDescriptionV());
        value.put("descriptionP", newVilla.getDescriptionP());
        value.put("superficie", newVilla.getSuperficie());
        value.put("anneesConstru", newVilla.getAnneeConstru());
        value.put("caution", newVilla.getCaution());
        String condition="id = "+uneVilla.getId();
        ret = bd.update("villa", value,condition,null);
        return ret;
    }

    /*
        Fonction qui récupère un curseur et implémente les informations liées aux villas en fonction
        du contenu du curseur
        Retourne une collection de villas
    */
    private ArrayList<Villa> cursorToVillaArrayList (Cursor curseur){
        ArrayList<Villa> listeVilla = new ArrayList<Villa>();
        int id;
        String nom;
        String adresse;
        String descriptionV;
        String descriptionP;
        String superficie;
        int anneeConstru;
        float caution;
        int idTypeVilla;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            id = curseur.getInt(0);
            nom = curseur.getString(1);
            adresse = curseur.getString(2);
            descriptionV = curseur.getString(3);
            descriptionP = curseur.getString(4);
            superficie = curseur.getString(5);
            anneeConstru = curseur.getInt(6);
            caution = curseur.getFloat(7);
            idTypeVilla = curseur.getInt(8);
            listeVilla.add(new Villa(id, nom, adresse, descriptionV, descriptionP, superficie,
                    anneeConstru, caution, idTypeVilla));
            curseur.moveToNext();
        }
        return listeVilla;
    }
}