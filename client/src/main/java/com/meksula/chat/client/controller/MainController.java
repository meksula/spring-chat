package com.meksula.chat.client.controller;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @Author
 * Karol Meksuła
 * 14-07-2018
 * */

public class MainController implements Initializable, Observable {
    private String who = "Karol";
    private List<Label> messagesCache = new ArrayList<>();

    @FXML
    private ImageView loupeButton;

    @FXML
    private TextField loupeField;

    @FXML
    private ImageView userAvatar;

    @FXML
    private VBox contactsVbox;

    @FXML
    private VBox chatWindow;

    @FXML
    private Label usernameField;
    
    @FXML
    private Label settingButton, newRoom, lookForRoom;

    @FXML
    private ImageView postButton;

    @FXML
    private TextArea messageField;

    @FXML
    private TitledPane contacts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addButtonActions();
        sendMessageAction();
        searchingAction();
        contactDisplay(new ArrayList<>(Arrays.asList("Adaś", "Karol", "Alek", "Tosiek")));

        usernameField.setText(who);
    }

    private void addButtonActions() {
        Label[] actions = {settingButton, newRoom, lookForRoom};

        for (Label label : actions) {
            label.setOnMouseEntered(event -> {
                label.getStyleClass().remove("menu_button");
                label.getStyleClass().add("menu_button_shine");
            });

            label.setOnMouseExited(event -> {
                label.getStyleClass().remove("menu_button_shine");
                label.getStyleClass().add("menu_button");
            });
        }

        postButton.setOnMouseEntered(event -> {
            postButton.setImage(new Image("/img/send-active.png"));
        });

        postButton.setOnMouseExited(event -> {
            postButton.setImage(new Image("/img/send-inactive.png"));
        });

    }

    private void sendMessageAction() {
        postButton.setOnMouseClicked(event -> send());

        messageField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                send();
            }
        });
    }

    private void send() {
        String text = messageField.getText();

        if (text.length() == 1) {
            return;
        }

        Label message = displayMessage(text);
        chatWindow.getChildren().add(message);
        this.messageField.clear();
        chatWindowMoving();
    }

    private Label displayMessage(String text) {
        Label label = new Label();
        label.setWrapText(true);
        label.getStyleClass().add("message");

        LocalTime localTime = LocalTime.now();
        String time = localTime.format(DateTimeFormatter.ofPattern("k:m:s"));
        label.setText(time + "\n" + who + " said:         " + text);
        return label;
    }



    private void chatWindowMoving() {
        int amount = chatWindow.getChildren().size();

        if (amount == 7) {
            Label oldest = (Label) chatWindow.getChildren().get(0);
            messagesCache.add(oldest);
            chatWindow.getChildren().remove(0);
        }
    }

    private void contactDisplay(List<String> contacts) {

        for (String contact : contacts) {
            Label label = new Label();
            label.getStyleClass().add("contact");

            label.setText(contact);
            contactsVbox.getChildren().add(label);
        }

    }

    private void searchingAction() {
        loupeButton.setOnMouseClicked(event -> {
            displaySearch(loupeField.getText());
        });

        loupeField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                displaySearch(loupeField.getText());
            }
        });
    }

    private void displaySearch(String text) {
        new FxmlLoaderTemplate().loadFxml("/templates/search_contacts.fxml", text);
    }

    @Override
    public void addListener(InvalidationListener listener) {

    }

    @Override
    public void removeListener(InvalidationListener listener) {

    }
}
