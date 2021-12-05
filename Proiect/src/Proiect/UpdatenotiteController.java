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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Raluca
 */
public class UpdatenotiteController implements Initializable {

    @FXML
    private Label textId;

    @FXML
    private TextField textTitle;

    @FXML
    private TextField textContent;

    @FXML
    private Button closeButton;

    @FXML
    private Button updateButton;

    boolean isClicked;

    @FXML
    void closeApp(ActionEvent event) {
        isClicked = false;
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void inchidereFereastra(ActionEvent event) {
        isClicked = false;
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updateInfo(ActionEvent event) {
        isClicked = true;
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }


    void getInfoUpdate(String titlu, String content,int id) {
        textContent.setText(content);
        textTitle.setText(titlu);
        textId.setText(String.valueOf(id));

    }

    String getTitle(){
        return textTitle.getText();
    }

    String getContent(){
        return textContent.getText();
    }

    boolean getIsClicked(){
        return isClicked;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

}
