package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.interfaces.HistoryService;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class SelectedBookFromModalityController implements Initializable {
    private final HistoryService historyService;
    @FXML private VBox vbSelectedBookRoot;
    @FXML private Button btTakeOnBook;
    @FXML private Button btReturnBook;
    private Book book;

    public SelectedBookFromModalityController(HistoryService historyService) {
        this.historyService = historyService;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @FXML private void takeOnBook() {
        History history = new History();
        history.setBook(book);
        history.setAppUser(Nptv23JavaFxApplication.currentUser);
        history.setTakeOnDate(LocalDate.now());
        try {
            historyService.add(history);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        closeModalityWindows();
    }
    @FXML private void returnBook() {
        historyService.returnBook(book);
        closeModalityWindows();
    }
    private void closeModalityWindows(){
        Stage stage = (Stage) vbSelectedBookRoot.getScene().getWindow();
        stage.close();
    }

    public void setVizibleButtons(boolean readingBook) {
        if (readingBook) {
            btTakeOnBook.setVisible(false);
            btReturnBook.setVisible(true);
        }else{
            btTakeOnBook.setVisible(true);
            btReturnBook.setVisible(false);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
