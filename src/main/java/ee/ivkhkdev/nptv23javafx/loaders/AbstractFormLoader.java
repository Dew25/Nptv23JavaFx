package ee.ivkhkdev.nptv23javafx.loaders;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class AbstractFormLoader{
    private final SpringFXMLLoader springFXMLLoader;

    public AbstractFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }
    protected Stage getPrimaryStage(){
        return Nptv23JavaFxApplication.primaryStage;
    }
    protected SpringFXMLLoader getSpringFXMLLoader(){
        return springFXMLLoader;
    }
    protected void handle(WindowEvent event) {
        // Можно показать диалог подтверждения перед закрытием
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Подтверждение");
        alert.setHeaderText("Вы уверены, что хотите закрыть приложение?");
        alert.setContentText("Нажмите ОК для закрытия или Отмена для продолжения.");

        // Если пользователь нажимает "ОК", закрываем окно
        if (alert.showAndWait().get() == ButtonType.OK) {
            Platform.exit(); // Закрываем приложение
        } else {
            event.consume(); // Отменяем событие закрытия (не закрываем окно)
        }
    }
    abstract public void load();
}
