package ee.ivkhkdev.nptv23javafx;

import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Nptv23JavaFxApplication extends Application {
    public static ConfigurableApplicationContext applicationContext;
    public static Stage primaryStage;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Nptv23JavaFxApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        SpringFXMLLoader springFXMLLoader = applicationContext.getBean(SpringFXMLLoader.class);
        FXMLLoader fxmlLoader = springFXMLLoader.load("/main/mainForm.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Nptv23JavaFX Библиотека");
        primaryStage.centerOnScreen();
        primaryStage.show();
    }
}
