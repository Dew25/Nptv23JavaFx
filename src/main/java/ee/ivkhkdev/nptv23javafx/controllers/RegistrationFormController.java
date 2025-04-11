package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.loaders.LoginFormLoader;
import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.loaders.RegistrationFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class RegistrationFormController implements Initializable {

    private final AppUserService appUserService;
    private final RegistrationFormLoader registrationFormLoader;
    private final LoginFormLoader loginFormLoader;
    @FXML private TextField tfLastname;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFirstname;

    public RegistrationFormController(AppUserService appUserService, RegistrationFormLoader registrationFormLoader, LoginFormLoader loginFormLoader) {
        this.appUserService = appUserService;
        this.registrationFormLoader = registrationFormLoader;
        this.loginFormLoader = loginFormLoader;
    }

    @FXML private void registration(){
        try {
            AppUser newUser = new AppUser();
            newUser.setFirstname(tfFirstname.getText());
            newUser.setLastname(tfLastname.getText());
            newUser.setUsername(tfUsername.getText());
            newUser.setPassword(pfPassword.getText());
            newUser.getRoles().add("USER");
            appUserService.add(newUser);
            registrationFormLoader.load();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @FXML private void goToLoginForm(){
        loginFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pfPassword.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
                this.registration();
            }
        });
    }
}
