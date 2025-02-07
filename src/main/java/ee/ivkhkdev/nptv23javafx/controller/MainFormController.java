package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.service.BookService;
import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class MainFormController implements Initializable {

    private SpringFXMLLoader springFXMLLoader;
    private BookService bookService;

    @FXML private VBox vbMainFormRoot;
    @FXML private TableView<Book> tvListBooks;
    @FXML private TableColumn<Book, String> tcId;
    @FXML private TableColumn<Book, String> tcTitle;
    @FXML private TableColumn<Book, String> tcAuthors;
    @FXML private TableColumn<Book, String> tcPublicationYear;
    @FXML private TableColumn<Book, String> tcQuantity;
    @FXML private TableColumn<Book, String> tcCount;


    public MainFormController(SpringFXMLLoader springFXMLLoader, BookService bookService) {
        this.springFXMLLoader = springFXMLLoader;
        this.bookService = bookService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/menu/menuForm.fxml");
        try {
            Parent menuFormRoot = fxmlLoader.load();
            vbMainFormRoot.getChildren().addFirst(menuFormRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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


    }
}
