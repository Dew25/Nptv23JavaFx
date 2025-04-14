package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.interfaces.HistoryService;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

@Component
public class SelectedBookFromModalityController implements Initializable {

    private SessionManager sessionManager;
    private final HistoryService historyService;
    private final MainFormController mainFormController;
    @FXML private VBox vbSelectedBookRoot;
    @FXML private Button btTakeOnBook;
    @FXML private Button btReturnBook;
    @FXML private ImageView ivCoverBook;
    private Book book;

    public SelectedBookFromModalityController(SessionManager sessionManager, HistoryService historyService, MainFormController mainFormController) {
        this.sessionManager = sessionManager;
        this.historyService = historyService;
        this.mainFormController = mainFormController;
    }

    public void setBook(Book book) {
        this.book = book;
        this.ivCoverBook.setImage(book.getCoverImage());
    }

    @FXML
    private void takeOnBook() {
        try {
            History history = new History();
            history.setBook(book);
            history.setAppUser(sessionManager.getCurrentUser());
            history.setTakeOnDate(LocalDate.now());
            historyService.add(history);
            closeModalityWindows();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @FXML private void returnBook() {
        historyService.returnBook(book);
        mainFormController.initTableView();
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
        ivCoverBook.setFitWidth(320);
        ivCoverBook.setFitHeight(400);
        ivCoverBook.setPreserveRatio(false);
        ivCoverBook.setSmooth(true);


    }
}
