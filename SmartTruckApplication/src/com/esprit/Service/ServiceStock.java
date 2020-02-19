package com.esprit.Service;

import com.esprit.Entite.Palette;
import com.esprit.GUI.AddStockController;
import com.esprit.IService.IService;
import com.esprit.Utils.DataBase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aa
 */
public class ServiceStock implements IService<Palette> {

    private Connection con;
    private Statement ste;

      UUID uniqueKey = UUID.randomUUID();

    public ServiceStock() {
        con = DataBase.getInstance().getConnection();

    }

    
    @Override
    public void ajouter(Palette t) throws SQLException {

        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `PIDev`.`Palette` (`num_lot`, `qte`, `date_expiration`) VALUES (NULL , '" + t.getNum_lot() + "', '" + t.getDate_expiration() + "');";
        ste.executeUpdate(requeteInsert);

    }

    public void ajouter2(Palette p) throws SQLException {

        ste = con.createStatement();
       
        String requeteInsert = "INSERT INTO `SmartTruck`.`Palette` (`num_lot`, `qte`, `date_expiration`, `ref_article`, `codeEmp`) VALUES (NULL , '" + p.getQte() + "', '" + p.getDate_expiration() + "', '" + p.getArticle().getRef() + "', '" + p.getEmplacement().getCodeEmp() + "');";
        ste.executeUpdate(requeteInsert);

    }
   
    public void delete(int num) throws SQLException {
        ste = con.createStatement();
         String req="DELETE FROM Palette WHERE num_lot='"+num+"'";
        
         ste.executeUpdate(req);

    }

   
    public void update( int qte,int num) throws SQLException {
        ste = con.createStatement();
        
       
        String req="UPDATE Palette " + "SET qte='"+qte+"'"+ "WHERE num_lot='"+num+"'";;
     
        ste.executeUpdate(req);


    }

    @Override
    public List<Palette> readAll() throws SQLException {
        List<Palette> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from Palette");
        while (rs.next()) {
            try {
                int id = rs.getInt(1);
                int qte = rs.getInt(2);
                String date_expiration = rs.getString(3);
                Date date1=new SimpleDateFormat("dd-MM-yyyy").parse(date_expiration);
                java.sql.Date sqlDate = new java.sql.Date(date1.getTime());
                
                 String ref_article= rs.getString(4);
                String code_emp = rs.getString(5);

                Palette p = new Palette( id ,qte, sqlDate,ref_article,code_emp);
                arr.add(p);
            } catch (ParseException ex) {
                Logger.getLogger(ServiceStock.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return arr;
    }
     public List<String> getAllArticles(String nom ) throws SQLException
    {        List<String> articles = new ArrayList<>();

        String req = "select ref_article from Article "  ;
         PreparedStatement ps = con.prepareStatement(req);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
              String r=(rs.getString("ref_article"));
              articles.add(r);

            }
            return articles;
    }

    @Override
    public boolean update(Palette t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Palette t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   

}
