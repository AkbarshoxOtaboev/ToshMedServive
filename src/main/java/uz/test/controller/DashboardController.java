package uz.test.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    @FXML
    private JFXButton company;

    @FXML
    private JFXButton drug;

    @FXML
    private JFXButton stat;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
