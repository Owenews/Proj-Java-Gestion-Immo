package com.example.gestionimmobilier.modele;

public class Role {
    private int id;
    private String type;

    //Constructeur
    public Role(int id, String type) {
        this.id = id;
        this.type = type;
    }

    //Getter et Setter
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    @Override
    public String toString() { return type; }
}
