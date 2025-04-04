package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.repository.AppUserRepository;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AppUserServiceImpl implements AppUserService {

    private SessionManager sessionManager;
    private AppUserRepository appUserRepository;
    public AppUserServiceImpl(SessionManager sessionManager, AppUserRepository repository) {
        this.sessionManager = sessionManager;
        this.appUserRepository = repository;
        initSuperUser();
    }
    @Override
    public List<AppUser> getList() {
        if(sessionManager.isLoggedIn() && sessionManager.hasRole(Role.MANAGER)){
            return appUserRepository.findAll();
        }else{
            return new ArrayList<>();
        }

    }
    @Override
    public void initSuperUser(){
        if(appUserRepository.count()>0){
            return;
        }
        AppUser admin = new AppUser();
        admin.setUsername("admin");
        admin.setPassword("12345");
        admin.setFirstname("Admin");
        admin.setLastname("SuperAdmin");
        admin.getRoles().add(Role.ADMINISTRATOR.toString());
        admin.getRoles().add(Role.MANAGER.toString());
        admin.getRoles().add(Role.USER.toString());
        appUserRepository.save(admin);
    }
    @Override
    public Optional<AppUser> add(AppUser user) {
       return Optional.of(appUserRepository.save(user));
    }


}
