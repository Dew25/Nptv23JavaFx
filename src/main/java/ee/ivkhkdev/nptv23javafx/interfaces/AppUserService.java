package ee.ivkhkdev.nptv23javafx.interfaces;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.entity.Session;
import ee.ivkhkdev.nptv23javafx.security.Role;
import javafx.collections.ObservableList;
import javafx.scene.control.SingleSelectionModel;

import java.util.Optional;

public interface AppUserService extends AppService<AppUser> {
    void initSuperUser();
    ObservableList<AppUser> getObservableList();

    void addRole(AppUser appdateUser,Role role);
    void removeRole(AppUser appdateUser,Role role);
}
