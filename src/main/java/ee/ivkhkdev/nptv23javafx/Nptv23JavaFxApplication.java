package ee.ivkhkdev.nptv23javafx;

import ee.ivkhkdev.nptv23javafx.loaders.LoginFormLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Nptv23JavaFxApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        LoginFormLoader loginFormLoader = SpringApplication.run(Nptv23JavaFxApplication.class).getBean(LoginFormLoader.class);
        loginFormLoader.setPrimaryStage(primaryStage);
        loginFormLoader.load();
    }
}
