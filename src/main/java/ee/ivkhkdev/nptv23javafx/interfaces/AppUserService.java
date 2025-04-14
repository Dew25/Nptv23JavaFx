package ee.ivkhkdev.nptv23javafx.interfaces;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.security.Role;

public interface AppUserService extends AppService<AppUser> {
    void initSuperUser();
    void addRole(AppUser appdateUser,Role role);
    void removeRole(AppUser appdateUser,Role role);
}
