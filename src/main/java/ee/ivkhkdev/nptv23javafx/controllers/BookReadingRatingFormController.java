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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
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
    private ComboBox<Integer> cbDayBefore;
    @FXML
    private ComboBox<Integer> cbDayAfter;
    @FXML
    private ComboBox<Integer> cbMonthBefore;
    @FXML
    private ComboBox<Integer> cbMonthAfter;
    @FXML
    private ComboBox<Integer> cbYearBefore;
    @FXML
    private ComboBox<Integer> cbYearAfter;
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
        if (cbYearBefore.getSelectionModel().getSelectedIndex() != -1
                && cbYearAfter.getSelectionModel().getSelectedIndex() != -1
                && cbMonthBefore.getSelectionModel().getSelectedIndex() != -1
                && cbMonthAfter.getSelectionModel().getSelectedIndex() != -1
                && cbDayBefore.getSelectionModel().getSelectedIndex() != -1
                && cbDayAfter.getSelectionModel().getSelectedIndex() != -1) {
            tvBookRatingView.setItems(
                    FXCollections.observableArrayList(
                            historyService.getRating(
                                    cbYearBefore.getSelectionModel(),
                                    cbYearAfter.getSelectionModel(),
                                    cbMonthBefore.getSelectionModel(),
                                    cbMonthAfter.getSelectionModel(),
                                    cbDayBefore.getSelectionModel(),
                                    cbDayAfter.getSelectionModel())
                    )
            );
        }else if(cbYearBefore.getSelectionModel().getSelectedIndex() != -1
                    && cbYearAfter.getSelectionModel().getSelectedIndex() != -1
                    && cbMonthBefore.getSelectionModel().getSelectedIndex() != -1
                    && cbMonthAfter.getSelectionModel().getSelectedIndex() != -1
                    && cbDayBefore.getSelectionModel().getSelectedIndex() == -1
                    && cbDayAfter.getSelectionModel().getSelectedIndex() == -1) {
                tvBookRatingView.setItems(
                        FXCollections.observableArrayList(
                                historyService.getRating(
                                        cbYearBefore.getSelectionModel(),
                                        cbYearAfter.getSelectionModel(),
                                        cbMonthBefore.getSelectionModel(),
                                        cbMonthAfter.getSelectionModel()
                                )
                        )
                );
        } else if (cbYearBefore.getSelectionModel().getSelectedIndex() != -1
                    && cbYearAfter.getSelectionModel().getSelectedIndex() != -1
                    && cbMonthBefore.getSelectionModel().getSelectedIndex() != -1
                    && cbMonthAfter.getSelectionModel().getSelectedIndex() != -1
                    && cbDayBefore.getSelectionModel().getSelectedIndex() == -1
                    && cbDayAfter.getSelectionModel().getSelectedIndex() == -1) {
                tvBookRatingView.setItems(
                        FXCollections.observableArrayList(
                                historyService.getRating(
                                        cbYearBefore.getSelectionModel(),
                                        cbYearAfter.getSelectionModel(),
                                        cbMonthBefore.getSelectionModel(),
                                        cbMonthAfter.getSelectionModel()
                                )
                        )
                );

        } else if (cbYearBefore.getSelectionModel().getSelectedIndex() != -1
                    && cbYearAfter.getSelectionModel().getSelectedIndex() != -1
                    && cbMonthBefore.getSelectionModel().getSelectedIndex() == -1
                    && cbMonthAfter.getSelectionModel().getSelectedIndex() == -1
                    && cbDayBefore.getSelectionModel().getSelectedIndex() == -1
                    && cbDayAfter.getSelectionModel().getSelectedIndex() == -1) {
                tvBookRatingView.setItems(
                        FXCollections.observableArrayList(
                                historyService.getRating(
                                        cbYearBefore.getSelectionModel(),
                                        cbYearAfter.getSelectionModel()
                                )
                        )
                );
            }
    }

    @Override
    public void initialize (URL url, ResourceBundle resourceBundle){
        for (int i = 1; i <= 31; i++) {
            cbDayBefore.getItems().add(i);
            cbDayAfter.getItems().add(i);
        }
        for (int i = 1; i <= 12; i++) {
            cbMonthBefore.getItems().add(i);
            cbMonthAfter.getItems().add(i);
        }
        for (int i = 0; i <= 2; i++) {
            Integer year = LocalDate.now().getYear();
            cbYearBefore.getItems().add(year - i);
            cbYearAfter.getItems().add(year - i);
        }
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
    }



}
