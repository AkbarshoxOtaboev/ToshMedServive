package uz.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import org.hibernate.Session;
import uz.test.db.HibernateUtils;
import uz.test.model.Admin;
import uz.test.repository.AdmiRepository;


public class App extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Session session = HibernateUtils.openSession();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/loginpage.fxml"));
        stage.setTitle("Tizimga kirish");
        stage.setScene(new Scene(root, 314, 290));
        stage.setResizable(false);
        stage.show();
    }



    public static void main(String[] args)  {
        launch(args);
    }
}
