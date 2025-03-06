package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.service.AppUserServiceImpl;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class LoginFormController {
    private FormLoader formLoader;
    private AppUserService appUserService;

    @FXML private Label lbInfo;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;

    public LoginFormController(FormLoader formLoader, AppUserService appUserService) {
        this.formLoader = formLoader;
        this.appUserService = appUserService;
    }

    @FXML private void login(){
        if(appUserService.authentication(tfUsername.getText(),pfPassword.getText())){
            formLoader.loadMainForm();
        }else{
            lbInfo.setText("Нет такого пользователя, или неправильный пароль");
        }
    }
    @FXML private void showRegistrationForm(){
        formLoader.loadRegistrationForm();
    }
}
