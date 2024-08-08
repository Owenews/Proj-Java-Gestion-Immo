package com.example.gestionimmobilier.modele;

public class Equiper {
    int idVilla;
    int idEquipements;
    String etat;

    //Constructeur
    public Equiper(int idVilla, int idEquipements, String etat) {
        this.idVilla = idVilla;
        this.idEquipements = idEquipements;
        this.etat = etat;
    }

    public Equiper(int idVilla, int idEquipements) {
        this.idVilla = idVilla;
        this.idEquipements = idEquipements;
    }

    //Getter et Setter
    public int getIdVilla() { return idVilla; }

    public void setIdVilla(int idVilla) { this.idVilla = idVilla; }

    public int getIdEquipements() { return idEquipements; }

    public void setIdEquipements(int idEquipements) { this.idEquipements = idEquipements; }

    public String getEtat() { return etat; }

    public void setEtat(String etat) { this.etat = etat; }

    @Override
    public String toString() {
        return "Equiper{" + "idVilla=" + idVilla +
                ", idEquipements=" + idEquipements +
                ", etat=" + etat + '}';
    }
}
