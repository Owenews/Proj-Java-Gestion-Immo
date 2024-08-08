package com.example.gestionimmobilier.modele;

public class Reservation {
    private int id;
    private String dateArrivee;
    private String dateDepart;
    private int nbAdultes;
    private int nbEnfants;
    private String dateReservation;
    private boolean optionMenage;
    private float montantR;
    private int idVilla;
    private int idLocataires;

    //Constructeur
    public Reservation(int id, String dateArrivee, String dateDepart, int nbAdultes, int nbEnfants,
                       String dateReservation, boolean optionMenage, float montantR, int idVilla, int idLocataires) {
        this.id = id;
        this.dateArrivee = dateArrivee;
        this.dateDepart = dateDepart;
        this.nbAdultes = nbAdultes;
        this.nbEnfants = nbEnfants;
        this.dateReservation = dateReservation;
        this.optionMenage = optionMenage;
        this.montantR = montantR;
        this.idVilla = idVilla;
        this.idLocataires = idLocataires;
    }

    //Getter et Setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getDateArrivee() { return dateArrivee; }

    public void setDateArrivee(String dateArrivee) { this.dateArrivee = dateArrivee; }

    public String getDateDepart() { return dateDepart; }

    public void setDateDepart(String dateDepart) { this.dateDepart = dateDepart; }

    public int getNbAdultes() { return nbAdultes; }

    public void setNbAdultes(int nbAdultes) { this.nbAdultes = nbAdultes; }

    public int getNbEnfants() { return nbEnfants; }

    public void setNbEnfants(int nbEnfants) { this.nbEnfants = nbEnfants;  }

    public String getDateReservation() { return dateReservation; }

    public void setDateReservation(String dateReservation) { this.dateReservation = dateReservation; }

    public boolean getOptionMenage() { return optionMenage; }

    public void setOptionMenage(boolean optionMenage) { this.optionMenage = optionMenage; }

    public float getMontantR() { return montantR; }

    public void setMontantR(float montantR) { this.montantR = montantR; }

    public int getIdVilla() { return idVilla; }

    public void setIdVilla(int idVilla) { this.idVilla = idVilla; }

    public int getIdLocataires() { return idLocataires; }

    public void setIdLocataires(int idLocataires) { this.idLocataires = idLocataires; }

    @Override
    public String toString() {
        return  " nbAdultes : " + nbAdultes +
                "\n nbEnfants : " + nbEnfants +
                "\n dateReservation : " + dateReservation + "";
    }

}
