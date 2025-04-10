package ee.ivkhkdev.nptv23javafx.loaders;

import ee.ivkhkdev.nptv23javafx.controllers.SelectedAuthorFormController;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class SelectedAuthorFormModalityLoader extends AbstractFormLoader{
    private Author author;
    public SelectedAuthorFormModalityLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void load(Author author) {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/author/selectedAuthorForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
            SelectedAuthorFormController selectedAuthorFormController = fxmlLoader.getController();
            selectedAuthorFormController.setAuthor(getAuthor());
            selectedAuthorFormController.initAuthorForm();
            // Создаем модальное окно
            Stage stage = new Stage();
            stage.setTitle("Информация об авторе");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
