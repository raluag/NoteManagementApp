/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proiect;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raluca
 */
public class DeleteNotiteController implements Initializable {



    @FXML
    private Label myId;

    @FXML
    private Label idTitle;

    @FXML
    private Label idContent;

    @FXML
    private Button closeDelButton;

    private boolean isClicked;

    private String titlu;
    private String date;
    private String content;

    void getInfo(String titlu, String content,int id) {
        idContent.setText(content);
        idTitle.setText(titlu);
        myId.setText(String.valueOf(id));

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {


//


    }

    @FXML
    void closeApp(ActionEvent event) {
        isClicked = false;
        Stage stage = (Stage) closeDelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void inchidereStergere(ActionEvent event) {
        isClicked = false;
        Stage stage = (Stage) closeDelButton.getScene().getWindow();
        stage.close();

    }

    @FXML
    void stergereLinie(ActionEvent event) {

        isClicked = true;
        Stage stage = (Stage) closeDelButton.getScene().getWindow();
        stage.close();
    }

    boolean getIsClicked() {
        return isClicked;
    }

}
