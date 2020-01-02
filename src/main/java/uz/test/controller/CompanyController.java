package uz.test.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import uz.test.model.Company;
import uz.test.repository.CompanyRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class CompanyController implements Initializable {
    private CompanyRepository companyRepository  = new CompanyRepository();

    @FXML
    private JFXTextField inputCompanyName;

    @FXML
    private TableView<Company> tableData;

    @FXML
    private TableColumn<Company, Long> id;

    @FXML
    private TableColumn<Company, String > companyName;

    @FXML
    private TableColumn<Company, Integer> debt;

    public CompanyController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void delete(javafx.event.ActionEvent actionEvent) {
    }

    public void save(javafx.event.ActionEvent actionEvent) {
        String companyName =inputCompanyName.getText();
        Integer balans = 0;
        Company company = new Company(companyName,balans);
        companyRepository.createCompany(company);
    }

    public void payment(javafx.event.ActionEvent actionEvent) {
    }


}
