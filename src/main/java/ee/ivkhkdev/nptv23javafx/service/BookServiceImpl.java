package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.repository.AuthorRepository;
import ee.ivkhkdev.nptv23javafx.model.repository.BookRepository;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private SessionManager sessionManager;

    public BookServiceImpl( SessionManager sessionManager, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.sessionManager = sessionManager;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Book> add(Book book) {
        try {
            if(sessionManager.isLoggedIn() && sessionManager.hasRole(Role.MANAGER)){
                Book addedBook = bookRepository.save(book);
                return Optional.of(addedBook);
            }else{
                return Optional.empty();
            }
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Book> getObservableList(){
        ObservableList<Book> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.getList());
        return observableList;
    }

    @Override
    public List<Book> getList() {
        if (sessionManager.isLoggedIn() && sessionManager.hasRole(Role.USER)) {
            return bookRepository.findAll();
        } else {
            return new ArrayList<>();
        }
    }



}
