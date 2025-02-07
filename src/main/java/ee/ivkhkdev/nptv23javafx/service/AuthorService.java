package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.repository.AuthorRepository;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    private AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> add(Author author) {
        return Optional.of(authorRepository.save(author));
    }
    public List<Author> getListAuthors() {
        return authorRepository.findAll();
    }

}
