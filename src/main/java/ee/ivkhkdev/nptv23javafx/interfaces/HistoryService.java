package ee.ivkhkdev.nptv23javafx.interfaces;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;

public interface HistoryService extends AppService<History> {
    boolean reading(Book book);
    History findByBookAndUser(Book book, AppUser currentUser);
}
