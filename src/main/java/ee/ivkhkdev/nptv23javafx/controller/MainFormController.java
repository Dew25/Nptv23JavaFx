package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.service.AppUserServiceImpl;
import ee.ivkhkdev.nptv23javafx.service.BookServiceImpl;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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

    private FormLoader formLoader;
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
    @FXML private Label lbInfo;


    public MainFormController(FormLoader formLoader, BookService bookService) {
        this.formLoader = formLoader;
        this.bookService = bookService;
    }

    @FXML private void showEditBookForm() {
        formLoader.loadEditBookForm(tvListBooks.getSelectionModel().getSelectedItem());
    }
    private void openBookDetails(Book book) {
        formLoader.loadSelectedBookFormModality(book);
    }


    public void setInfoMessage(String message){
        lbInfo.setText(message);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Добавляем форму меню первым элементом vbMainFormRoot
        vbMainFormRoot.getChildren().addFirst(formLoader.loadMenuForm());

        tvListBooks.setItems(bookService.getObservableList());
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
                    if(AppUserServiceImpl.currentUser.getRoles().contains(AppUserServiceImpl.ROLES.MANAGER.toString())){
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
