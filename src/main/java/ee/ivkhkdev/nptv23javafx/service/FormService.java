package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.controller.EditBookFormController;
import ee.ivkhkdev.nptv23javafx.controller.SelectedBookFromController;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.tools.SpringFXMLLoader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class FormService {
    private SpringFXMLLoader springFXMLLoader;

    public FormService(SpringFXMLLoader springFXMLLoader) {
        this.springFXMLLoader = springFXMLLoader;
    }
    public void loadLoginForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/user/loginForm.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Nptv23JavaFX вход пользователя");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
    public void loadMainForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/main/mainForm.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Nptv23JavaFX Библиотека");
        getPrimaryStage().centerOnScreen();
        getPrimaryStage().show();
    }
    private Stage getPrimaryStage(){
        return Nptv23JavaFxApplication.primaryStage;
    }
    public void loadNewBookForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/book/newBookForm.fxml");
        Parent root = null;
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
        FXMLLoader fxmlLoader = springFXMLLoader.load("/book/editBookForm.fxml");
        try {
            Parent editBookFormRoot = fxmlLoader.load();
            EditBookFormController editBookFormController = fxmlLoader.getController();
            editBookFormController.setEditBook(selectedBook);
            Scene scene = new Scene(editBookFormRoot);
            getPrimaryStage().setTitle("Nptv23JavaFX Библиотека - Редактирование книги");
            getPrimaryStage().setScene(scene);
            getPrimaryStage().setResizable(false);
            getPrimaryStage().centerOnScreen();
            getPrimaryStage().show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Parent loadMenuForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/menu/menuForm.fxml");
        try {
            return fxmlLoader.load();
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public void loadAuthorForm(){
        FXMLLoader fxmlLoader = springFXMLLoader.load("/author/AuthorForm.fxml");
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        getPrimaryStage().setScene(scene);
        getPrimaryStage().setTitle("Создание нового пользователя");
    }

    public void loadRegistrationForm() {
        FXMLLoader fxmlLoader = springFXMLLoader.load("/user/registrationForm.fxml");
        Parent root = null;
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
        FXMLLoader fxmlLoader = springFXMLLoader.load("/book/selectedBookForm.fxml");
        Parent root = null;
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
}
