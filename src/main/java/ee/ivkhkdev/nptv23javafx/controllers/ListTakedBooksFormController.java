package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.service.HistoryService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import org.springframework.stereotype.Component;

import java.net.URL;

import java.util.ResourceBundle;

@Component
public class ListTakedBooksFormController implements Initializable {
    private MainFormLoader mainFormLoader;
    private HistoryService historyService;
    @FXML private ListView<History> lvTackedBookRoot;

    public ListTakedBooksFormController(MainFormLoader mainFormLoader, HistoryService historyService) {
        this.mainFormLoader = mainFormLoader;
        this.historyService = historyService;
    }

    @FXML private void goToMainForm(){
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lvTackedBookRoot.setItems(historyService.getObservableTakenList());
        lvTackedBookRoot.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(History history, boolean empty) {
                super.updateItem(history, empty);
                if (empty || history == null) {
                    setText(null);
                } else {
                    setText(
                            history.getId().toString()
                            + ". " + history.getBook().getTitle()
                            + ". Читает: "+ history.getAppUser().getFirstname()
                            + " " + history.getAppUser().getLastname()
                            + ". Выдана: "+history.getTakeOnDate().toString()
                    );
                }
            }
        });
    }
}
