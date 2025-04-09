package ee.ivkhkdev.nptv23javafx.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class NewBookFormLoader extends AbstractFormLoader{
    public NewBookFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load() {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/book/newBookForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Создание новой книги");
    }
}
