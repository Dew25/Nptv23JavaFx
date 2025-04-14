package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.repository.AppUserRepository;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    private AppUserRepository appUserRepository;
    private SessionManager sessionManager;
    public AuthService(SessionManager sessionManager, AppUserRepository appUserRepository) {
        this.sessionManager = sessionManager;
        this.appUserRepository = appUserRepository;
    }
    public boolean authenticate(String username, String password) {
        Optional<AppUser> optionalAppUser = appUserRepository.findByUsername(username);
        if(optionalAppUser.isPresent()) {
            AppUser loginUser = optionalAppUser.get();
            if(loginUser.getPassword().equals(password)) {
                sessionManager.login(loginUser);
                return true;
            }else{
                return false;
            }
        }
        return false;

    }

}
