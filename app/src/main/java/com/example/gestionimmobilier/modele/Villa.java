package com.example.gestionimmobilier.modele;

public class Villa {
    private int id;
    private String nom;
    private String adresse;
    private String descriptionV;
    private String descriptionP;
    private String superficie;
    private int anneeConstru;
    private float caution;
    private int idTypeVilla;

    //Constructeur
    public Villa(int id, String nom, String adresse, String descriptionV, String descriptionP, String superficie,
                 int anneeConstru, float caution, int idTypeVilla) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.descriptionV = descriptionV;
        this.descriptionP = descriptionP;
        this.superficie = superficie;
        this.anneeConstru = anneeConstru;
        this.caution = caution;
        this.idTypeVilla = idTypeVilla;
    }

    public Villa(int id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    public Villa(String nom, String adresse, String descriptionV, String descriptionP, String superficie,
                 int anneeConstru, float caution) {
        this.nom = nom;
        this.adresse = adresse;
        this.descriptionV = descriptionV;
        this.descriptionP = descriptionP;
        this.superficie = superficie;
        this.anneeConstru = anneeConstru;
        this.caution = caution;
    }

    //Getter et Setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getAdresse() { return adresse; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getDescriptionV() { return descriptionV; }

    public void setDescriptionV(String descriptionV) { this.descriptionV = descriptionV; }

    public String getDescriptionP() { return descriptionP; }

    public void setDescriptionP(String descriptionP) { this.descriptionP = descriptionP; }

    public String getSuperficie() { return superficie; }

    public void setSuperficie(String superficie) { this.superficie = superficie; }

    public int getAnneeConstru() { return anneeConstru; }

    public void setAnneeConstru(int anneeConstru) { this.anneeConstru = anneeConstru; }

    public float getCaution() { return caution; }

    public void setCaution(float caution) { this.caution = caution; }

    public int getIdTypeVilla() { return idTypeVilla; }

    public void setIdTypeVilla(int idTypeVilla) { this.idTypeVilla = idTypeVilla; }

    @Override
    public String toString() { return  nom ; }
}
    /*public String toString() {
        return  "nom : " + nom + "\n" +
                "adresse : " + adresse + "\n" +
                "superficie : " + superficie + "";

    }*/

