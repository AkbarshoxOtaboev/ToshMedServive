package uz.test.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import uz.test.model.Admin;
import uz.test.repository.AdmiRepository;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Label;


public class AdminController implements Initializable {
    private AdmiRepository admiRepository = new AdmiRepository();
    @FXML
    private JFXTextField updateLogin;

    @FXML
    private JFXTextField updateParol;

    @FXML
    private Label login;

    @FXML
    private Label parol;

    public AdminController() throws Exception {
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
       load();
    }

    private void load() {
        Admin admin = null;
        admin = admiRepository.getAdminByID((long) 1);
        login.setText(admin.getLogin());
        parol.setText(admin.getPassword());
    }

    public void update(javafx.event.ActionEvent actionEvent) {
        String updLogin = updateLogin.getText();
        String updParol = updateParol.getText();
        Admin admin = new Admin(updLogin,updParol);
        admin.setId((long) 1);
        admiRepository.updateAdmin(admin);
        login.setText(updLogin);
        parol.setText(updParol);
    }
}
