package uz.test.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import uz.test.model.Company;
import uz.test.model.Drug;
import uz.test.repository.CompanyRepository;
import uz.test.repository.DrugRepository;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DrugController implements Initializable {
    private DrugRepository drugRepository = new DrugRepository();
    private CompanyRepository companyRepository = new CompanyRepository();
    @FXML
    private JFXComboBox<DropDown> companyCB;

    @FXML
    private JFXTextField drugName;

    @FXML
    private JFXTextField drugCount;

    @FXML
    private JFXTextField drugPrice;
    @FXML
    private DatePicker date;

    @FXML
    private TableView<Drug> tableDataDrug;

    @FXML
    private TableColumn<Drug, Long> id;

    @FXML
    private TableColumn<Drug, String> drugNameInTable;

    @FXML
    private TableColumn<Drug, Integer> drugCountInTable;

    @FXML
    private TableColumn<Drug, Double> drugPriceInTable;

    @FXML
    private TableColumn<Drug, Double> generalPriceInTable;

    @FXML
    private TableColumn<Drug, String> dateWhenBuyDrug;

    private boolean close =true;

    public DrugController() throws Exception {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            initComboxCompanyName();
        } catch (Exception e) {
            e.printStackTrace();
        }
        disable(close);
        refreshTable();
        companyCB.setOnAction(actionEvent -> {
            comboBoxEvent();
        });
    }


    private void disable(boolean close) {
        drugName.setDisable(close);
        drugPrice.setDisable(close);
        drugCount.setDisable(close);
        date.setDisable(close);
    }

    private ObservableList<Drug> drugs = FXCollections.observableArrayList();

    private void loadTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        drugNameInTable.setCellValueFactory(new PropertyValueFactory<>("drugName"));
        drugCountInTable.setCellValueFactory(new PropertyValueFactory<>("count"));
        drugPriceInTable.setCellValueFactory(new PropertyValueFactory<>("price"));
        generalPriceInTable.setCellValueFactory(new PropertyValueFactory<>("generalPrice"));
        dateWhenBuyDrug.setCellValueFactory(new PropertyValueFactory<>("date"));

    }
    private void refreshTable(){
        loadTable();
        drugs = FXCollections.observableArrayList(drugRepository.getAllDrugs());
        tableDataDrug.setItems(drugs);
    }


    private void initComboxCompanyName() throws Exception {
        CompanyRepository companyRepository = new CompanyRepository();
        List<Company> companies = companyRepository.getAllCompany();
        List<DropDown> companyNameList =new ArrayList<>();
        for (int i = 0; i < companies.size() ; i++) {
            DropDown dropDown = new DropDown();
            dropDown.setId(companies.get(i).getId());
            dropDown.setName(companies.get(i).getName());
            companyNameList.add(dropDown);
        }
        ObservableList<DropDown> strings = FXCollections.observableArrayList();
        for(int i = 0 ; i < companyNameList.size(); i++){
           strings.add(companyNameList.get(i));
        }
        companyCB.setItems(strings);
    }

    public void saveDrug(ActionEvent actionEvent) {
        String name = drugName.getText();
        Integer count= Integer.parseInt(String.valueOf(drugCount.getText()));
        Double price= Double.parseDouble(String.valueOf(drugPrice.getText()));
        Double generalPrice = 1.0*count*price;
        String dateBuy = date.getValue().toString();
        DropDown dropDown = companyCB.getSelectionModel().getSelectedItem();
        Long companyId= dropDown.getId();
        Drug newDrug = new Drug(name,count,price,generalPrice,dateBuy,companyId);
        drugRepository.saveDrug(newDrug);
        Company company = companyRepository.getCompanyById(companyId);
        Integer companyBalans = company.getBalans();
        company.setBalans((int) (companyBalans - generalPrice));
        company.setId(companyId);
        companyRepository.updateCompany(company);
        refreshTable();


    }

    public void comboBoxEvent() {
        close = false;
        disable(close);
        DropDown dropDown = companyCB.getSelectionModel().getSelectedItem();
        Long companyId= dropDown.getId();
        System.out.println(companyId);
        loadTable();
        drugs = FXCollections.observableArrayList(drugRepository.getDrugsByIdCompany(companyId));
        tableDataDrug.setItems(drugs);
    }
}
