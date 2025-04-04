package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.entity.History;
import ee.ivkhkdev.nptv23javafx.model.entity.Session;
import ee.ivkhkdev.nptv23javafx.model.repository.AppUserRepository;
import ee.ivkhkdev.nptv23javafx.model.repository.BookRepository;
import ee.ivkhkdev.nptv23javafx.model.repository.HistoryRepository;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import jakarta.transaction.Transactional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class HistoryService implements ee.ivkhkdev.nptv23javafx.interfaces.HistoryService {
private final HistoryRepository historyRepository;
    private final BookRepository bookRepository;
    private final SessionManager sessionManager;
    private Role role;


    public HistoryService(HistoryRepository historyRepository, BookRepository bookRepository, SessionManager sessionManager) {
        this.historyRepository = historyRepository;
        this.bookRepository = bookRepository;

        this.sessionManager = sessionManager;
    }
    @Transactional
    @Override
    public Optional<History> add(History history) {
        if(sessionManager.isLoggedIn() || sessionManager.getCurrentUser().getRoles().contains(Role.USER.toString())){
            if(history == null || history.getBook().getCount() <= 0){
                return Optional.empty();
            }
            //Уменьшаем число книг в библиотеке на 1
            history.getBook().setCount(history.getBook().getCount() - 1);
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

    public ObservableList<History> getObservableTakenList() {
        ObservableList<History> observableList = FXCollections.observableArrayList();
        for (History history : getList()) {
            if(history.getReturnDate() == null){
                observableList.add(history);
            }
        }
        return observableList;
    }

    public boolean isReadingBook(Book book) {
        if(sessionManager.getCurrentUser() == null){
            return false;
        }
        List<History> listHistoryWithReadingBook = historyRepository.findByBook_IdAndAppUser_Id(book.getId(), sessionManager.getCurrentUser().getId());
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
        List<History> listHistory = historyRepository.findByBook_IdAndAppUser_Id(book.getId(), sessionManager.getCurrentUser().getId());
        for (History history : listHistory) {
            if (history.getReturnDate() == null && history.getBook().getQuantity() > history.getBook().getCount()){
                history.setReturnDate(LocalDate.now());
                history.getBook().setCount(history.getBook().getCount() + 1);
                historyRepository.save(history);
                return true;
            }
        }
        return false;
    }
}
