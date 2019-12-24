package uz.test.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    private final String DASHBOARD = "/fxml/dashboard.fxml";

    @FXML
    private JFXTextField login;

    @FXML
    private JFXPasswordField parol;

    @FXML
    private JFXButton signin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }


    public void kirish(ActionEvent actionEvent) {
        if(login.getText().equals("admin") && parol.getText().equals("admin")){
            try{
                Parent root = FXMLLoader.load(getClass().getResource(DASHBOARD));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


