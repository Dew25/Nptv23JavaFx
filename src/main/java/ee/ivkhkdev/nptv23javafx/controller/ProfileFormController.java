package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class ProfileFormController implements Initializable {
    private final MainFormLoader mainFormLoader;
    private final AppUserService appUserService;
    private final SessionManager sessionManager;


    @FXML
    private TextField tfLastname;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFirstname;

    public ProfileFormController(AppUserService appUserService, MainFormLoader mainFormLoader, SessionManager sessionManager) {
        this.appUserService = appUserService;
        this.mainFormLoader = mainFormLoader;
        this.sessionManager = sessionManager;

    }
    @FXML
    private void updateProfile() {
        if(sessionManager.isLoggedIn()) {
            AppUser appUser = sessionManager.getCurrentUser();
            appUser.setFirstname(tfFirstname.getText());
            appUser.setLastname(tfLastname.getText());
            appUser.setUsername(tfUsername.getText());
            if (!pfPassword.getText().isEmpty()) {
                appUser.setPassword(pfPassword.getText());
            }
            Optional<AppUser> appUserOptional = appUserService.add(appUser);
            if (appUserOptional.isPresent()) {
                sessionManager.login(appUserOptional.get());
                mainFormLoader.load("Профиль пользователя обновлен");
            } else {
                mainFormLoader.load("Обновить профиль польздвателя не удалось");
            }
        }
    }
    @FXML
    private void showMianForm() {
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AppUser appUser = sessionManager.getCurrentUser();
        if (appUser != null && appUser.getUsername() != "admin") {
            tfFirstname.setText(appUser.getFirstname());
            tfLastname.setText(appUser.getLastname());
            tfUsername.setText(appUser.getUsername());
            pfPassword.setText("");
        }
    }

}
