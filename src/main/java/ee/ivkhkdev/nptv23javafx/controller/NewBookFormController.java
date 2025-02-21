package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.service.AuthorService;
import ee.ivkhkdev.nptv23javafx.service.BookService;
import ee.ivkhkdev.nptv23javafx.service.FormService;
import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;
@Component
public class NewBookFormController implements Initializable {

    private FormService formService;
    private BookService bookService;
    private AuthorService auhtorService;

    @FXML private TextField tfTitle;
    @FXML private ListView<Author> lvAuthors;
    @FXML private TextField tfPublicationYear;
    @FXML private TextField tfQuantity;

    public NewBookFormController(FormService formService, BookService bookService, AuthorService authorService) {
        this.formService = formService;
        this.bookService = bookService;
        this.auhtorService = authorService;
    }
    @FXML private void create(){
        Book book = new Book();
        book.setTitle(tfTitle.getText());
        book.getAuthors().addAll(lvAuthors.getSelectionModel().getSelectedItems());
        book.setPublicationYear(Integer.parseInt(tfPublicationYear.getText()));
        book.setQuantity(Integer.parseInt(tfQuantity.getText()));
        book.setCount(book.getQuantity());
        bookService.create(book);
        formService.loadMainForm();
    }

    @FXML private void goToMainForm(){
        formService.loadMainForm();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvAuthors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Author> authors = auhtorService.getListAuthors();
        lvAuthors.getItems().setAll(FXCollections.observableArrayList(authors));
        lvAuthors.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Author author, boolean empty) {
                super.updateItem(author, empty);
                if (empty || author == null) {
                    setText(null);
                } else {
                    setText(author.getId() + ". " + author.getFirstname() + " " + author.getLastname());
                }
            }
        });
    }

}
