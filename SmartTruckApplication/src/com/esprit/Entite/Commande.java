/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import java.util.Date;

/**
 *
 * @author aa
 */
public class Commande {
    private String num_commande;
    private Date date_commande;
    private Date date_livraison;

    public Date getDate_livraison() {
        return date_livraison;
    }
    private float montant;
        private String type;

    private Utilisateur user;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Commande(String num_commande, Date date_commande, float montant, String type, Utilisateur user) {
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.montant = montant;
        this.type = type;
        this.user = user;
    }

    public Commande(String num_commande, Date date_commande, float montant, String type, Date date_livraison) {
        this.num_commande = num_commande;
        this.date_commande = date_commande;
        this.montant = montant;
        this.type = type;
        this.date_livraison = date_livraison;
    }

    

    public String getNum_commande() {
        return num_commande;
    }

    public void setNum_commande(String num_commande) {
        this.num_commande = num_commande;
    }

    public Date getDate_commande() {
        return date_commande;
    }

    public void setDate_commande(Date date_commande) {
        this.date_commande = date_commande;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    @Override
    public String toString() {
        return "Commande{" + "num_commande=" + num_commande + ", date_commande=" + date_commande + ", montant=" + montant + '}';
    }
    
}
