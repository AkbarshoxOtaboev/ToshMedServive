package uz.test.repository;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public abstract class Report {
    private static JasperReport jreport;
    private static JasperViewer jviewer;
    private static JasperPrint jprint;

    public static void createReport(Connection connect, Map<String, Object> map, InputStream by){
        try{
            jreport = (JasperReport) JRLoader.loadObject(by);
            jprint = JasperFillManager.fillReport(jreport,map, connect);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public static void showReport(){
        jviewer = new JasperViewer(jprint);
        jviewer.setVisible(true);
    }
}
