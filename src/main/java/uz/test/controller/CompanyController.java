package uz.test.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uz.test.model.Company;
import uz.test.repository.CompanyRepository;

import javax.swing.*;
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
    private TableColumn<Company, String > name;

    @FXML
    private TableColumn<Company, Integer> balans;

    public CompanyController() throws Exception {
    }
     private ObservableList<Company> companies = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
     loadTable();
     refreshTable();
    }

    private void refreshTable() {
        loadTable();
        companies = FXCollections.observableArrayList(companyRepository.getAllCompany());
        tableData.setItems(companies);
    }

    private void loadTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        balans.setCellValueFactory(new PropertyValueFactory<>("balans"));
    }

    public void delete(javafx.event.ActionEvent actionEvent) {


        if(tableData.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ro`yhatdan tanlang");
        }else{
            Company company = tableData.getSelectionModel().getSelectedItem();
            Long id = company.getId();
            companyRepository.deleteCompany(id);
            refreshTable();
        }
    }

    public void save(javafx.event.ActionEvent actionEvent) {

        String companyName =inputCompanyName.getText();
        Integer balans = 0;
        if(companyName.equals("")){
            JOptionPane.showMessageDialog(null, "Korxona nomi kirgizing");
        }else {
            Company company = new Company(companyName,balans);
            companyRepository.createCompany(company);
            refreshTable();
        }


    }
    public void payment(javafx.event.ActionEvent actionEvent) {
    }


}
