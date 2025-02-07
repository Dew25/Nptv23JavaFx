package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.repository.BookRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookService {
    private BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void create(Book book) {
        bookRepository.save(book);

    }
    public ObservableList<Book> getListBooks(){
        List<Book> books = bookRepository.findAll();
        ObservableList<Book> listBooks = FXCollections.observableArrayList();
        listBooks.addAll(books);
        return listBooks;
    }
}
