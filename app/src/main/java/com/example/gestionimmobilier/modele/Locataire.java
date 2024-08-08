package com.example.gestionimmobilier.modele;

public class Locataire {
    private int id;
    private String nom;
    private String prenom;
    private String adresse;
    private String numTel;
    private String email;
    private String commentaire;

    //Constructeur
    public Locataire(int id, String nom, String prenom, String adresse, String numTel, String email, String commentaire) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
        this.commentaire = commentaire;
    }

    public Locataire(String nom, String prenom, String adresse, String numTel, String email, String commentaire) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numTel = numTel;
        this.email = email;
        this.commentaire = commentaire;
    }

    //Getter et Setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getNom() { return nom; }

    public void setNom(String nom) { this.nom = nom; }

    public String getPrenom() { return prenom; }

    public void setPrenom(String prenom) { this.prenom = prenom; }

    public String getAdresse() { return adresse; }

    public void setAdresse(String adresse) { this.adresse = adresse; }

    public String getNumTel() { return numTel; }

    public void setNumTel(String numTel) { this.numTel = numTel; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getCommentaire() { return commentaire; }

    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }

    @Override
    public String toString() { return nom + "  " + prenom  ; }
}
    /*public String toString() {
        return " nom : " + nom +
               "\n prenom : " + prenom +
               "\n adresse : " + adresse + "";
    }*/

