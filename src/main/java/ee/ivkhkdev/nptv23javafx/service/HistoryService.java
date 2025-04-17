package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.model.repository.BookRepository;
import ee.ivkhkdev.nptv23javafx.model.repository.HistoryRepository;
import ee.ivkhkdev.nptv23javafx.rating.BookRatingViewModel;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import jakarta.transaction.Transactional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;
import org.springframework.stereotype.Service;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HistoryService implements ee.ivkhkdev.nptv23javafx.interfaces.HistoryService {
private final HistoryRepository historyRepository;
private final BookRepository bookRepository;
    private final SessionManager sessionManager;

    public HistoryService(HistoryRepository historyRepository, BookRepository bookRepository, SessionManager sessionManager) {
        this.historyRepository = historyRepository;
        this.bookRepository = bookRepository;
        this.sessionManager = sessionManager;
    }
    @Transactional
    @Override
    public Optional<History> add(History history) {
        if(sessionManager.isLoggedIn() || sessionManager.getCurrentUser().getRoles().contains(Role.USER.toString())){
            if(history == null || history.getBook().getCount() < 1){
                return Optional.empty();
            }
            //Уменьшаем число книг в библиотеке на 1
            history.getBook().setCount(history.getBook().getCount() - 1);
            bookRepository.save(history.getBook());
            return Optional.of(historyRepository.save(history));
        }else{
            return Optional.empty();
        }
    }

    @Override
    public List<History> getList() {
        if(sessionManager.isLoggedIn() || sessionManager.getCurrentUser().getRoles().contains(Role.USER.toString())){}
        return historyRepository.findAll();
    }

    @Override
    public ObservableList<History> getObservableList() {
        ObservableList<History> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.getList());
        return observableList;
    }

    public ObservableList<History> getObservableTakenList() {
        ObservableList<History> observableList = FXCollections.observableArrayList();
        for (History history : getList()) {
            if(history.getReturnDate() == null){
                observableList.add(history);
            }
        }
        return observableList;
    }

    @Override
    public List<BookRatingViewModel> getRating(LocalDate from, LocalDate to) {
        List<History> results = historyRepository.findByTakeOnDateBetween(from, to);
        Map<Book, Integer> map = new HashMap<>();
        for(History history : results){
            Book book = history.getBook();
            map.put(book, map.getOrDefault(book, 0) + 1);
        }
        List<BookRatingViewModel> ratingList = map.entrySet().stream()
                .map(entry ->new BookRatingViewModel(entry.getKey(),entry.getValue()))
                .sorted(Comparator.comparing(BookRatingViewModel::getCount).reversed())
                .collect(Collectors.toList());
        return ratingList;
    }



    public boolean isReadingBook(Book book) {
        if(sessionManager.getCurrentUser() == null){
            return false;
        }
        List<History> listHistoryWithReadingBook = historyRepository.findByBook_IdAndAppUser_IdAndReturnDate(book.getId(), sessionManager.getCurrentUser().getId(), null);
        for(History history : listHistoryWithReadingBook){
            if(history.getReturnDate() == null){
                return true;
            }
        }
        return false;
    }
    @Transactional
    @Override
    public boolean returnBook(Book book) {
        if(sessionManager.isLoggedIn() || sessionManager.getCurrentUser().getRoles().contains(Role.USER.toString())){}
        List<History> listHistory = historyRepository.findByBook_IdAndAppUser_IdAndReturnDate(book.getId(), sessionManager.getCurrentUser().getId(), null);
        for (History history : listHistory) {
            if (history.getBook().getCount() < history.getBook().getQuantity()){
                history.setReturnDate(LocalDate.now());
                history.getBook().setCount(history.getBook().getCount() + 1);
                historyRepository.save(history);
                return true;
            }
        }
        return false;
    }
}
