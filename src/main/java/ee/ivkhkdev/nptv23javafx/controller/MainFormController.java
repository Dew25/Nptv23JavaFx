package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.repository.HistoryRepository;
import ee.ivkhkdev.nptv23javafx.service.CellFactoryService;
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
    private final CellFactoryService cellFactoryService;
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


    public MainFormController(FormLoader formLoader, BookService bookService, CellFactoryService cellFactoryService) {
        this.formLoader = formLoader;
        this.bookService = bookService;
        this.cellFactoryService = cellFactoryService;
    }

    @FXML private void showEditBookForm() {
        formLoader.loadEditBookForm(tvListBooks.getSelectionModel().getSelectedItem());
    }
    private void openBookDetails(Book book) {
        formLoader.loadSelectedBookFormModality(book);
    }

    public void reloadTableView(){
        tvListBooks.setItems(bookService.getObservableList());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Добавляем форму меню первым элементом vbMainFormRoot
        vbMainFormRoot.getChildren().addFirst(formLoader.loadMenuForm());
        // Инициируем список книг
        reloadTableView();
        // Настраиваем отображение полей книги в столбцах таблицы
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        // Авторы -> в строку преобразовыаем в виде Имя Фамилия
        tcAuthors.setCellValueFactory(cellFactoryService.createAuthorsCellValueFactory());
        tcPublicationYear.setCellValueFactory(new PropertyValueFactory<>("publicationYear"));
        tcQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        tcCount.setCellValueFactory(new PropertyValueFactory<>("count"));
        // Навешиваем на таблицу книг обработчик события клика мышкой
        // (логика: при клике проверяет роль и показывает или скрывает HBox с кнопкой Редактировать)
        tvListBooks.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Book>() {
            @Override
            public void changed(ObservableValue<? extends Book> observable, Book oldValue, Book newValue) {
                if (newValue != null) {
                    if(Nptv23JavaFxApplication.currentUser.getRoles().contains(Nptv23JavaFxApplication.ROLES.MANAGER.toString())){
                        hbEditBook.setVisible(true);
                    }else{
                        hbEditBook.setVisible(false);
                    }
                }
            }
        });
         // Обработка двойного клика -> показывает модальное окно с выбранной книгой
        tvListBooks.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && !tvListBooks.getSelectionModel().isEmpty()) {
                Book selectedBook = tvListBooks.getSelectionModel().getSelectedItem();
                try {
                    openBookDetails(selectedBook);
                    reloadTableView();
                    lbInfo.setText(
                        selectedBook.getTitle()
                        + " - выдана пользователю " + Nptv23JavaFxApplication.currentUser.getFirstname()
                        + " " + Nptv23JavaFxApplication.currentUser.getLastname()
                    );
                }catch (Exception e){
                    lbInfo.setText(selectedBook.getTitle() + " - выдать не удалось");
                }
            }
        });

    }
}
