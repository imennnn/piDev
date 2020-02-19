/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Entite;

import javafx.scene.control.Button;

/**
 *
 * @author aa
 */
public class LigneCommande {
    private int qte;
    private Article article;
    private Commande cmd;
        private String ref_article;
        private Button btnSupprimer;

    public Button getBtnSupprimer() {
        return btnSupprimer;
    }

    public void setBtn(Button btnSupprimer) {
        this.btnSupprimer = btnSupprimer;
    }

    public String getRef_article() {
        return ref_article;
    }

    public void setRef_article(String ref_article) {
        this.ref_article = ref_article;
    }


    public LigneCommande(int qte, String ref_article) {
        this.qte = qte;
        this.ref_article = ref_article;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

        

    

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public Commande getCmd() {
        return cmd;
    }

    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }

    public LigneCommande(int qte, Article article, Commande cmd) {
        this.qte = qte;
        this.article = article;
        this.cmd = cmd;
    }

    public LigneCommande(int qte,  Commande cmd) {
        this.qte = qte;
        this.cmd = cmd;
    }

   public LigneCommande(int qte, Article article ){
        this.qte = qte;
        this.article = article;
        
    }

    public LigneCommande(int qte, String ref_article, Button btnSupprimer) {
        this.qte = qte;
        this.ref_article = ref_article;
        this.btnSupprimer = new Button("supprimer");
    }

   

    
    
    
    
}
