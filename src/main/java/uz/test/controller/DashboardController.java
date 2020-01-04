package uz.test.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        company.setOnMouseClicked(mouseEvent -> {
            loadStage("/fxml/company.fxml", "Ko`rxonalar`");

        });
        drug.setOnMouseClicked(mouseEvent -> {
            loadStage("/fxml/drug.fxml","Dorilar bo`limi");
        });


    }
    private void loadStage(String fxml, String scenaName){
        try{
            Parent root = FXMLLoader.load(getClass().getResource(fxml));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle(scenaName);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void statistika(ActionEvent actionEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/statistika.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Statistika");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void admin(ActionEvent actionEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/admin.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Security");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pay(ActionEvent actionEvent) {
        try{
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/payment.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("To`lovlar bo`limi");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
