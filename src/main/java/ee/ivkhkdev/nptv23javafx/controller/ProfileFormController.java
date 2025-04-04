package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
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

    private final AppUserService appUserService;
    private final FormLoader formLoader;
    private final SessionManager sessionManager;


    @FXML
    private TextField tfLastname;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFirstname;

    public ProfileFormController(AppUserService appUserService, FormLoader formLoader, SessionManager sessionManager) {
        this.appUserService = appUserService;
        this.formLoader = formLoader;
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
                formLoader.loadMainForm("Профиль пользователя обновлен");
            } else {
                formLoader.loadMainForm("Обновить профиль польздвателя не удалось");
            }
        }
    }
    @FXML
    private void showMianForm() {
        formLoader.loadMainForm();
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
