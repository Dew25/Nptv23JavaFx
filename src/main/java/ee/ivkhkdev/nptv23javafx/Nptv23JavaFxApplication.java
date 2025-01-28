package ee.ivkhkdev.nptv23javafx;

import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Nptv23JavaFxApplication extends Application {
    public static ConfigurableApplicationContext applicationContext;

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Nptv23JavaFxApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        SpringFXMLLoader springFXMLLoader = applicationContext.getBean(SpringFXMLLoader.class);
        FXMLLoader fxmlLoader = springFXMLLoader.load("/nptv23JavaFx.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("Nptv23JavaFX");
        stage.centerOnScreen();
        stage.show();
    }
}
