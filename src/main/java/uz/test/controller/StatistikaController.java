package uz.test.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    public void printTable(ActionEvent actionEvent) {
        try {
            Printer printer = Printer.getDefaultPrinter();
            Stage dialogStage = new Stage(StageStyle.DECORATED);
            PrinterJob job = PrinterJob.createPrinterJob(printer);
            if(job != null){
                boolean showDialog = job.showPageSetupDialog(dialogStage);
                if(showDialog){
                    tableData.setScaleX(0.60);
                    tableData.setScaleY(0.60);
                    tableData.setTranslateX(-220);
                    tableData.setTranslateY(-70);
                    boolean success = job.printPage(tableData);
                    if(success){
                        job.endJob();
                    }
                    tableData.setTranslateX(0);
                    tableData.setTranslateY(0);
                    tableData.setScaleX(1.0);
                    tableData.setScaleY(1.0);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
