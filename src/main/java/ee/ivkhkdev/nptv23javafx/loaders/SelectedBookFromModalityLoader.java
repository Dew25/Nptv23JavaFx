package ee.ivkhkdev.nptv23javafx.loaders;

import ee.ivkhkdev.nptv23javafx.controllers.SelectedBookFromModalityController;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class SelectedBookFromModalityLoader extends AbstractFormLoader{
    public SelectedBookFromModalityLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load() { throw  new UnsupportedOperationException("Not supported yet."); }
    public void load(Book book, boolean readingBook) {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/book/selectedBookFormModality.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
            SelectedBookFromModalityController selectedBookFromController = fxmlLoader.getController();
            selectedBookFromController.setBook(book);
            selectedBookFromController.setVizibleButtons(readingBook);
            // Создаем модальное окно
            Stage stage = new Stage();
            stage.setTitle("Информация о книге");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
