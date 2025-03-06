package ee.ivkhkdev.nptv23javafx.tools;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.controller.EditBookFormController;
import ee.ivkhkdev.nptv23javafx.controller.ListAuthorsFormController;
import ee.ivkhkdev.nptv23javafx.controller.SelectedAuthorFormController;
import ee.ivkhkdev.nptv23javafx.controller.SelectedBookFromController;
import ee.ivkhkdev.nptv23javafx.model.entity.Author;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormLoader {
    private final SpringFXMLLoader springFXMLLoader;


    public FormLoader(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;

    }
    public void loadLoginForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/user/loginForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setResizable(false);//Делаем окно с неизменяемым размером
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Nptv23JavaFX вход пользователя");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
    public void loadMainForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/main/mainForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        getPrimaryStage().setOnCloseRequest(event -> {
            // Можно показать диалог подтверждения перед закрытием
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Подтверждение");
            alert.setHeaderText("Вы уверены, что хотите закрыть приложение?");
            alert.setContentText("Нажмите ОК для закрытия или Отмена для продолжения.");

            // Если пользователь нажимает "ОК", закрываем окно
            if (alert.showAndWait().get() == javafx.scene.control.ButtonType.OK) {
                Platform.exit(); // Закрываем приложение
            } else {
                event.consume(); // Отменяем событие закрытия (не закрываем окно)
            }
        });
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Nptv23JavaFX Библиотека");

    }
    private Stage getPrimaryStage(){
        return Nptv23JavaFxApplication.primaryStage;
    }
    public void loadNewBookForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/book/newBookForm.fxml");
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
    public void loadEditBookForm(Book selectedBook) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/book/editBookForm.fxml");
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
    public Parent loadMenuForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/menu/menuForm.fxml");
        try {
            return fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void loadAuthorForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/author/AuthorForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Создание нового автора");
    }

    public void loadRegistrationForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/user/registrationForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Создание нового пользователя");
    }

    public void loadSelectedBookFormModality(Book book) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/book/selectedBookForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
            SelectedBookFromController selectedBookFromController = fxmlLoader.getController();
            selectedBookFromController.setBook(book);
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
    public void loadListAuthorForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/author/listAuthorsForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
            ListAuthorsFormController listAuthorsFormController = fxmlLoader.getController();
            Scene scene = new Scene(root);
            getPrimaryStage().setScene(scene);
            getPrimaryStage().setTitle("Список авторов");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadSelectedAuthorFormModality(Author author) {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/view/author/selectedAuthorForm.fxml");
        Parent root;
        try {
            root = fxmlLoader.load();
            SelectedAuthorFormController selectedAuthorFormController = fxmlLoader.getController();
            selectedAuthorFormController.setAuthor(author);
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
}
