package ee.ivkhkdev.nptv23javafx.interfaces;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.rating.BookRatingViewModel;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;

import java.util.List;

public interface HistoryService extends AppService<History> {
    boolean isReadingBook(Book book);
    boolean returnBook(Book book);
    public ObservableList<History> getObservableTakenList();
    public List<BookRatingViewModel> getRating(
            SingleSelectionModel<Integer> selectionDayBefore,
            SingleSelectionModel<Integer> selectionDayAfter,
            SingleSelectionModel<Integer> selectionMonthBefore,
            SingleSelectionModel<Integer> selectionMonthAfter,
            SingleSelectionModel<Integer> selectionYearBefore,
            SingleSelectionModel<Integer> selectionYearAfter
    );
   public List<BookRatingViewModel> getRating(
            SingleSelectionModel<Integer> selectionMonthBefore,
            SingleSelectionModel<Integer> selectionMonthAfter,
            SingleSelectionModel<Integer> selectionYearBefore,
            SingleSelectionModel<Integer> selectionYearAfter
    );
   public List<BookRatingViewModel> getRating(
            SingleSelectionModel<Integer> selectionYearBefore,
            SingleSelectionModel<Integer> selectionYearAfter
    );

}
