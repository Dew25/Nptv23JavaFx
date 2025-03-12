package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import org.springframework.stereotype.Component;

import javafx.scene.control.TableView;

import java.util.stream.Collectors;

@Component
public class CellFactoryService {
    public Callback<ListView<Author>, ListCell<Author>> createAuthorListCellFactory() {
        return lv -> new ListCell<>() {
            @Override
            protected void updateItem(Author author, boolean empty) {
                super.updateItem(author, empty);
                if (empty || author == null) {
                    setText(null);
                } else {
                    setText(author.getId() + ". " + author.getFirstname() + " " + author.getLastname());
                }
            }
        };
    }
    public Callback<ListView<Book>, ListCell<Book>> createBookListCellFactory() {
        return lv -> new ListCell<>() {
            @Override
            protected void updateItem(Book book, boolean empty) {
                super.updateItem(book, empty);
                if (empty || book == null) {
                    setText(null);
                } else {
                    setText(book.getId() + ". " + book.getTitle() + ". " + book.getPublicationYear());
                }
            }
        };
    }
    public Callback<TableColumn.CellDataFeatures<Book, String>, ObservableValue<String>> createAuthorsCellValueFactory() {
        return cellData -> {
            Book book = cellData.getValue();
            if (book.getAuthors() == null || book.getAuthors().isEmpty()) {
                return new SimpleStringProperty("");
            }
            String authors = book.getAuthors().stream()
                    .map(author -> author.getFirstname() + " " + author.getLastname())
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(authors);
        };
    }

}

