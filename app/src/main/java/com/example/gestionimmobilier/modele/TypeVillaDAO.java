package com.example.gestionimmobilier.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class TypeVillaDAO {

    // Classe permettant d'accéder aux données des typeVillas de la BDD
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public TypeVillaDAO(Context ct) {
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    //Fonction qui récupère une collection de typeVilla
    public ArrayList<TypeVilla> getTypeVilla(){
        Cursor curseur;
        String req = "select * from TypeVilla";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToTypeVillaArrayList(curseur);
    }

    /*
       Fonction qui récupère un id de la table TypeVilla et affiche les informations liées aux
        réservation en fonction de l'id du TypeVilla chosi
       Retourne une collection de villas
    */
    public ArrayList<Villa> getTypeVillaStudio(int idV) {
        Cursor curseur;
        String req = "select * from villa where idTypeVilla =  '" + idV + "'";
        Log.d("msg","requete" + req);
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
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
            idTypeVilla= curseur.getInt(8);
            listeVilla.add(new Villa(id,nom, adresse,descriptionV,descriptionP,superficie,
                    anneeConstru,caution,idTypeVilla));
            curseur.moveToNext();
        }
        return listeVilla;
    }

    /*
        Fonction qui récupère un curseur et implémente les informations liées aux typeVilla en
        fonction du contenu du curseur
        Retourne une collection des tydeVilla
    */
    private ArrayList<TypeVilla> cursorToTypeVillaArrayList(Cursor curseur){
        ArrayList<TypeVilla> listeTypeVilla = new ArrayList<TypeVilla>();
        int id;
        int nbChambresPossible;
        String nom;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = curseur.getInt(0);
            nbChambresPossible = curseur.getInt(1);
            nom = curseur.getString(2);
            listeTypeVilla.add(new TypeVilla(id,nbChambresPossible,nom));
            curseur.moveToNext();
        }
        return listeTypeVilla;
    }
}