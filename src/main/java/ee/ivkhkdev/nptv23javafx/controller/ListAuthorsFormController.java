package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.service.CellFactoryService;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class ListAuthorsFormController implements Initializable {
    private FormLoader formLoader;
    private AuthorService authorService;
    private final CellFactoryService cellFactoryService;
    @FXML private ListView<Author> lvListAuthors;
    @FXML private ListView<Book> lvAuthorBooks;
    @FXML private Label lbInfo;
    @FXML private VBox vbListSelectedAuthorsBooks;

    public ListAuthorsFormController(AuthorService authorService, FormLoader formLoader, CellFactoryService cellFactoryService) {
        this.authorService = authorService;
        this.formLoader = formLoader;
        this.cellFactoryService = cellFactoryService;
    }
    @FXML private void goToMainForm(){
        formLoader.loadMainForm();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvListAuthors.getItems().setAll(authorService.getObservableList());
        lvListAuthors.setCellFactory(cellFactoryService.createAuthorListCellFactory());
        lvAuthorBooks.setCellFactory(cellFactoryService.createBookListCellFactory());
        lvListAuthors.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !lvListAuthors.getSelectionModel().isEmpty()) {
                Author selectedAuthor = lvListAuthors.getSelectionModel().getSelectedItem();
                lvAuthorBooks.getItems().clear();
                ObservableList<Book> observableList = FXCollections.observableArrayList();
                observableList.setAll(selectedAuthor.getBooks());
                lvAuthorBooks.setItems(observableList);
                vbListSelectedAuthorsBooks.setVisible(true);
            }else if (event.getClickCount() == 2 && !lvListAuthors.getSelectionModel().isEmpty()) {
                formLoader.loadSelectedAuthorFormModality(lvListAuthors.getSelectionModel().getSelectedItem());
            }
        });

    }
}
