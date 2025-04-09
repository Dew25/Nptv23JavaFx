package ee.ivkhkdev.nptv23javafx.loaders;

import ee.ivkhkdev.nptv23javafx.controller.EditBookFormController;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class EditBookFormLoader extends AbstractFormLoader{
    public EditBookFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load() {
        throw  new UnsupportedOperationException("Not supported yet.");
    }
    public void load(Book selectedBook) {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/book/editBookForm.fxml");
        try {
            Parent editBookFormRoot = fxmlLoader.load();
            EditBookFormController editBookFormController = fxmlLoader.getController();
            editBookFormController.setEditBook(selectedBook);
            Scene scene = new Scene(editBookFormRoot);
            getPrimaryStage().setTitle("Nptv23JavaFX Библиотека - Редактирование книги");
            getPrimaryStage().setScene(scene);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
