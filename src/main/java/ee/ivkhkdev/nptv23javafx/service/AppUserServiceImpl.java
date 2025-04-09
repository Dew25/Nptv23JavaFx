package ee.ivkhkdev.nptv23javafx.service;

import ee.ivkhkdev.nptv23javafx.interfaces.AppUserService;
import ee.ivkhkdev.nptv23javafx.model.entity.AppUser;
import ee.ivkhkdev.nptv23javafx.model.entity.Book;
import ee.ivkhkdev.nptv23javafx.model.repository.AppUserRepository;
import ee.ivkhkdev.nptv23javafx.security.Role;
import ee.ivkhkdev.nptv23javafx.security.SessionManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
        if(sessionManager.isLoggedIn() && (sessionManager.hasRole(Role.MANAGER) || sessionManager.hasRole(Role.ADMINISTRATOR))){
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
    public ObservableList<AppUser> getObservableList() {
        ObservableList<AppUser> observableList = FXCollections.observableArrayList();
        observableList.addAll(this.getList());
        return observableList;
    }

    @Override
    public void addRole(AppUser appUser,Role role) {
        if(sessionManager.getCurrentUser().getRoles().contains(Role.ADMINISTRATOR.toString())){
            appUser.getRoles().add(role.toString());
            saveAndLogin(appUser);
        }
    }

    @Override
    public void removeRole(AppUser appUser,Role role) {
        if(sessionManager.getCurrentUser().getRoles().contains(Role.ADMINISTRATOR.toString())) {
            if (appUser.getUsername().equals("admin")) {
                return;
            }
            if (appUser.getRoles().contains(role.toString())
                    && role != Role.USER) {
                appUser.getRoles().remove(role.toString());
                saveAndLogin(appUser);
            }
        }
    }
    private void saveAndLogin(AppUser appUser){
        AppUser updateUser = appUserRepository.save(appUser);
        if(appUser.getUsername().equals(sessionManager.getCurrentUser().getUsername())){
            sessionManager.login(updateUser);
        }
    }

    @Override
    public Optional<AppUser> add(AppUser user) {
       return Optional.of(appUserRepository.save(user));
    }


}
