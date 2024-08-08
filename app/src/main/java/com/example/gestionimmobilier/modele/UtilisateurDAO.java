package com.example.gestionimmobilier.modele;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

public class UtilisateurDAO {

    // Classe permettant d'accéder aux données des villas de la BDD
    private static String base = "gestionimmo";
    private static int version = 1;
    BD_SQLiteOpenHelper accesBD;

    public UtilisateurDAO(Context ct){
        accesBD = new BD_SQLiteOpenHelper(ct, base, null, version);
    }

    //Fonction qui récupère une collection d'utilisateur
    public ArrayList<Utilisateur> recupUtilisateur(){
        Cursor curseur;
        String req = "select * from utilisateur";
        curseur = accesBD.getReadableDatabase().rawQuery(req,null);
        ArrayList<Utilisateur> listeUtilisateur = new ArrayList<Utilisateur>();
        String id,login,mdp;
        int idRoles;

        curseur.moveToFirst();
        while (!curseur.isAfterLast()){
            id = curseur.getString(0);
            login = curseur.getString(1);
            mdp = curseur.getString(2);
            idRoles = curseur.getInt(3);
            listeUtilisateur.add(new Utilisateur(id,login,mdp,idRoles));
            curseur.moveToNext();
        }
        return listeUtilisateur;
    }


    // Fonction qui permet de se connecter en fonction de l'identifiant et du mot de passe
    public Utilisateur seConnecter(String unLogin,String unMotDePasse){
        Log.d("login",unLogin+" "+unMotDePasse);
        ArrayList<Utilisateur> lesUtilisateurs=recupUtilisateur();
        int i=0;
        Boolean finBoucle=false;
        while ( i < lesUtilisateurs.size() &&  !finBoucle ){
            Log.d("testboucle",""+lesUtilisateurs.get(i).getLogin()+"");
            if(lesUtilisateurs.get(i).getLogin().equals(unLogin) && lesUtilisateurs.get(i).getMotDePasse().equals(unMotDePasse)){
                finBoucle=true;
            }else{
                i=i+1;
            }
        }
        if(finBoucle){ Log.d("utilisateur","*******"+lesUtilisateurs.get(i));
            return lesUtilisateurs.get(i);
        }else{
            return null;
        }
    }
}

