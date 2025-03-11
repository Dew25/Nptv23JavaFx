package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.Nptv23JavaFxApplication;
import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.repository.AppUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepository repository;
    public AppUserServiceImpl(AppUserRepository repository) {
        this.repository = repository;
        initSuperUser();
    }
    @Override
    public List<AppUser> getList() {
        return repository.findAll();
    }
    @Override
    public void initSuperUser(){
        if(repository.count()>0){
            return;
        }
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword("12345");
        admin.setFirstname("Admin");
        admin.setLastname("SuperAdmin");
        admin.getRoles().add(Nptv23JavaFxApplication.ROLES.ADMINISTRATOR.toString());
        admin.getRoles().add(Nptv23JavaFxApplication.ROLES.MANAGER.toString());
        admin.getRoles().add(Nptv23JavaFxApplication.ROLES.USER.toString());
        repository.save(admin);
    }
    @Override
    public Optional<AppUser> add(AppUser user) {
       return Optional.of(repository.save(user));
    }
    @Override
    public boolean authentication(String username, String password) {
        Optional<AppUser> optionalAppUser = repository.findByUsername(username);
        if(optionalAppUser.isEmpty()) {
            return false;
        }
        AppUser loginUser = optionalAppUser.get();
        if(!loginUser.getPassword().equals(password)) {
            return false;
        }
        Nptv23JavaFxApplication.currentUser = loginUser;
        return true;
    }
}
