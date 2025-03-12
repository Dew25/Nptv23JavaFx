package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.interfaces.BookService;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.repository.AuthorRepository;
import ee.ivkhkdev.nptv23javafx.model.repository.BookRepository;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class BookServiceImpl implements BookService {
    private final HistoryService historyService;
    private final AuthorRepository authorRepository;
    private BookRepository bookRepository;
    public BookServiceImpl(HistoryService historyService, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.historyService = historyService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public Optional<Book> add(Book book) {
        try {
            Book addedBook = bookRepository.save(book);
            return Optional.of(addedBook);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ObservableList<Book> getObservableList(){
        ObservableList<Book> observableList = FXCollections.observableArrayList();
        // Создаем фоновую задачу
        Task<List<Book>> task = new Task<>() {
            @Override
            protected List<Book> call() throws Exception {
                return bookRepository.findAll(); // Загрузка данных в фоновом потоке
            }
        };
        task.setOnSucceeded(event -> {
            Platform.runLater(() -> {
                observableList.addAll(task.getValue()); // Обновляем ObservableList в UI-потоке
            });
        });
        observableList.addAll(this.getList());
        // Запуск задачи в фоновом потоке
        new Thread(task).start();
        return observableList;
    }



    @Override
    public List<Book> getList() {
        return bookRepository.findAll();
    }


}
