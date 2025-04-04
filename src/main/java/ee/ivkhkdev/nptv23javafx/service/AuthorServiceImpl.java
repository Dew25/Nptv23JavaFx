package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.interfaces.AuthorService;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.repository.AuthorRepository;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private SessionManager sessionManager;
    private AuthorRepository authorRepository;

    public AuthorServiceImpl(SessionManager sessionManager, AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> add(Author author) {
        //if(sessionManager.isLoggedIn() && sessionManager.hasRole(role.MANAGER)){
            return Optional.of(authorRepository.save(author));
//        }else{
//            return Optional.empty();
//        }

    }
    public List<Author> getList() {
//        if(sessionManager.isLoggedIn() && sessionManager.hasRole(role.USER)){
            return authorRepository.findAll();
//        }else{
//            return new ArrayList<>();
//        }
    }

    @Override
    public ObservableList<Author> getObservableList() {
        ObservableList<Author> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.getList());
        return observableList;
    }
}
