package com.studiobeu.swapprototype.model;

public class Reseau {

    protected int type;
    protected String titre;
    protected String pseudo;
    protected String lien;

    public Reseau(int i,String s){
        this.type = i;
        this.titre = s;
    }

    public Reseau(int i,String s,String p){
        this.type = i;
        this.titre = s;
        this.pseudo = p;
    }

    public Reseau(int i,String t,String s, String a){
        this.type = i;
        this.titre = t;
        this.pseudo = s;
        this.lien = a;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getName() {
        return pseudo;
    }

    public void setName(String name) {
        this.pseudo = name;
    }

    public String getAdress() {
        return lien;
    }

    public void setAdress(String adress) {
        this.lien = adress;
    }

    public String getTitre() {
        return titre;
    }
}
