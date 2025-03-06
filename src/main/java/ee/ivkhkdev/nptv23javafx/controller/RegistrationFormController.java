package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.service.AppUserServiceImpl;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class RegistrationFormController {

    private AppUserService appUserService;
    private FormLoader formLoader;

    @FXML private TextField tfLastname;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFirstname;

    public RegistrationFormController(AppUserService appUserService, FormLoader formLoader) {
        this.appUserService = appUserService;
        this.formLoader = formLoader;
    }

    @FXML private void registration(){
        try {
            AppUser newUser = new AppUser();
            newUser.setFirstname(tfFirstname.getText());
            newUser.setLastname(tfLastname.getText());
            newUser.setUsername(tfUsername.getText());
            newUser.setPassword(pfPassword.getText());
            newUser.getRoles().add(AppUserServiceImpl.ROLES.USER.toString());
            appUserService.add(newUser);
            formLoader.loadLoginForm();
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

}
