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
public class Palette {
    private int num_lot;
    private int qte;
    private Date date_expiration;
    private Article article;
    private String ref, codeEmp;

    public Palette(int num_lot, int qte, Date date_expiration, String ref, String codeEmp) {
        this.num_lot = num_lot;
        this.qte = qte;
        this.date_expiration = date_expiration;
        this.ref = ref;
        this.codeEmp = codeEmp;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getCodeEmp() {
        return codeEmp;
    }

    public void setCodeEmp(String codeEmp) {
        this.codeEmp = codeEmp;
    }

    public Palette(int num_lot, int qte, Date date_expiration, Article article, Emplacement emplacement) {
        this.num_lot = num_lot;
        this.qte = qte;
        this.date_expiration = date_expiration;
        this.article = article;
        this.emplacement = emplacement;
    }
    public Palette( int qte, Date date_expiration, Article article, Emplacement emplacement) {
        this.qte = qte;
        this.date_expiration = date_expiration;
        this.article = article;
        this.emplacement = emplacement;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }
    private Emplacement emplacement;


    public int getNum_lot() {
        return num_lot;
    }

    public void setNum_lot(int num_lot) {
        this.num_lot = num_lot;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Date getDate_expiration() {
        return date_expiration;
    }

    public void setDate_expiration(Date date_expiration) {
        this.date_expiration = date_expiration;
    }

   

    @Override
    public String toString() {
        return "Stock{" + "num_lot=" + num_lot + ", qte=" + qte + ", date_expiration=" + date_expiration +  '}';
    }

    
    public Palette(int num_lot, int qte, Date date_expiration) {
        this.num_lot = num_lot;
        this.qte = qte;
        this.date_expiration = date_expiration;
    }
    
     public Palette( int qte, Date date_expiration) {
       
        this.qte = qte;
        this.date_expiration = date_expiration;
        
    }
    
}
