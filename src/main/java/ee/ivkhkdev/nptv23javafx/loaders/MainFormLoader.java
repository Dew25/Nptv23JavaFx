package ee.ivkhkdev.nptv23javafx.loaders;

import ee.ivkhkdev.nptv23javafx.controllers.MainFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class MainFormLoader extends AbstractFormLoader{
    public MainFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }
    public void load(){
        this.load("");
    }
    public void load(String message) {
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/main/mainForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
            MainFormController mainFormController = fxmlLoader.getController();
            if(message!=null && !message.isEmpty()){
                mainFormController.setInfoMessage(message);
            }
            mainFormController.initTableView();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getPrimaryStage().setOnCloseRequest(this::handle);
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Nptv23JavaFX Библиотека");
    }
}
