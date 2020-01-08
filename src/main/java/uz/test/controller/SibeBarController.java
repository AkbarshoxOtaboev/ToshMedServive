package uz.test.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.fxml.FXML;
import javafx.scene.CacheHint;
import javafx.scene.Parent;
import javafx.scene.effect.Effect;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import java.net.URL;
import java.util.ResourceBundle;

public class SibeBarController implements Initializable {
    private final String COMPANY_UI = "/fxml/company.fxml";
    private final String DRAG_UI = "/fxml/drug.fxml";
    private final String PAYMENT_UI= "/fxml/payment.fxml";
    private final String STATISTIKA_UI = "/fxml/statistika.fxml";
    @FXML
    private BorderPane borderPane;
    @FXML
    private JFXButton companyIdBtn;

    @FXML
    private JFXButton drugIdBtn;

    @FXML
    private JFXButton paymentIdBtn;

    @FXML
    private JFXButton statIdBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadUI(COMPANY_UI);
    }

    private void loadUI(String uiPath){
        Parent root = null;
        try{
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(uiPath));
            root = fxmlLoader.load();
        } catch (Exception e) {
            System.err.println(e);
        }
        borderPane.setCenter(root);
    }

    public void company(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(COMPANY_UI);
        companyIdBtn.setStyle("-fx-background-color: #f20b0b");
        paymentIdBtn.setStyle("");
        statIdBtn.setStyle("");
        drugIdBtn.setStyle("");
    }

    public void drag(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(DRAG_UI);
        drugIdBtn.setStyle("-fx-background-color: #f20b0b");
        companyIdBtn.setStyle("");
        paymentIdBtn.setStyle("");
        statIdBtn.setStyle("");
    }

    public void payment(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(PAYMENT_UI);
        paymentIdBtn.setStyle("-fx-background-color: #f20b0b");
        companyIdBtn.setStyle("");
        statIdBtn.setStyle("");
        drugIdBtn.setStyle("");
    }

    public void statistika(javafx.scene.input.MouseEvent mouseEvent) {
        loadUI(STATISTIKA_UI);
        statIdBtn.setStyle("-fx-background-color: #f20b0b");
        paymentIdBtn.setStyle("");
        companyIdBtn.setStyle("");
        drugIdBtn.setStyle("");
    }
}

