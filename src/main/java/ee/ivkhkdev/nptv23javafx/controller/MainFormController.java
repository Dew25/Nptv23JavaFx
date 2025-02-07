package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import org.springframework.stereotype.Component;

import javax.swing.text.TabableView;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MainFormController implements Initializable {
    private SpringFXMLLoader springFXMLLoader;
    @FXML private VBox vbMainFormRoot;

    public MainFormController(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/menu/menuForm.fxml");
        try {
            Parent menuRoot = fxmlLoader.load();
            vbMainFormRoot.getChildren().addFirst(menuRoot);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
