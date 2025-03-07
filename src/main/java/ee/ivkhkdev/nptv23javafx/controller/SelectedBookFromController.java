package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.HistoryService;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SelectedBookFromController implements Initializable {
    private HistoryService historyService;
    @FXML private VBox vbSelectedBookRoot;
    private Book book;

    public SelectedBookFromController(HistoryService historyService) {
        this.historyService = historyService;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
    @FXML private void takeOnBook() {
        History history = new History();
        if(book != null && book.getCount() > 0) {
            book.setCount(book.getCount() - 1);
        }
        history.setBook(book);
        try {
            historyService.add(history);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) vbSelectedBookRoot.getScene().getWindow();
        stage.close();
    }
    @FXML private void returnBook() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
