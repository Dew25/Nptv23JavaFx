package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppUserService {
    public static AppUser currentUser;
    public enum ROLES {USER, MANAGER, ADMINISTRATOR};
    private AppUserRepository repository;
    public AppUserService(AppUserRepository repository) {
        this.repository = repository;
    }
    public void add(AppUser user) {
        repository.save(user);
    }
    public boolean authentication(String username, String password) {
        Optional<AppUser> optionalAppUser = repository.findByUsername(username);
        if(optionalAppUser.isEmpty()) {
            return false;
        }
        AppUser loginUser = optionalAppUser.get();
        if(!loginUser.getPassword().equals(password)) {
            return false;
        }
        currentUser = loginUser;
        return true;
    }
}
