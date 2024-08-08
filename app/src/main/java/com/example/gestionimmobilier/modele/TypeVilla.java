package com.example.gestionimmobilier.modele;

public class TypeVilla {
    private int id;
    private int nbChambresPossible;
    private String nom;

    //Constructeur
    public TypeVilla(int id, int nbChambresPossible, String nom) {
        this.id = id;
        this.nbChambresPossible = nbChambresPossible;
        this.nom = nom;
    }

    //Getter et Setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public int getNbChambresPossible() { return nbChambresPossible; }

    public void setNbChambresPossible(int nbChambresPossible) { this.nbChambresPossible = nbChambresPossible; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    @Override
    public String toString() {
        return nom;
    }
}
