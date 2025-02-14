package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.service.FormService;
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
    private FormService formService;

    public MenuFormController(FormService formService) {
        this.formService = formService;
    }

    @FXML private void showAuthorForm(){
        formService.loadAuthorForm();
    }
    @FXML private void showBookForm() throws IOException {
        formService.loadNewBookForm();
    }
    private Stage getPrimaryStage() {
        return Nptv23JavaFxApplication.primaryStage;
    }
}
