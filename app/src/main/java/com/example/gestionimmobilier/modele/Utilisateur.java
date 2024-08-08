package com.example.gestionimmobilier.modele;

public class Utilisateur {
    private String id;
    private String login;
    private String motDePasse;
    private int idRoles;

    //Constructeur
    public Utilisateur(String id, String login, String motDePasse, int idRoles) {
        this.id = id;
        this.login = login;
        this.motDePasse = motDePasse;
        this.idRoles = idRoles;
    }

    //Getter et Setter
    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getLogin() { return login; }

    public void setLogin(String login) { this.login = login; }

    public String getMotDePasse() { return motDePasse; }

    public void setMotDePasse(String motDePasse) { this.motDePasse = motDePasse; }

    public int getIdRoles() { return idRoles; }

    public void setIdRoles(int idRoles) { this.idRoles = idRoles; }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", " +
                "login=" + login + ", " +
                "motDePasse=" + motDePasse +
                ", idRoles=" + idRoles + '}';
    }
}
