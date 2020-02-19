/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

/**
 *
 * @author aa
 */
public class Fournisseur {
    private int id_fournisseur;
    private String nom_ste;
    private String mail;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public String getNom_ste() {
        return nom_ste;
    }

    public void setNom_ste(String nom_ste) {
        this.nom_ste = nom_ste;
    }

    public Fournisseur(int id_fournisseur, String nom_ste) {
        this.id_fournisseur = id_fournisseur;
        this.nom_ste = nom_ste;
    }

    public Fournisseur() {
    }
    
    
    
}
