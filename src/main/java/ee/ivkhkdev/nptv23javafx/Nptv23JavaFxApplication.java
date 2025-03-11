package ee.ivkhkdev.nptv23javafx;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Nptv23JavaFxApplication extends Application {
    public static ConfigurableApplicationContext applicationContext;
    public static Stage primaryStage;
    public static AppUser currentUser;
    public enum ROLES {USER, MANAGER, ADMINISTRATOR}

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Nptv23JavaFxApplication.class, args);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Nptv23JavaFxApplication.primaryStage = primaryStage;

        FormLoader formLoader = applicationContext.getBean(FormLoader.class);
        formLoader.loadLoginForm();
    }
}
