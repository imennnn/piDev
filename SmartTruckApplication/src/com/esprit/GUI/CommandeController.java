/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import com.esprit.Entite.Article;
import com.esprit.Entite.Commande;
import com.esprit.Entite.CommandeFournisseur;
import com.esprit.Entite.LigneCommande;
import com.esprit.Entite.Palette;
import com.esprit.Service.ServiceCommande;

import com.esprit.Utils.DataBase;
import com.sun.prism.impl.Disposer.Record;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect.Type;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.DateStringConverter;
import javafx.util.converter.FloatStringConverter;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.LocalDateStringConverter;

/**
 * FXML Controller class
 *
 * @author aa
 */
public class CommandeController implements Initializable {

    @FXML
    private TextField txtNum;
    @FXML
    private TextField txtTotal;
    @FXML
    private TextField txtQte;
    @FXML
    private ComboBox<String> comboRef;
    @FXML
    private DatePicker date;
    @FXML
    private Button btnAjouter;
    private Connection con;
    private Statement ste;
    private Button btn;
    ServiceCommande s = new ServiceCommande();

    final ObservableList<String> options = FXCollections.observableArrayList();
    final ObservableList<String> optionsArticle = FXCollections.observableArrayList();

    ObservableList<LigneCommande> oblist = FXCollections.observableArrayList();
    ObservableList<CommandeFournisseur> obliste = FXCollections.observableArrayList();

    @FXML
    private TableView<LigneCommande> tableArticle;
    @FXML
    private TableColumn<LigneCommande, String> cellReference;
    @FXML
    private TableColumn<LigneCommande, Integer> cellQuantite;
    @FXML
    private Button btnArticle;
    @FXML
    private ComboBox<String> comboFrs;

    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat(
            DATE_PATTERN);
    @FXML
    private Button btnDelete;
    @FXML
    private TextField txtDesignation;
    @FXML
    private DatePicker dateCommande;
    @FXML
    private Label errorNum;
    @FXML
    private Label errorTotal;
    @FXML
    private Label errorDateC;
    @FXML
    private Label errorDateL;
    @FXML
    private Label errorFrs;
    @FXML
    private Label errorArticle;
    @FXML
    private Label errorQte;
    String msg="Ce champs est obligatoire";
    @FXML
    private Button idGP;

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fillComboFournisseur();

        new AutoCompleteComboBoxListener<>(comboFrs);
       

       

    }

    public void fillComboFournisseur() {

        List<String> fournisseurs = s.getAllFournisseur();
        for (int i = 0; i < fournisseurs.size(); i++) {
            options.add(fournisseurs.get(i));
        }
        comboFrs.setItems(options);

    }

    @FXML
    public void fillComboArticle() throws SQLException {

        List<String> articles = s.getAllArticles(comboFrs.getValue());
        for (int i = 0; i < articles.size(); i++) {
            optionsArticle.add(articles.get(i));
        }
        comboRef.setItems(optionsArticle);

    }

    @FXML
    private void AjouterCommande(ActionEvent event) throws SQLException {
      if(  controlSaisie(txtNum,errorNum,msg) &
       controlSaisie(txtQte,errorQte,msg) &
        controlSaisie(txtTotal,errorTotal,msg) &
        controlSaisieDate(dateCommande,errorDateC,msg) &
        controlSaisieDate(date,errorDateL,msg) &
        controlSaisieCombo(comboFrs,errorDateL,msg)&
        controlSaisieCombo(comboRef,errorDateL,msg))
      
      {btnAjouter.setDisable(false);
          Commande c = new Commande(txtNum.getText(), Date.valueOf(dateCommande.getValue()), Float.valueOf(txtTotal.getText()),
                "Commande entree", Date.valueOf(date.getValue()));
        s.ajouterCommande(c);

        for (LigneCommande LigneCommande : tableArticle.getItems()) {
            Article A = new Article(LigneCommande.getRef_article());
            LigneCommande lc = new LigneCommande(LigneCommande.getQte(), A, c);
            s.ajouterLigneCommande(lc);

        }
        Alert alert=new Alert(AlertType.INFORMATION);
        alert.setTitle("Ajout avec succèe ");
        alert.setHeaderText(null);
        alert.setContentText("Commande ajoutée avec succèe");
        alert.showAndWait(); 
      }
       
        

    }

    @FXML
    private void AjouterArticle(ActionEvent event) {

        LigneCommande lc = new LigneCommande(Integer.parseInt(txtQte.getText()), comboRef.getValue());

        oblist.add(lc);
        System.out.println(comboRef.getValue());

        cellQuantite.setCellValueFactory(new PropertyValueFactory<>("qte"));
        cellReference.setCellValueFactory(new PropertyValueFactory<>("ref_article"));

        tableArticle.setItems(oblist);

        tableArticle.setEditable(true);
        cellQuantite.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

    }

    @FXML
    private void supprimerArticle(ActionEvent event) {
        
        Alert alerts=new Alert(AlertType.CONFIRMATION);
        alerts.setTitle("Confirmer la suppression");
        alerts.setHeaderText(null);
        alerts.setContentText("Voulez vous vraiment supprimer cet article?");
        Optional <ButtonType> result=alerts.showAndWait();
        if (result.get()==ButtonType.OK){
                    tableArticle.getItems().remove(tableArticle.getSelectionModel().getSelectedItem());

        }

    }

    @FXML
    private void fillDesignation(ActionEvent event) throws SQLException {
        System.out.println("hhh");
        System.out.println(comboRef.getSelectionModel().getSelectedItem());
        String des = s.getDesignation(comboRef.getSelectionModel().getSelectedItem());
        System.out.println(s);
        txtDesignation.setText(des);
        txtDesignation.setEditable(false);
    }
    
    private boolean controlSaisie(TextField tf,Label lb,String msg)
    {  boolean b=true;
        if(tf.getText().length()==0)
        {
            lb.setText(msg);
          
            b=false;
        }
        
        return b;
    }
   private boolean controlSaisieDate(DatePicker dp,Label lb,String msg)
    {boolean b=true;
    LocalDate value= dp.getValue();
        if( value==null)
        {
            lb.setText(msg);
         b=false;
        }
       
        return b;
    }
   private boolean controlSaisieCombo(ComboBox cmb ,Label lb,String msg)
    {boolean b=true;
        
        if( cmb.getSelectionModel().isEmpty())
        {
            lb.setText(msg);
           b=false;
        }
      
        return b;
    }

    @FXML
    private void afficher(MouseEvent event) throws IOException {
        
        
            FXMLLoader loader= new FXMLLoader(getClass().getResource("AfficheCommande.fxml"));
           
            Parent root= loader.load();
             Scene scene= new Scene(root);
             Stage stage= new Stage(StageStyle.DECORATED);
             stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        
    }

    @FXML
    private void load(MouseEvent event) throws IOException {
         FXMLLoader loader= new FXMLLoader(getClass().getResource("AddStock.fxml"));
           
            Parent root= loader.load();
             Scene scene= new Scene(root);
             Stage stage= new Stage(StageStyle.DECORATED);
             stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
        
    }
    
    }


