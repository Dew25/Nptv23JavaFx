package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.loaders.MenuFormLoader;
import ee.ivkhkdev.nptv23javafx.loaders.NewBookFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class NewBookFormController implements Initializable {
    private final MainFormLoader mainFormLoader;
    private final NewBookFormLoader newBookFormLoader;
    private final BookService bookService;
    private final AuthorService authorService;

    @FXML private Label lbInfo;
    @FXML private TextField tfTitle;
    @FXML private ListView<Author> lvAuthors;
    @FXML private TextField tfPublicationYear;
    @FXML private TextField tfQuantity;

    public NewBookFormController(MainFormLoader mainFormLoader, NewBookFormLoader newBookFormLoader, BookService bookService, AuthorService authorService) {
        this.mainFormLoader = mainFormLoader;
        this.newBookFormLoader = newBookFormLoader;
        this.bookService = bookService;
        this.authorService = authorService;
    }
    @FXML private void create(){
        Book book = new Book();
        if(tfTitle.getText().isEmpty() || tfPublicationYear.getText().isEmpty()
                || tfQuantity.getText().isEmpty() || lvAuthors.getSelectionModel().getSelectedItems().isEmpty()){
            lbInfo.setText("Заполните все поля");
            return;
        }
        book.setTitle(tfTitle.getText());
        book.getAuthors().addAll(lvAuthors.getSelectionModel().getSelectedItems());
        book.setPublicationYear(Integer.parseInt(tfPublicationYear.getText()));
        book.setQuantity(Integer.parseInt(tfQuantity.getText()));
        book.setCount(book.getQuantity());
        bookService.add(book);
        newBookFormLoader.load();
    }

    @FXML private void goToMainForm(){
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvAuthors.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        List<Author> authors = authorService.getList();
        lvAuthors.getItems().setAll(FXCollections.observableArrayList(authors));
        lvAuthors.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Author author, boolean empty) {
                super.updateItem(author, empty);
                if (empty || author == null) {
                    setText(null);
                } else {
                    setText(String.format("%d. %s %s",author.getId(), author.getFirstname(), author.getLastname()));
                }
            }
        });
        tfQuantity.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                this.create();
            }
        });
    }

}
