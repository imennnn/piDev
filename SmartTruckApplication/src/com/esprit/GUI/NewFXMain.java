/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esprit.GUI;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author aa
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
       
   
                try {
            // Parent root= new FXMLLoader().load(getClass().getResource("AfficheCommande.fxml"));
                       Parent root= new FXMLLoader().load(getClass().getResource("Commande.fxml"));
                               //  Parent root= new FXMLLoader().load(getClass().getResource("AddStock.fxml"));


        
        Scene scene = new Scene(root,850, 580);
        
        primaryStage.setTitle("Smart Truck");
        primaryStage.setScene(scene);
        primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(AddStockController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
