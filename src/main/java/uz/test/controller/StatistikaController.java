package uz.test.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class StatistikaController implements Initializable {
    @FXML
    private JFXComboBox<?> companyStat;

    @FXML
    private JFXButton search;

    @FXML
    private JFXButton printBtn;

    @FXML
    private TableView<?> tableData;

    @FXML
    private TableColumn<?, ?> id;

    @FXML
    private TableColumn<?, ?> companyName;

    @FXML
    private TableColumn<?, ?> drugName;

    @FXML
    private TableColumn<?, ?> count;

    @FXML
    private TableColumn<?, ?> price;

    @FXML
    private TableColumn<?, ?> generalPrice;

    @FXML
    private TableColumn<?, ?> date;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
