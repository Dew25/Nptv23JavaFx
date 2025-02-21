package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.repository.AppUserRepository;
import ee.ivkhkdev.nptv23javafx.service.AppUserService;
import ee.ivkhkdev.nptv23javafx.service.FormService;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.stereotype.Component;

@Component
public class RegistrationFormController {

    private AppUserService appUserService;
    private FormService formService;

    @FXML private TextField tfLastname;
    @FXML private TextField tfUsername;
    @FXML private PasswordField pfPassword;
    @FXML private TextField tfFirstname;

    public RegistrationFormController(AppUserService appUserService, FormService formService) {
        this.appUserService = appUserService;
        this.formService = formService;
    }

    @FXML private void registration(){
        try {
            AppUser newUser = new AppUser();
            newUser.setFirstname(tfFirstname.getText());
            newUser.setLastname(tfLastname.getText());
            newUser.setUsername(tfUsername.getText());
            newUser.setPassword(pfPassword.getText());
            newUser.getRoles().add(AppUserService.ROLES.USER.toString());
            appUserService.add(newUser);
            formService.loadLoginForm();
        }catch (Exception e){
            throw new RuntimeException(e);
        }


    }

}
