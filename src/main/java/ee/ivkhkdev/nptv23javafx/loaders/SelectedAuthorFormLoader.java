package ee.ivkhkdev.nptv23javafx.loaders;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class SelectedAuthorFormLoader extends AbstractFormLoader{
    public SelectedAuthorFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }

    @Override
    public void load() {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/author/listAuthorsForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
            Scene scene = new Scene(root);
            getPrimaryStage().setScene(scene);
            getPrimaryStage().setTitle("Список авторов");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
