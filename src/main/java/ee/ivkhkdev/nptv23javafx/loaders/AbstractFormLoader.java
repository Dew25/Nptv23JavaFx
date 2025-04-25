package ee.ivkhkdev.nptv23javafx.loaders;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public abstract class AbstractFormLoader{
    // в классе LoginFormLoader инициируется поле с springFXMLLoader.primaryStage
    private final SpringFXMLLoader springFXMLLoader;

    public AbstractFormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }

    // Получаем primaryStage из springFXMLLoader.getPrimaryStage() - этот метод доступен во всех наследниках (Loaders)
    public Stage getPrimaryStage(){
        return this.springFXMLLoader.getPrimaryStage();
    }
    // Этот метод используется в LoginFormLoader для передачи в springFXMLLoader primaryStage
    public void setPrimaryStage(Stage primaryStage){
        this.springFXMLLoader.setPrimaryStage(primaryStage);
    }

    protected SpringFXMLLoader getSpringFXMLLoader(){
        return springFXMLLoader;
    }

    protected void closeWindow(WindowEvent event) {
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
    // Этот абстрактный метод должен быть реализован в наследнике
    abstract public void load();
}
