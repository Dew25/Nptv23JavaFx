package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class SelectedBookFromController implements Initializable {
    @FXML private VBox vbSelectedBookRoot;
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }
    @FXML private void takeOnBook() {
        Stage stage = (Stage) vbSelectedBookRoot.getScene().getWindow();
        stage.close();
    }
    @FXML private void returnBook() {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
