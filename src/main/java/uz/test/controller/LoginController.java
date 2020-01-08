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
import uz.test.model.Admin;
import uz.test.repository.AdmiRepository;

import javax.swing.*;
import javax.swing.plaf.OptionPaneUI;
import javax.swing.text.html.Option;
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


    public void kirish(ActionEvent actionEvent) throws Exception {
        Admin admin = new AdmiRepository().getAdminByID((long) 1);
        if(login.getText().equals(admin.getLogin()) && parol.getText().equals(admin.getPassword())){
            try{
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/sidebar.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(null,"Hotolik yuz berdi" );

        }

    }
}


