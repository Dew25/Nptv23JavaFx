package ee.ivkhkdev.nptv23javafx.interfaces;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import javafx.collections.ObservableList;

public interface HistoryService extends AppService<History> {
    boolean isReadingBook(Book book);
    boolean returnBook(Book book);
    public ObservableList<History> getObservableTakenList();
}
