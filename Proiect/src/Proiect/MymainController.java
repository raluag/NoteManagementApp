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

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Raluca
 */
public class MymainController implements Initializable {

    String updateTitle;
    String updateContent;
    private int id;

    private HBox myButtonsDisplayed;

    @FXML
    private Button searchButton;

    @FXML
    private TextField seachField;

    @FXML
    private TableView<Notite> myTable;

    @FXML
    private TableColumn<Notite, String> idTitle;

    @FXML
    private TableColumn<Notite, String> idDate;

    @FXML
    private TableColumn<Notite, String> idContent;

    @FXML
    private TableColumn<Notite, String> idAction;

    private final ObservableList<Notite> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idTitle.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        idDate.setCellValueFactory(cellData -> cellData.getValue().dateProperty());
        idContent.setCellValueFactory(cellData -> cellData.getValue().contentProperty());
        try {
            setMain();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MymainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MymainController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void adaugareNotite(ActionEvent event)  {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/resources/adaugare_notite.fxml"));
            Parent root2 = (Parent) loader.load();
            AdaugarenotiteController scene2controller = loader.getController();
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root2));
            stage.showAndWait();
            boolean isClicked = scene2controller.getIsClicked();
            if(scene2controller.getIsClicked()){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date();
                Connection conn = Proiect.Conexiune.connect();
                Statement stmt = conn.createStatement();
                ResultSet results = stmt.executeQuery("SELECT * FROM NOTITE WHERE \"id\"=(SELECT MAX(\"id\") FROM NOTITE)");
                while(results.next()) {
                    String titlu = results.getString("titlu");
                    String continut = results.getString("continut");
                    id = results.getInt("id");
                    Main.notiteData.add(new Notite(titlu, sdf.format(date), continut, id));
                }
                stmt.close();

            }

        } catch (IOException | ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void setMain() throws ClassNotFoundException, SQLException, IOException {
        //Set the custom factory to action column
        idAction.setCellFactory(addButton());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String titlu;
        String continut;
        Connection conn = Proiect.Conexiune.connect();
        Statement stmt = conn.createStatement();
        ResultSet results = stmt.executeQuery("SELECT * from NOTITE");
        if(notiteData.isEmpty()){
          while (results.next()) {
            titlu = results.getString("titlu");
            continut = results.getString("continut");
            id = results.getInt("id");
            System.out.println("id-ul initial:"+id);
            Main.notiteData.add(new Notite(titlu,sdf.format(date),continut,id));
            myTable.setItems(Main.notiteData);
          }
        }

        stmt.close();

        myTable.setItems(Main.notiteData);
    }

    @FXML
    void inchidereAplicatie(ActionEvent event) {
        Platform.exit();
    }

    private Callback<TableColumn<Notite, String>, TableCell<Notite, String>> addButton() {
        Callback<TableColumn<Notite, String>, TableCell<Notite, String>> myButtons
                = (param) -> {

            final TableCell<Notite, String> cell = new TableCell<Notite, String>() {

                //updateItem method override
                @Override
                public void updateItem(String item, boolean empty) {

                    super.updateItem(item, empty);

                    //cell created only on empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        //create the button and the Hbox containing the items

                        final Button updateButton = new Button("Update");
                        final Button deleteButton = new Button("Delete");
                        myButtonsDisplayed = new HBox(deleteButton, updateButton);

                        //put the listener to them,for when it's clicked
                        updateButton.setOnAction(event -> {
                            //clicked row extract

                            Notite p = getTableView().getItems().get(getIndex());
                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                        "/resources/update_notite.fxml"));
                                Parent root3 = (Parent) loader.load();
                                UpdatenotiteController scene3controller = loader.getController();
                                scene3controller.getInfoUpdate(p.getTitle(), p.getContent(),p.getId());
                                Stage stage = new Stage();
                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setTitle("Update!");
                                stage.setScene(new Scene(root3));
                                stage.showAndWait();
                                boolean isClicked = scene3controller.getIsClicked();
                                if(isClicked){
                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date();
                                    Connection conn = Proiect.Conexiune.connect();
                                    Statement stmt = conn.createStatement();
                                    stmt.executeUpdate("UPDATE NOTITE set \"titlu\" = '"+scene3controller.getTitle()+"',\"continut\" = '"+scene3controller.getContent()+"' WHERE \"id\" ="+p.getId());
                                    ResultSet results = stmt.executeQuery("SELECT * FROM NOTITE");
                                    notiteData.clear();

                                    while(results.next()) {
                                        String titlu = results.getString("titlu");
                                        String continut = results.getString("continut");
                                        id = results.getInt("id");
                                        Main.notiteData.add(new Notite(titlu, sdf.format(date), continut, id));
                                    }
                                    stmt.close();
                                }

                            } catch (IOException | SQLException | ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                        });

                        deleteButton.setOnAction(event -> {
                            //clicked row extract

                            Notite p = getTableView().getItems().get(getIndex());

                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource(
                                        "/resources/delete_notite.fxml"));
                                Parent root2 = (Parent) loader.load();
                                DeleteNotiteController scene2controller = loader.getController();
                                scene2controller.getInfo(p.getTitle(),p.getContent(),p.getId());
                                Stage stage = new Stage();
                                stage.setTitle("Delete!");

                                stage.initModality(Modality.APPLICATION_MODAL);
                                stage.setScene(new Scene(root2));
                                stage.showAndWait();
                                boolean isClicked = scene2controller.getIsClicked();
                                if(isClicked){




                                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                                    Date date = new Date();
                                    Connection conn = Proiect.Conexiune.connect();
                                    Statement stmt = conn.createStatement();
                                    stmt.executeUpdate("DELETE FROM NOTITE WHERE \"id\" = "+p.getId());
                                    ResultSet results = stmt.executeQuery("SELECT * FROM NOTITE");
                                    notiteData.clear();

                                    while(results.next()) {
                                        String titlu = results.getString("titlu");
                                        String continut = results.getString("continut");
                                        id = results.getInt("id");

                                        Main.notiteData.add(new Notite(titlu, sdf.format(date), continut, id));
                                    }
                                    stmt.close();

                                }

                            } catch (IOException | ClassNotFoundException | SQLException ex) {
                                ex.printStackTrace();
                            }
                        });
                        //remember the set created
                        setGraphic(myButtonsDisplayed);
                        setText(null);
                    }
                }
                ;
            };
            return cell;
        };
        return myButtons;
    }

    @FXML
    void searchAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        if(!seachField.getText().isEmpty()) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Connection conn = Proiect.Conexiune.connect();
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM NOTITE WHERE \"titlu\"='" + seachField.getText() + "'");
            notiteData.clear();

            while (results.next()) {
                String titlu = results.getString("titlu");
                String continut = results.getString("continut");
                id = results.getInt("id");

                Main.notiteData.add(new Notite(titlu, sdf.format(date), continut, id));
            }
            stmt.close();
        }
        else{

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date();
            Connection conn = Proiect.Conexiune.connect();
            Statement stmt = conn.createStatement();
            ResultSet results = stmt.executeQuery("SELECT * FROM NOTITE");
            notiteData.clear();

            while(results.next()) {
                String titlu = results.getString("titlu");
                String continut = results.getString("continut");
                id = results.getInt("id");

                Main.notiteData.add(new Notite(titlu, sdf.format(date), continut, id));
            }
            stmt.close();

        }

    }

}
