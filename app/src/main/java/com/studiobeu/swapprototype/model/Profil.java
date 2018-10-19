package com.studiobeu.swapprototype.model;

import java.util.Vector;

public class Profil {

    private Contact myContact;
    private Vector<Contact> carnet;

    public Profil(String name){
        this.myContact = new Contact(0,name);
        this.carnet = new Vector<Contact>();
    }

    public Profil(){
        this.myContact = new Contact(0,"");
        this.carnet = new Vector<Contact>();
    }

    /** ============================= Modification Profil =======================================*/
    public void addContact (Contact contact){
        int id = 1;
        while(this.containID(id)){
            id++;
        }
        contact.setId(id);
        this.carnet.add(contact);
    }

    /** =============================== Manipulation Profil ======================================*/
    public boolean containID(int id){
        boolean contain = false;
        for (Contact c:carnet) {
            if(c.getId() == id){
                contain = false;
            }
        }
        return  contain;
    }

    /** =============================== getter et setter =========================================*/
    public Contact getMyContact() {
        return myContact;
    }

    public Vector<Contact> getCarnet() {
        return carnet;
    }
}
