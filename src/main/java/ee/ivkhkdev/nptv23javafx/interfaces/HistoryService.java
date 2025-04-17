package ee.ivkhkdev.nptv23javafx.interfaces;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.rating.BookRatingViewModel;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.util.List;

public interface HistoryService extends AppService<History> {
    boolean isReadingBook(Book book);
    boolean returnBook(Book book);
    ObservableList<History> getObservableTakenList();
    List<BookRatingViewModel> getRating(LocalDate from, LocalDate to);
}
