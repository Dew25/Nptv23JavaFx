package ee.ivkhkdev.nptv23javafx.security;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import org.springframework.stereotype.Component;

@Component
public class SessionManager {
    private AppUser currentUser;

    public void login(AppUser appUser) {
        this.currentUser = appUser;
    }

    public AppUser getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean isLoggedIn() {
        return currentUser != null;
    }

    public boolean hasRole(Role role) {
        if(currentUser.getRoles().contains(role.toString())) {
            return true;
        } else {
            return false;
        }
    }
}
