package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import ee.ivkhkdev.nptv23javafx.interfaces.HistoryService;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.model.entity.Session;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class SelectedBookFromModalityController implements Initializable {
    private Role role;
    private SessionManager sessionManager;
    private final HistoryService historyService;
    private final AppUserService appUserService;
    private final MainFormController mainFormController;
    @FXML private VBox vbSelectedBookRoot;
    @FXML private Button btTakeOnBook;
    @FXML private Button btReturnBook;
    private Book book;

    public SelectedBookFromModalityController(Role role, SessionManager sessionManager, HistoryService historyService, AppUserService appUserService, MainFormController mainFormController) {
        this.role = role;
        this.sessionManager = sessionManager;
        this.historyService = historyService;
        this.appUserService = appUserService;
        this.mainFormController = mainFormController;
    }

    public void setBook(Book book) {
        this.book = book;
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

    }
}
