/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.Service;

import com.esprit.Entite.Article;
import com.esprit.Entite.Commande;
import com.esprit.Entite.CommandeFournisseur;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Palette;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aa
 */
public class ServiceCommande {

    private Connection con;
    private Statement ste;

    public ServiceCommande() {
        con = DataBase.getInstance().getConnection();

    }
    public List<String> getAllFournisseur()
    {
        List<String> fournisseurs = new ArrayList<>();

         try {
            String req = "select nom_ste from Fournisseur";
            PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String nom_ste =(rs.getString("nom_ste"));
                fournisseurs.add(nom_ste);
            }
            


        } catch (SQLException e) {
            e.printStackTrace();
        }
         return fournisseurs;
    }
    
    public List<String> getAllArticles(String nom ) throws SQLException
    {        List<String> articles = new ArrayList<>();

        String req = "select ref_article from Fournisseur,Article where Fournisseur.id_fournisseur=Article.id_fournisseur and Fournisseur.nom_ste='" 
                + nom +"'" ;
         PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              String r=(rs.getString("ref_article"));
              articles.add(r);

            }
            return articles;
    }
    public void ajouterCommande(Commande c) throws SQLException
    {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `SmartTruck`.`Commande` (`num_commande`, `date_commande`, `montant`, `type`, `date_livraison`) VALUES ( '" + c.getNum_commande() + "', '" + c.getDate_commande() + "', '" + c.getMontant() + "', '" + c.getType() + "', '" + c.getDate_livraison() + "');";
        ste.executeUpdate(requeteInsert);
        
    }
          

    public void ajouterLigneCommande(LigneCommande lc) throws SQLException
    {      
        ste = con.createStatement();
        String requete = "INSERT INTO `SmartTruck`.`LigneCommande` (`qte`, `ref_article`, `num_commande`) VALUES ('" + lc.getQte() + "', '" + lc.getArticle().getRef() + "', '" + lc.getCmd().getNum_commande() + "');";
        ste.executeUpdate(requete);
    }
    
    public List<CommandeFournisseur>  afficherCommande() throws SQLException
    {  
                List<CommandeFournisseur> cf = new ArrayList<>();

        ste = con.createStatement();
       String requete =" SELECT LigneCommande.num_commande,LigneCommande.ref_article,qte,Fournisseur.nom_ste,date_commande,montant,commande.date_livraison "
               + "FROM LigneCommande,Commande,Article,Fournisseur"
               + " WHERE LigneCommande.num_commande = Commande.num_commande and "
               + "LigneCommande.ref_article=Article.ref_article AND Article.id_fournisseur=Fournisseur.id_fournisseur ORDER BY num_commande DESC; ";
       
       ResultSet rs = ste.executeQuery(requete);
       while (rs.next()) {
            try {
                String num_commande = rs.getString("num_commande");
                String ref_article = rs.getString("ref_article");
                int qte = rs.getInt("qte");
                String nom_ste = rs.getString("nom_ste");

                String date_commande = rs.getString("date_commande");
                java.util.Date date1=new SimpleDateFormat("yyyy-MM-dd").parse(date_commande);
                java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
                
                float montant= rs.getFloat("montant");
                
                Date date_livraison = rs.getDate("date_livraison");
//                java.util.Date date=new SimpleDateFormat("yyyy-MM-dd").parse(date_livraison);
               // java.sql.Date sqlDate1 = new java.sql.Date(date.getTime());
                
               
                CommandeFournisseur c = new CommandeFournisseur( num_commande,ref_article,qte, nom_ste,sqlDate,montant,date_livraison);
                cf.add(c);
            } catch (ParseException ex) {
                Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       return cf;
    }
    
    public void modifierCommande(Date date,float total,String num,Date date1) throws SQLException
    {
        ste = con.createStatement();
        
       
        String req="UPDATE Commande " + "SET date_commande='"+date+"',montant='"+total+"' ,date_livraison='"+date1+"'"+ "WHERE num_commande='"+num+"'";
     
        ste.executeUpdate(req);
        
    }
    public void DeleteCommande(String num) throws SQLException
    {
        ste = con.createStatement();
         String req="DELETE FROM LigneCommande WHERE num_commande='"+num+"'";
        
         ste.executeUpdate(req);
    }
    public String  getDesignation(String ref) throws SQLException
    { String nom="";
    ste = con.createStatement();
        String req = "select designation from Article where Article.ref_article='" + ref +"'" ;
         
       
            ResultSet rs = ste.executeQuery(req);
            while(rs.next())
            {
                nom=rs.getString("designation");
            }
            
             
            return nom;
            
    }
    public String getMail(String noms) throws SQLException
    {
        String nom="";
    ste = con.createStatement();
        String req = "select mail from Fournisseur,Article where Article.id_fournisseur=Fournisseur.id_fournisseur and Article.ref_article='" + noms +"'" ;
         
       
            ResultSet rs = ste.executeQuery(req);
            while(rs.next())
            {
                nom=rs.getString("mail");
            }
            
             
            return nom;
    }
}

