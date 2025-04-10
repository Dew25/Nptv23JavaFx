package ee.ivkhkdev.nptv23javafx.controllers;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.loaders.MainFormLoader;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.security.Role;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
public class AdminPanelFormController implements Initializable {

    private MainFormLoader mainFormLoader;
    private AppUserService appUserService;
    private AppUser updateUser;
    @FXML private TextField tfUser;
    @FXML private ComboBox<Role> cbRoles;
    @FXML private TableColumn<AppUser,String> tcId;
    @FXML private TableColumn<AppUser,String> tcFirstname;
    @FXML private TableColumn<AppUser,String> tcLastname;
    @FXML private TableColumn<AppUser,String> tcUsername;
    @FXML private TableColumn<AppUser,String> tcRoles;
    @FXML private TableView<AppUser> tvUsers;
    public AdminPanelFormController(MainFormLoader mainFormLoader, AppUserService appUserService) {
        this.mainFormLoader = mainFormLoader;
        this.appUserService = appUserService;
    }
    private void initFieldsForEdit(AppUser selectedUser) {
        this.updateUser = selectedUser;
        tfUser.setText(selectedUser.getUsername() + " " + selectedUser.getRoles()
                .stream().map(
                role -> role.toString())
                .collect(Collectors.joining(", ")));
        cbRoles.setCellFactory(lv -> new ListCell<>() {
            @Override
            protected void updateItem(Role role, boolean empty) {
                super.updateItem(role, empty);
                setText(empty || role == null ? null : role.toString());
            }
        });
        cbRoles.getItems().clear();
        cbRoles.getItems().addAll(Role.values());
    }
    @FXML private void btAddClick(){
        if(cbRoles.getSelectionModel().isEmpty()){return;}
        appUserService.addRole(updateUser, cbRoles.getSelectionModel().getSelectedItem());
        tvUsers.refresh();
        clearFieldsAndSelected();
    }
    @FXML private void btRemoveClick(){
        if(cbRoles.getSelectionModel().isEmpty()){return;}
        appUserService.removeRole(updateUser, cbRoles.getSelectionModel().getSelectedItem());
        tvUsers.refresh();
        clearFieldsAndSelected();
    }

    private void clearFieldsAndSelected() {
        tfUser.clear();
        cbRoles.getSelectionModel().clearSelection();
        tvUsers.getSelectionModel().clearSelection();
    }

    @FXML private void showMainForm(){
        mainFormLoader.load();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Инициируем список книг
        tvUsers.setItems(appUserService.getObservableList());
        // Заполняем столбцы
        tcId.setCellValueFactory(cellData -> cellData.getValue().idProperty());
        tcFirstname.setCellValueFactory(cellData -> cellData.getValue().firstnameProperty());
        tcLastname.setCellValueFactory(cellData -> cellData.getValue().lastnameProperty());
        tcUsername.setCellValueFactory(cellData -> cellData.getValue().usernameProperty());
        tcRoles.setCellValueFactory(cellData -> cellData.getValue().rolesProperty());

        tvUsers.setOnMouseClicked(event -> {
            if (event.getClickCount() == 1 && !tvUsers.getSelectionModel().isEmpty()) {
                AppUser selectedUser = tvUsers.getSelectionModel().getSelectedItem();
                initFieldsForEdit(selectedUser);
            }
        });

    }

}
