package com.example.gestionimmobilier.modele;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import java.util.ArrayList;

    public class ReservationDAO {

    // Classe permettant d'accéder aux données des réservations de la BDD
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public ReservationDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    /* Fonctoin qui récupère l'id le plus grand des réservations et lui ajoute +1 dans la BDD
       Retourne l'id le plus petit disponible pour les réservations
     */
    public int dernierId() {
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select max(id)+1 from reservations;",
                null);
        curseur.moveToFirst();
        int lastId = curseur.getInt(0);
        return lastId;
    }

    // Fonction qui ajoute une réservation dans la BDD
    public long addReservation(Reservation uneReservation){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();

        ContentValues value = new ContentValues();
        value.put("id",uneReservation.getId());
        value.put("dateArrivee", uneReservation.getDateArrivee());
        value.put("dateDepart",uneReservation.getDateDepart());
        value.put("nbAdultes", uneReservation.getNbAdultes());
        value.put("nbEnfants",uneReservation.getNbEnfants());
        value.put("dateReservation", uneReservation.getDateReservation());
        value.put("optionMenage", uneReservation.getOptionMenage());
        value.put("montantR", uneReservation.getMontantR());
        value.put("idVilla", uneReservation.getIdVilla());
        value.put("idLocataires", uneReservation.getIdLocataires());
        // Exécution de l'ordre SQL d'insertion
        ret = bd.insert("reservations", null, value);

        return ret;
    }


    //Fonction qui récupère une collection de réservations
    public ArrayList<Reservation> getLesReservations(){
        Cursor curseur;
        String req = "select * from reservations";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        return cursorToReservationArrayList(curseur);
    }

    // Fonction qui supprime une réservation de la BDD
    public long supprimerReservation(Reservation uneReservation){
        long ret;
        SQLiteDatabase bd = accesBD.getWritableDatabase();
        String condition = "nbAdultes ='"+uneReservation.getNbAdultes()+
                "' AND nbEnfants='"+uneReservation.getNbEnfants()+"'";
        Log.d("réservation supprimer", condition);
        //Exécution de l'ordre SQL de suppréssion
        ret = bd.delete("reservations", condition ,null);
        return ret;
    }

    //Fonction qui récupère une réservation en fonction de l'id récupéré
    public Reservation getReservations(int idR){
        Reservation laReservation = null;
        Cursor curseur;
        curseur = accesBD.getReadableDatabase().rawQuery("select * from reservations where id="
                +idR+";",null);
        if (curseur.getCount() > 0) {
            curseur.moveToFirst();
            laReservation = new Reservation(idR, curseur.getString(1), curseur.getString(2),
                    curseur.getInt(3), curseur.getInt(4), curseur.getString(5),
                    Boolean.parseBoolean(curseur.getString(6)), curseur.getFloat(7),
                    curseur.getInt(8), curseur.getInt(9));
        }
        return laReservation;
    }

    // Fonction qui modifie une réservation de la BDD
    public int modifierReservation(Reservation nvReservation, Reservation ancReservation) {
        int ret;

        SQLiteDatabase bd = accesBD.getWritableDatabase();
        ContentValues value = new ContentValues();

        value.put("dateArrivee", nvReservation.getDateArrivee());
        value.put("dateDepart", nvReservation.getDateDepart());
        value.put("nbAdultes", nvReservation.getNbAdultes());
        value.put("nbEnfants", nvReservation.getNbEnfants());
        value.put("dateReservation", nvReservation.getDateReservation());
        value.put("optionMenage", nvReservation.getOptionMenage());
        value.put("montantR", nvReservation.getMontantR());
        value.put("idVilla", nvReservation.getIdVilla());
        value.put("idLocataires", nvReservation.getIdLocataires());

        String condition = "id =" + ancReservation.getId()+ "";

        //Exécution de l'ordre SQL de modification
        ret = bd.update("reservations", value, condition, null);
        return ret;

    }

    /*
       Fonction qui récupère un id de la table Villa et affiche les informations liées aux
       réservation en fonction de l'id de la villa chosi
       Retourne une collection de réservation
    */
    public ArrayList<Reservation> getVillaFiltre(int idR) {
        Cursor curseur;
        String req = "select * from reservations where idVilla =  " + idR + "";
        Log.d("msg","requete " + req);
        curseur = accesBD.getReadableDatabase().rawQuery(req, null);
        ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();

        int id;
        String dateArrivee;
        String dateDepart;
        int nbAdultes;
        int nbEnfants;
        String dateReservation;
        boolean optionMenage;
        float montantR;
        int idVilla;
        int idLocataires;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()) {
            id = curseur.getInt(0);
            dateArrivee = curseur.getString(1);
            dateDepart = curseur.getString(2);
            nbAdultes = curseur.getInt(3);
            nbEnfants = curseur.getInt(4);
            dateReservation = curseur.getString(5);
            optionMenage = Boolean.parseBoolean(curseur.getString(6));
            montantR = curseur.getFloat(7);
            idVilla = curseur.getInt(8);
            idLocataires = curseur.getInt(9);
            dateReservation = curseur.getString(5);
            listeReservation.add(new Reservation(id, dateArrivee, dateDepart, nbAdultes,
                    nbEnfants, dateReservation, optionMenage, montantR, idVilla, idLocataires));
            curseur.moveToNext();
        }
        return listeReservation;
    }


    /*
        Fonction qui récupère un curseur et implémente les informations liées aux équipements en
        fonction du contenu du curseur
        Retourne une collection de réservations
    */
    private ArrayList<Reservation> cursorToReservationArrayList(Cursor curseur){
        ArrayList<Reservation> listeReservation = new ArrayList<Reservation>();
        int id;
        String dateArrivee;
        String dateDepart;
        int nbAdultes;
        int nbEnfants;
        String dateReservation;
        boolean optionMenage;
        float montantR;
        int idVilla;
        int idLocataires;


        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = curseur.getInt(0);
            dateArrivee = curseur.getString(1);
            dateDepart = curseur.getString(2);
            nbAdultes = curseur.getInt(3);
            nbEnfants = curseur.getInt(4);
            dateReservation = curseur.getString(5);
            optionMenage = Boolean.parseBoolean(curseur.getString(6));
            montantR = curseur.getFloat(7);
            idVilla = curseur.getInt(8);
            idLocataires = curseur.getInt(9);
            listeReservation.add(new Reservation(id, dateArrivee, dateDepart, nbAdultes, nbEnfants,
                    dateReservation, optionMenage, montantR, idVilla, idLocataires));
            curseur.moveToNext();
        }

        return listeReservation;
    }

}

