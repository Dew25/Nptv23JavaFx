package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class SelectedAuthorFormController {
    private Author author;


    @FXML private TextField tfFirstname;
    @FXML private TextField tfLastname;
    @FXML private TextField tfId;
    public SelectedAuthorFormController(AuthorService authorService) {


    }

    public void setAuthor(Author author) {
        this.author = author;
    }
    @FXML private void update(){

    }
    public void initAuthorForm(){
        tfId.setText(author.getId().toString());
        tfFirstname.setText(author.getFirstname());
        tfLastname.setText(author.getLastname());
    }

}
