/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proiect;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

/**
 *
 * @author Raluca
 */
public class Main extends Application {


    private static Main instance;
    private Stage mainWindow;
    public static ObservableList<Notite> notiteData = FXCollections.observableArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public ObservableList<Notite> getNotiteData() {
        return notiteData;
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        this.mainWindow = primaryStage;
        primaryStage.setTitle("Notite");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
      /*  Notite p = new Notite();
        notiteData.add(new Notite("ExamISP", sdf.format(date), "To learn chapter 1-4"));
        p.setId();
        notiteData.add(new Notite("WatchMovie", sdf.format(date), "Movie Title"));
        p.setId();
        notiteData.add(new Notite("Eat", sdf.format(date), "probably fish"));
        p.setId();*/

        showMainFxml();

    }

    private void showMainFxml() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/resources/mymain.fxml"));
            Scene scene = new Scene(root);
            mainWindow.setScene(scene);
            mainWindow.show();
        } catch (IOException ex) {
            Logger.getLogger(MymainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Main getInstance() {
        return instance;
    }

    public Stage getMainWindow() {
        return mainWindow;
    }




}
