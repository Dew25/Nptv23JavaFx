package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.loaders.*;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;

@Component
public class MenuFormController implements Initializable {
    private final AuthorFormLoader authorFormLoader;
    private final NewBookFormLoader newBookFormLoader;
    private final LoginFormLoader loginFormLoader;
    private final TackeBookFormLoader tackeBookFormLoader;
    private final ListAuthorsFormLoader listAuthorsFormLoader;
    private final ProfileFormLoader profileFormLoader;
    private final AdminPanelFormLoader adminPanelFormLoader;
    private final BookReadingRatingFormLoader readingRatingFormLoader;

    private final SessionManager sessionManager;
    private Parent rootMenuForm;

    @FXML private Menu mBooks;
    @FXML private Menu mAdmin;
    @FXML private Menu mUsers;
    @FXML private MenuItem miEnter;
    @FXML private MenuItem miProfile;
    @FXML private MenuItem miLogout;


    public MenuFormController(AuthorFormLoader authorFormLoader, SessionManager sessionManager, NewBookFormLoader newBookFormLoader, LoginFormLoader loginFormLoader, TackeBookFormLoader tackeBookFormLoader, ListAuthorsFormLoader listAuthorsFormLoader, ProfileFormLoader profileFormLoader, AdminPanelFormLoader adminPanelFormLoader, BookReadingRatingFormLoader readingRatingFormLoader) {
        this.authorFormLoader = authorFormLoader;
        this.sessionManager = sessionManager;
        this.newBookFormLoader = newBookFormLoader;
        this.loginFormLoader = loginFormLoader;
        this.tackeBookFormLoader = tackeBookFormLoader;
        this.listAuthorsFormLoader = listAuthorsFormLoader;
        this.profileFormLoader = profileFormLoader;
        this.adminPanelFormLoader = adminPanelFormLoader;
        this.readingRatingFormLoader = readingRatingFormLoader;
    }

    @FXML private void showAuthorForm(){
        authorFormLoader.load();;
    }
    @FXML private void showBookForm() {
        newBookFormLoader.load();
    }
    @FXML private void showLoginForm(){
        loginFormLoader.load();
    }
    @FXML private void logout(){
        this.sessionManager.logout();
        loginFormLoader.load();
    }
    @FXML private void showTakedBookForm(){
        tackeBookFormLoader.load();
    }

    @FXML private void showListAuthorsForm(){
        listAuthorsFormLoader.load();
    }
    @FXML private void showProfileForm(){
        profileFormLoader.load();
    }
    @FXML private void showAdminPanelForm(){
        adminPanelFormLoader.load();
    }
    @FXML private void showBookReadingRatinForm(){
        readingRatingFormLoader.load();
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
            }else if(sessionManager.getCurrentUser().getRoles().contains(Role.USER.toString())){
                mBooks.setVisible(false);
                mAdmin.setVisible(false);
                mUsers.setVisible(true);
                miEnter.setVisible(false);
                miProfile.setVisible(true);
                miLogout.setVisible(true);
            }
        }

    }

    public void setRootMenuForm(Parent root) {
        this.rootMenuForm = root;
    }

    public Node getRootMenuForm() {
        return rootMenuForm;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initMenuVisible();
    }
}
