package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.service.AppUserServiceImpl;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {

    private FormLoader formLoader;
    @FXML private Menu mBooks;
    @FXML private Menu mAdmin;
    @FXML private Menu mUsers;
    @FXML private MenuItem miEnter;
    @FXML private MenuItem miProfile;
    @FXML private MenuItem miLogout;


    public MenuFormController(FormLoader formLoader) {
        this.formLoader = formLoader;
    }

    @FXML private void showAuthorForm(){
        formLoader.loadAuthorForm();
    }
    @FXML private void showBookForm() {
        formLoader.loadNewBookForm();
    }
    @FXML private void showLoginForm(){
        formLoader.loadLoginForm();
    }
    @FXML private void logout(){
        AppUserServiceImpl.currentUser = null;
        formLoader.loadLoginForm();
    }
    private void initMenuVisible(){
        if(AppUserServiceImpl.currentUser.getRoles().contains(AppUserServiceImpl.ROLES.ADMINISTRATOR.toString())){
            mBooks.setVisible(true);
            mAdmin.setVisible(true);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
        }else if(AppUserServiceImpl.currentUser.getRoles().contains(AppUserServiceImpl.ROLES.MANAGER.toString())){
            mBooks.setVisible(true);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);
        }else if(AppUserServiceImpl.currentUser.getRoles().contains(AppUserServiceImpl.ROLES.USER.toString())){
            mBooks.setVisible(false);
            mAdmin.setVisible(false);
            mUsers.setVisible(true);
            miEnter.setVisible(false);
            miProfile.setVisible(true);
            miLogout.setVisible(true);

        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }
}
