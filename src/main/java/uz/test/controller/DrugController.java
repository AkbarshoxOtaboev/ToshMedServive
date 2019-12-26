package uz.test.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import uz.test.model.Company;
import uz.test.repository.CompanyRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DrugController implements Initializable {
    @FXML
    private JFXComboBox<String> companyCB;

    @FXML
    private JFXTextField drugName;

    @FXML
    private JFXTextField drugCount;

    @FXML
    private JFXTextField drugPrice;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initComboxCompanyName();
    }

    private void initComboxCompanyName() {
        CompanyRepository companyRepository = new CompanyRepository();
        List<Company> companies = companyRepository.getAllCompany();
        List<String> companyNameList =new ArrayList<>();
        for (int i = 0; i < companies.size() ; i++) {
            companyNameList.add(companies.get(i).getName());
        }
        ObservableList<String> strings = FXCollections.observableArrayList(companyNameList);
        companyCB.setItems(strings);
    }


    public void saveDrug(ActionEvent actionEvent) {

    }

    public void fillCB(ActionEvent actionEvent) {
    }
}
