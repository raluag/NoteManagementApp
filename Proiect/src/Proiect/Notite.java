/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proiect;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

/**
 *
 * @author Raluca
 */
public class Notite {

    private  int id;
    private final StringProperty title;
    private final StringProperty date;
    private final StringProperty content;

    public Notite(String title, String date, String content,int id) {

        this.title = new SimpleStringProperty(title);
        this.date = new SimpleStringProperty(date);
        this.content = new SimpleStringProperty(content);
        this.id = id;

    }

    public String getTitle() {
        return title.get();
    }

    public int getId(){ return id; }

    public void setId(int id){this.id = id;}

    public void setTitle(String title) {
        this.title.set(title);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public String getDate() {
        return date.get();
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public String getContent() {
        return content.get();
    }

    public void setContent(String content) {
        this.content.set(content);
    }

    public StringProperty contentProperty() {
        return content;
    }

}
