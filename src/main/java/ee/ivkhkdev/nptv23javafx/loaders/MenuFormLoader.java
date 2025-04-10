package ee.ivkhkdev.nptv23javafx.loaders;

import ee.ivkhkdev.nptv23javafx.controllers.MenuFormController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Component
public class MenuFormLoader extends AbstractFormLoader{
    private MenuFormController menuFormController;
    public MenuFormLoader(SpringFXMLLoader springFXMLLoader) {
        super(springFXMLLoader);
    }
    @Override
    public void load(){
        FXMLLoader fxmlLoader = getSpringFXMLLoader().load("/view/menu/menuForm.fxml");
        try {
            Parent root = fxmlLoader.load();
            menuFormController = fxmlLoader.getController();
            menuFormController.setRootMenuForm(root);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public MenuFormController getMenuFormController() {
        return menuFormController;
    }
}
