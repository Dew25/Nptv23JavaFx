package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class AuthorFormController implements Initializable {
    private final MainFormLoader mainFormLoader;
    private final AuthorService authorService;
    @FXML private TextField tfFirstname;
    @FXML private TextField tfLastname;

    public AuthorFormController(MainFormLoader mainFormLoader, AuthorService authorService) {
        this.mainFormLoader = mainFormLoader;

        this.authorService = authorService;
    }

    @FXML private void create(){
        Author author = new Author();
        author.setFirstname(tfFirstname.getText());
        author.setLastname(tfLastname.getText());
        authorService.add(author);
        mainFormLoader.load();
    }
    @FXML private void goToMainForm() {
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tfLastname.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                this.create();
            }
        });
    }
}
