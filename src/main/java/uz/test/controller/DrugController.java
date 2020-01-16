package uz.test.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import uz.test.App;
import uz.test.model.Company;
import uz.test.model.Drug;
import uz.test.repository.CompanyRepository;
import uz.test.repository.DrugRepository;
import uz.test.repository.Report;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;

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

    @FXML
    private Button printBtn;

    private boolean close =true;
    private boolean update = true;
    public DrugController() throws Exception {
    }
    Window owner;


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
        if (update){
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
        }else {
            if(companyCB.getSelectionModel().isEmpty()){
                JOptionPane.showMessageDialog(null, "Korxonani tanlang");
            }else{
                Drug selectedDrug = tableDataDrug.getSelectionModel().getSelectedItem();
                String name = drugName.getText();
                Integer count= Integer.parseInt(String.valueOf(drugCount.getText()));
                Double price= Double.parseDouble(String.valueOf(drugPrice.getText()));
                Double generalPrice = 1.0*count*price;
                String dateBuy = date.getValue().toString();
                DropDown dropDown = companyCB.getSelectionModel().getSelectedItem();
                Long companyId= dropDown.getId();
                Drug newDrug = new Drug(name,count,price,generalPrice,dateBuy,companyId);
                newDrug.setId(selectedDrug.getId());
                Company company = companyRepository.getCompanyById(companyId);
                company.setBalans((int) (company.getBalans() + selectedDrug.getGeneralPrice()));
                drugRepository.updateDrug(newDrug);
                company.setBalans((int) (company.getBalans() - generalPrice));
                company.setId(companyId);
                companyRepository.updateCompany(company);
                refreshTable();

            }


        }

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


    public void deleteDrug(ActionEvent actionEvent) {
        if(tableDataDrug.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ro`yhatdan tanlang");
        }else {
            Drug drug = tableDataDrug.getSelectionModel().getSelectedItem();
            Long id = drug.getId();
            drugRepository.deleteDrug(id);
            refreshTable();
        }
    }

    public void updateDrug(ActionEvent actionEvent) {
        if(tableDataDrug.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ro`yhatdan tanlang");
        }else {
            update = false;
            disable(false);
            Drug drug = tableDataDrug.getSelectionModel().getSelectedItem();
            drugName.setText(drug.getDrugName());
            drugCount.setText(drug.getCount().toString());
            drugPrice.setText(drug.getPrice().toString());
            date.setValue(LocalDate.parse(drug.getDate()));

        }
    }



    public void print(ActionEvent actionEvent) throws IOException {
        Printer printer= Printer.getDefaultPrinter();
        PageLayout pageLayout = printer.createPageLayout(Paper.A4, PageOrientation.LANDSCAPE, Printer.MarginType.DEFAULT);

        PrinterJob printerJob = PrinterJob.createPrinterJob();

        if (printerJob !=null) {
            boolean showDialog = printerJob.showPrintDialog(owner);
            if(showDialog){
                tableDataDrug.setScaleX(0.60);
                tableDataDrug.setScaleY(0.60);
                tableDataDrug.setTranslateX(-200);
                tableDataDrug.setTranslateY(-50);
                boolean success = printerJob.printPage(pageLayout, tableDataDrug);
                if(success){
                    printerJob.endJob();
                }
                tableDataDrug.setTranslateX(0);
                tableDataDrug.setTranslateY(0);
                tableDataDrug.setScaleX(1.0);
                tableDataDrug.setScaleY(1.0);

            }
        }
    }
}
