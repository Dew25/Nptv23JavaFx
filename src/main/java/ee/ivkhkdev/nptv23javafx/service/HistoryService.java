package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.model.repository.BookRepository;
import ee.ivkhkdev.nptv23javafx.model.repository.HistoryRepository;
import jakarta.transaction.Transactional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class HistoryService implements ee.ivkhkdev.nptv23javafx.interfaces.HistoryService {
private final HistoryRepository historyRepository;
    private final BookRepository bookRepository;

    public HistoryService(HistoryRepository historyRepository, BookRepository bookRepository) {
        this.historyRepository = historyRepository;
        this.bookRepository = bookRepository;
    }
    @Transactional
    @Override
    public Optional<History> add(History history) {
        if(history == null || history.getBook().getCount() <= 0){
            return Optional.empty();
        }
        //Уменьшаем число книг в библиотеке на 1
        history.getBook().setCount(history.getBook().getCount() - 1);
        bookRepository.save(history.getBook());
        return Optional.of(historyRepository.save(history));
    }

    @Override
    public List<History> getList() {
        return historyRepository.findAll();
    }

    public ObservableList<History> getObservableTakedList() {
        ObservableList<History> observableList = FXCollections.observableArrayList();
        for (History history : getList()) {
            if(history.getReturnDate() == null){
                observableList.add(history);
            }
        }
        return observableList;
    }
}
