package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.service.AuthorService;
import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.id.factory.spi.StandardGenerator;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class AuthorFormController implements Initializable {

    private SpringFXMLLoader springFXMLLoader;
    private AuthorService authorService;
    @FXML private TextField tfFirstname;
    @FXML private TextField tfLastname;

    public AuthorFormController(SpringFXMLLoader springFXMLLoader, AuthorService authorService) {
        this.springFXMLLoader = springFXMLLoader;
        this.authorService = authorService;
    }

    @FXML private void create() throws IOException {
        Author author = new Author();
        author.setFirstname(tfFirstname.getText());
        author.setLastname(tfLastname.getText());
        authorService.add(author);
        loadMainForm();
    }
    private void loadMainForm() throws IOException {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/main/mainForm.fxml");
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Nptv23JavaFX Библиотека");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
    private Stage getPrimaryStage(){
        return Nptv23JavaFxApplication.primaryStage;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
