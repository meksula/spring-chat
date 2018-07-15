package com.meksula.chat.client.controller;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @Author
 * Karol Meksuła
 * 14-07-2018
 * */

public class FxmlLoaderTemplate {

    public void loadFxml(String path, Event event) {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(this.getClass().getResource(path));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = null;
        if (parent != null) {
            scene = new Scene(parent);
        }
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void loadFxml(String path, Object data) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(path));

        Parent parent = null;
        try {
            parent = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = null;
        if (parent != null) {
            scene = new Scene(parent);
        }

        SearchContactWindowController controller = loader.<SearchContactWindowController>getController();
        controller.initData(data);


        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
