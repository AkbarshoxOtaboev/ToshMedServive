package uz.test.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.print.Printer;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import uz.test.model.Company;
import uz.test.model.Drug;
import uz.test.model.Payment;
import uz.test.repository.AdmiRepository;
import uz.test.repository.CompanyRepository;
import uz.test.repository.PaymentRepository;

import javax.swing.*;

public class PaymentController implements Initializable {

    private PaymentRepository paymentRepository = new PaymentRepository();
    private CompanyRepository companyRepository = new CompanyRepository();

    @FXML
    private TableView<Payment> tableDate;

    @FXML
    private TableColumn<Payment, Long> id;

    @FXML
    private TableColumn<Payment, String> paymentComment;

    @FXML
    private TableColumn<Payment, Double> paymentVolume;

    @FXML
    private TableColumn<Payment, String> date;

    @FXML
    private JFXComboBox<DropDown> companyCB;

    @FXML
    private JFXTextField paymennvolumeTF;

    @FXML
    private TextArea comment;

    @FXML
    private DatePicker paymentDate;


    public PaymentController() throws Exception {
    }

    private boolean update = true;
    private ObservableList<Payment> payments = FXCollections.observableArrayList();
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
        loadTable();
        payments = FXCollections.observableArrayList(paymentRepository.getAllPayments());
        tableDate.setItems(payments);
    }
    private void refreshtableById( Long id){
        loadTable();
        payments = FXCollections.observableArrayList(paymentRepository.getPaymentsByCompanyID(id));
        tableDate.setItems(payments);
    }

    private void loadTable() {
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        paymentComment.setCellValueFactory(new PropertyValueFactory<>("commentary"));
        paymentVolume.setCellValueFactory(new PropertyValueFactory<>("paymentVolume"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));

    }

    public void payment(ActionEvent actionEvent) {
        if(update){
            if(companyCB.getSelectionModel().isEmpty() || paymennvolumeTF.getText().isEmpty() || paymentDate.getValue().toString().isEmpty()){
                JOptionPane.showMessageDialog(null,"Barcha maydonlarni to`ldiring");
            }
            else{
                String paymentComment = comment.getText();
                Integer pavmentVolume = Integer.parseInt(paymennvolumeTF.getText());
                String  datePayment = paymentDate.getValue().toString();
                DropDown dropDown = companyCB.getSelectionModel().getSelectedItem();
                Long companyID = dropDown.getId();
                Payment payment = new Payment(paymentComment,pavmentVolume,datePayment,companyID);
                paymentRepository.creataPayment(payment);
                Company company = companyRepository.getCompanyById(companyID);
                Integer companyBalans= company.getBalans();
                company.setBalans(companyBalans + pavmentVolume);
                company.setId(companyID);
                companyRepository.updateCompany(company);
                refreshtableById(companyID);
            }
        }else {
            Payment selectedPayment = tableDate.getSelectionModel().getSelectedItem();
            String paymentComment = comment.getText();
            Integer pavmentVolume = Integer.parseInt(paymennvolumeTF.getText());
            String  datePayment = paymentDate.getValue().toString();
            DropDown dropDown = companyCB.getSelectionModel().getSelectedItem();
            Long companyID = dropDown.getId();
            Payment payment = new Payment(paymentComment,pavmentVolume,datePayment,companyID);
            payment.setId(selectedPayment.getId());
            Company company = companyRepository.getCompanyById(companyID);
            company.setBalans(company.getBalans() - selectedPayment.getPaymentVolume());
            paymentRepository.updatePayment(payment);
            company.setBalans(company.getBalans() + pavmentVolume);
            company.setId(companyID);
            companyRepository.updateCompany(company);
            refreshtableById(companyID);
        }

    }

    public void print(ActionEvent actionEvent) {

    }

    public void selectComboBox(ActionEvent actionEvent) {
        DropDown dropDown = companyCB.getSelectionModel().getSelectedItem();
        Long companyID = dropDown.getId();
        payments = FXCollections.observableArrayList(paymentRepository.getPaymentsByCompanyID(companyID));
        loadTable();
        tableDate.setItems(payments);
    }

    public void removePayment(ActionEvent actionEvent) {
        if(tableDate.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ro`yhatdan tanlang");
        }else{
            Payment payment = tableDate.getSelectionModel().getSelectedItem();
            DropDown dropDown = companyCB.getSelectionModel().getSelectedItem();
            Long companyId = dropDown.getId();
            Long id = payment.getId();
            paymentRepository.deletePayment(id);
            tableDate.refresh();
            refreshtableById(companyId);

        }
    }

    public void updateBtn(ActionEvent actionEvent) {
        if(tableDate.getSelectionModel().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ro`yhatdan tanlang");
        }else {
            Payment payment = tableDate.getSelectionModel().getSelectedItem();
            comment.setText(payment.getCommentary());
            paymennvolumeTF.setText(payment.getPaymentVolume().toString());
//            paymennvolumeTF.setDisable(true);
            paymentDate.setValue(LocalDate.parse(payment.getDate()));
            update = false;
        }
    }
}
