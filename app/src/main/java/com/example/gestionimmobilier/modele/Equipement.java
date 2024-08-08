package com.example.gestionimmobilier.modele;

public class Equipement {
    private int id;
    private String nom;
    private String description;
    private float caution;

    //Constructeur
    public Equipement(int id, String nom, String description, float caution) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.caution = caution;
    }

    public Equipement(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    //Getter et Setter
    public int getId() {return id;}
    public void setId(int id) {this.id = id;}
    public String getNom() {return nom;}
    public void setNom(String nom) {this.nom = nom;}
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    public float getCaution() {return caution;}
    public void setCaution(float caution) { this.caution = caution; }
    @Override
    public String toString() {
        return "Un/Une " + nom + ", \n" +
                "description : " + description + ",\n" +
                "Caution de " + caution + " euros." ;
    }
}