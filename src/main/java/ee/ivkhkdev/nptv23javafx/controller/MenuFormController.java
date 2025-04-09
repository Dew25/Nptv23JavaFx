package ee.ivkhkdev.nptv23javafx.controller;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.model.entity.Session;
import ee.ivkhkdev.nptv23javafx.model.repository.BookRepository;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import ee.ivkhkdev.nptv23javafx.tools.FormLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {

    private final AppUserService appUserService;
    private SessionManager sessionManager;

    private final FormLoader formLoader;
    private Session session;
    @FXML private Menu mBooks;
    @FXML private Menu mAdmin;
    @FXML private Menu mUsers;
    @FXML private MenuItem miEnter;
    @FXML private MenuItem miProfile;
    @FXML private MenuItem miLogout;


    public MenuFormController( SessionManager sessionManager, FormLoader formLoader, AppUserService appUserService) {

        this.sessionManager = sessionManager;
        this.formLoader = formLoader;
        this.appUserService = appUserService;

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
        this.sessionManager.logout();
        formLoader.loadLoginForm();
    }
    @FXML private void showTakedBookForm(){
        formLoader.loadTakedBookForm();
    }

    @FXML private void showListAuthorsForm(){
        formLoader.loadListAuthorForm();
    }
    @FXML private void showProfileForm(){
        formLoader.loadProfileForm();
    }
    @FXML private void showAdminPanelForm(){
        formLoader.loadAdminPanelForm();
    }
    private void initMenuVisible(){
        if(sessionManager.isLoggedIn()){
            if(sessionManager.getCurrentUser().getRoles().contains(Role.ADMINISTRATOR.toString())){
                mBooks.setVisible(true);
                mAdmin.setVisible(true);
                mUsers.setVisible(true);
                miEnter.setVisible(false);
                miProfile.setVisible(true);
                miLogout.setVisible(true);
            }else if(sessionManager.getCurrentUser().getRoles().contains(Role.MANAGER.toString())){
                mBooks.setVisible(true);
                mAdmin.setVisible(false);
                mUsers.setVisible(true);
                miEnter.setVisible(false);
                miProfile.setVisible(true);
                miLogout.setVisible(true);
            }else if(session.getCurrentUser().getRoles().contains(Role.USER.toString())){
                mBooks.setVisible(false);
                mAdmin.setVisible(false);
                mUsers.setVisible(true);
                miEnter.setVisible(false);
                miProfile.setVisible(true);
                miLogout.setVisible(true);
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }
}
