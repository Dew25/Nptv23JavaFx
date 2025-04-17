package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.rating.BookRatingViewModel;
import ee.ivkhkdev.nptv23javafx.service.HistoryService;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class BookReadingRatingFormController implements Initializable {
    private final HistoryService historyService;
    private final MainFormLoader mainFormLoader;
    @FXML
    private Label lbInfo;
    @FXML
    private DatePicker dpBeforeDate;
    @FXML
    private DatePicker dpAfterDate;
    @FXML
    private TableView<BookRatingViewModel> tvBookRatingView;
    @FXML
    private TableColumn<BookRatingViewModel, String> tcId;
    @FXML
    private TableColumn<BookRatingViewModel, String> tcTitle;
    @FXML
    private TableColumn<BookRatingViewModel, String> tcAuthors;
    @FXML
    private TableColumn<BookRatingViewModel, String> tcPublishedYear;
    @FXML
    private TableColumn<BookRatingViewModel, String> tcRating;

    public BookReadingRatingFormController(HistoryService historyService, MainFormLoader mainFormLoader) {
        this.historyService = historyService;
        this.mainFormLoader = mainFormLoader;
    }
    @FXML
    private void goToMainForm(){
        mainFormLoader.load();
    }

    @FXML
    private void calculateRating() {
        LocalDate from = dpBeforeDate.getValue();
        LocalDate to = dpAfterDate.getValue();
        if (from == null || to == null) {
            lbInfo.setText("Пожалуйста, выберите обе даты.");
            return;
        }
        lbInfo.setText("Выберите диапазон дат");
        tvBookRatingView.setItems(FXCollections.observableArrayList(historyService.getRating(from,to)));
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        //Настраиваем отображение столбцов в таблице
        tcId.setCellValueFactory(sellData -> new SimpleStringProperty(sellData.getValue().getBook().getId().toString()));
        tcTitle.setCellValueFactory(sellData -> new SimpleStringProperty(sellData.getValue().getTitle()));
        tcAuthors.setCellValueFactory(cellData -> {
            String authors = cellData.getValue().getBook().getAuthors().stream()
                    .map(author -> author.getFirstname() + " " + author.getLastname())
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(authors);
        });
        tcPublishedYear.setCellValueFactory(sellData -> new SimpleStringProperty(((Integer) sellData.getValue().getBook().getPublicationYear()).toString()));
        tcRating.setCellValueFactory(sellData -> new SimpleStringProperty(sellData.getValue().getCount().toString()));
        //Настраиваем отображение даты в даты pfAfterDate
        dpAfterDate.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate item, boolean empty) {
                super.updateItem(item, empty);
                if (dpBeforeDate.getValue() != null && item.isBefore(dpBeforeDate.getValue())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #ffc0cb;");
                }
            }
        });
    }



}
