package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.controllers.helper.CoverImageChoose;
import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.loaders.NewBookFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import org.springframework.stereotype.Component;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

@Component
public class NewBookFormController implements Initializable {
    private final MainFormLoader mainFormLoader;
    private final BookService bookService;
    private final AuthorService authorService;
    private File selectedCoverFile;
    private CoverImageChoose coverImageChoose;
    @FXML private Label lbInfo;
    @FXML private TextField tfTitle;
    @FXML private ListView<Author> lvAuthors;
    @FXML private TextField tfPublicationYear;
    @FXML private TextField tfQuantity;
    @FXML private Label lbChooseCoverImage;
    @FXML private Button btChooseCoverImage;

    public NewBookFormController(MainFormLoader mainFormLoader, BookService bookService, AuthorService authorService, CoverImageChoose coverImageChoose) {
        this.mainFormLoader = mainFormLoader;
        this.bookService = bookService;
        this.authorService = authorService;
        this.coverImageChoose = coverImageChoose;
    }
    @FXML
    private void chooseCoverImage() {
        selectedCoverFile = coverImageChoose.getSelectedCoverFile(tfTitle.getScene().getWindow());
        if (selectedCoverFile != null) {
            lbChooseCoverImage.setText("Файл выбран: " + selectedCoverFile.getName());
            lbChooseCoverImage.setVisible(true);
        }
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
        book.setCoverImage(selectedCoverFile);
        bookService.add(book);
        mainFormLoader.load();
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
