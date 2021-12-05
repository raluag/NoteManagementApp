/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proiect;

import static Proiect.Main.notiteData;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raluca
 */
public class AdaugarenotiteController implements Initializable {


    @FXML
    private TextField textTitlu;

    @FXML
    private TextField textContents;

    @FXML
    private Button okButton;

    @FXML
    private Button closeButton1;

    private boolean isClicked;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    void inapoiInAplicatie(ActionEvent event) {

        Stage stage = (Stage) closeButton1.getScene().getWindow();

        stage.close();
    }

    @FXML
    void revenireInAplicatie(ActionEvent event) {
        isClicked = false;
        Stage stage = (Stage) closeButton1.getScene().getWindow();
        stage.close();

    }

    @FXML
    void passInfo(ActionEvent event) {
            isClicked = true;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/resources/mymain.fxml"));
            Parent root = (Parent) loader.load();
            MymainController scene1controller = loader.getController();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();

            Connection conn = Proiect.Conexiune.connect();
            Statement stmt = conn.createStatement();
            String titlu = textTitlu.getText();
            String continut = textContents.getText();
            stmt.executeUpdate("INSERT INTO NOTITE (\"titlu\",\"continut\") VALUES('"+titlu+"','"+continut+"')");
            stmt.close();
            Stage stage1 = (Stage) closeButton1.getScene().getWindow();
            stage1.close();


        } catch (IOException | SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public boolean getIsClicked(){return isClicked;};
}


