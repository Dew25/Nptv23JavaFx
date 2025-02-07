package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MenuFormController {
    private SpringFXMLLoader springFXMLLoader;

    public MenuFormController(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    @FXML private void showAuthorForm() throws IOException {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/author/AuthorForm.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Создание нового пользователя");
    }
    @FXML private void showBookForm() throws IOException {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/book/newBookForm.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Создание новой книги");
    }
    private Stage getPrimaryStage() {
        return Nptv23JavaFxApplication.primaryStage;
    }
}
