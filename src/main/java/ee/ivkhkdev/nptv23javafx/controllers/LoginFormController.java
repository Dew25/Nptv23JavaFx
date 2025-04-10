package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.loaders.RegistrationFormLoader;
import ee.ivkhkdev.nptv23javafx.service.AuthService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class LoginFormController implements Initializable {
    private AuthService authService;
    private AppUserService appUserService;
    private MainFormLoader mainFormLoader;
    private final RegistrationFormLoader registrationFormLoader;
    @FXML private Label lbInfo;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;

    public LoginFormController(AuthService authService, MainFormLoader mainFormLoader, AppUserService appUserService, RegistrationFormLoader registrationFormLoader) {
        this.authService = authService;
        this.mainFormLoader = mainFormLoader;
        this.appUserService = appUserService;

        this.registrationFormLoader = registrationFormLoader;
    }

    @FXML private void login(){
        if(authService.authenticate(tfUsername.getText(),pfPassword.getText())){
            mainFormLoader.load();
        }else{
            lbInfo.setText("Нет такого пользователя, или неправильный пароль");
        }
    }
    @FXML private void showRegistrationForm(){
        registrationFormLoader.load();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        pfPassword.setOnKeyPressed(event -> {
            if (event.getCode().toString().equals("ENTER")) {
               this.login();
            }
        });
    }
}
