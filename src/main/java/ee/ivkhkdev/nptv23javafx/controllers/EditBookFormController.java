package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class EditBookFormController implements Initializable {
    private MainFormLoader mainFormLoader;
    private BookService bookService;
    private AuthorService auhtorService;
    private Book editBook;

    @FXML private TextField tfId;
    @FXML private TextField tfTitle;
    @FXML private ListView<Author> lvAuthors;
    @FXML private TextField tfPublicationYear;
    @FXML private TextField tfQuantity;
    @FXML private TextField tfCount;


    public EditBookFormController(MainFormLoader mainFormLoader, BookService bookService, AuthorService authorService) {
        this.mainFormLoader = mainFormLoader;
        this.bookService = bookService;
        this.auhtorService = authorService;
    }
    @FXML private void goEdit(){
        editBook.setTitle(tfTitle.getText());
        editBook.getAuthors().addAll(lvAuthors.getSelectionModel().getSelectedItems());
        editBook.setPublicationYear(Integer.parseInt(tfPublicationYear.getText()));
        editBook.setQuantity(Integer.parseInt(tfQuantity.getText()));
        editBook.setCount(Integer.parseInt(tfCount.getText()));
        bookService.add(editBook);
        mainFormLoader.load();
    }

    @FXML private void goToMainForm() {
        mainFormLoader.load();
    }

    public void setEditBook(Book editBook) {
        this.editBook = editBook;
        tfId.setText(editBook.getId().toString());
        tfTitle.setText(editBook.getTitle());
        tfPublicationYear.setText(((Integer) editBook.getPublicationYear()).toString());
        tfQuantity.setText(((Integer) editBook.getQuantity()).toString());
        tfCount.setText(((Integer) editBook.getCount()).toString());

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvAuthors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Author> authors = auhtorService.getList();
        lvAuthors.getItems().setAll(FXCollections.observableArrayList(authors));
        lvAuthors.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Author author, boolean empty) {
                super.updateItem(author, empty);
                if (empty || author == null) {
                    setText(null);
                } else {
                    setText("ID: " + author.getId() + " - " + author.getFirstname() + " " + author.getLastname());
                }
            }
        });
        tfCount.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                this.goEdit();
            }
        });
    }

}
