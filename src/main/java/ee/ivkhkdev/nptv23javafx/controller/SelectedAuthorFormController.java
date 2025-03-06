package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.fxml.FXML;
import org.springframework.stereotype.Component;

@Component
public class SelectedAuthorFormController {
    private Author author;
    private FormLoader formLoader;
    private AuthorService authorService;

    public SelectedAuthorFormController(FormLoader formLoader, AuthorService authorService) {
        this.formLoader = formLoader;
        this.authorService = authorService;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
