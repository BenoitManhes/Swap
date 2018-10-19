package com.studiobeu.swapprototype.model;

import java.util.Vector;

public class Contact {

    private int id = -1;
    private String nom;
    private Vector<Reseau> listReseau;

    public Contact (int i, String s){
        this.id = i;
        this.nom = s;
        this.listReseau = new Vector<>();
    }

    public Contact ( String s){
        this.nom = s;
        this.listReseau = new Vector<>();
    }

    /** ============================= Modification Contact =======================================*/
    public void add (Reseau reseau){

    }

    /** =============================== getter et setter =========================================*/
    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Vector<Reseau> getListReseau() {
        return listReseau;
    }
}
