package uz.test.controller;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class SibeBarController implements Initializable {
    private final String COMPANY_UI = "/fxml/company.fxml";
    private final String DRAG_UI = "/fxml/drag.fxml";
    private final String PAYMENT_UI= "/fxml/payment.fxml";
    private final String STATISTIKA_UI = "/fxml/statistika.fxml";
    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadUI(String uiPath){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(uiPath));
        } catch (Exception e) {
            System.err.println(e);
        }
        borderPane.setCenter(root);
    }

    public void company(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(COMPANY_UI);
    }

    public void drag(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(DRAG_UI);
    }

    public void payment(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(PAYMENT_UI);
    }

    public void statistika(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(STATISTIKA_UI);
    }
}

