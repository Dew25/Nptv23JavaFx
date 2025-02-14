package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.service.AuthorService;
import ee.ivkhkdev.nptv23javafx.service.FormService;
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

    private FormService formService;
    private AuthorService authorService;
    @FXML private TextField tfFirstname;
    @FXML private TextField tfLastname;

    public AuthorFormController(FormService formService, AuthorService authorService) {
        this.formService = formService;
        this.authorService = authorService;
    }

    @FXML private void create() throws IOException {
        Author author = new Author();
        author.setFirstname(tfFirstname.getText());
        author.setLastname(tfLastname.getText());
        authorService.add(author);
        formService.loadMainForm();
    }
    @FXML private void goToMainForm() throws IOException {
        formService.loadMainForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
