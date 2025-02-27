package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.service.AppUserService;
import ee.ivkhkdev.nptv23javafx.service.BookService;
import ee.ivkhkdev.nptv23javafx.service.FormService;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class MainFormController implements Initializable {

    private FormService formService;
    private BookService bookService;

    @FXML private VBox vbMainFormRoot;
    @FXML private TableView<Book> tvListBooks;
    @FXML private TableColumn<Book, String> tcId;
    @FXML private TableColumn<Book, String> tcTitle;
    @FXML private TableColumn<Book, String> tcAuthors;
    @FXML private TableColumn<Book, String> tcPublicationYear;
    @FXML private TableColumn<Book, String> tcQuantity;
    @FXML private TableColumn<Book, String> tcCount;
    @FXML private HBox hbEditBook;


    public MainFormController(FormService formService, BookService bookService) {
        this.formService = formService;
        this.bookService = bookService;
    }

    @FXML private void showEditBookForm() {
        formService.loadEditBookForm(tvListBooks.getSelectionModel().getSelectedItem());
    }
    private void openBookDetails(Book book) {
        formService.loadSelectedBookFormModality(book);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        vbMainFormRoot.getChildren().addFirst(formService.loadMenuForm());
        tvListBooks.setItems(bookService.getListBooks());
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcAuthors.setCellValueFactory(cellData -> {
            Book book = cellData.getValue(); // Получаем объект Book из строки таблицы
            if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
                return new SimpleStringProperty("");
            }
            // Преобразуем коллекцию авторов в строку
            String authors = book.getAuthors().stream()
                    .map(author -> author.getFirstname() + " " + author.getLastname())
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(authors);
        });
        tcPublicationYear.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        tvListBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
                if (newValue != null) {
                    if(AppUserService.currentUser.getRoles().contains(AppUserService.ROLES.MANAGER.toString())){
                        hbEditBook.setVisible(true);
                    }else{
                        hbEditBook.setVisible(false);
                    }
                }
            }
        });
         // Обработка двойного клика
        tvListBooks.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !tvListBooks.getSelectionModel().isEmpty()) {
                Book selectedBook = tvListBooks.getSelectionModel().getSelectedItem();
                openBookDetails(selectedBook);
            }
        });
    }
}
