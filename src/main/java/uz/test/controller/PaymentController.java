package uz.test.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uz.test.model.Company;
import uz.test.model.Payment;
import uz.test.repository.CompanyRepository;

public class PaymentController implements Initializable {
    @FXML
    private TableView<Payment> tableDate;

    @FXML
    private TableColumn<Payment, Long> id;

    @FXML
    private TableColumn<Payment, Double> paymentVolume;

    @FXML
    private TableColumn<Payment, String> date;

    @FXML
    private JFXComboBox<DropDown> companyCB;

    @FXML
    private JFXTextField paymennvolumeTF;

    @FXML
    private DatePicker paymentDate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
        refreshTable();
        try {
            initComboBoxCompanyName();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void initComboBoxCompanyName() throws Exception {
        CompanyRepository companyRepository = new CompanyRepository();
        List<Company> companies = companyRepository.getAllCompany();
        List<DropDown> companyNameList = new ArrayList<>();
        for (int i = 0; i< companies.size(); i++){
            DropDown dropDown = new DropDown();
            dropDown.setId(companies.get(i).getId());
            dropDown.setName(companies.get(i).getName());
            companyNameList.add(dropDown);
        }
        ObservableList<DropDown> strings = FXCollections.observableArrayList();
        for (int i = 0 ; i < companyNameList.size(); i++){
            strings.add(companyNameList.get(i));
        }
        companyCB.setItems(strings);
    }

    private void refreshTable() {
    }

    private void loadTable() {

    }

    public void payment(ActionEvent actionEvent) {
    }

    public void print(ActionEvent actionEvent) {
    }
}
