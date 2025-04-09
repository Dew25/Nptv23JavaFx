package ee.ivkhkdev.nptv23javafx.model.entity;

import jakarta.persistence.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class AppUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private String firstname;
    private String lastname;
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> roles = new HashSet<>();

    public AppUser() {}
    public AppUser(String username, String password, String firstname, String lastname) {
        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) && Objects.equals(username, appUser.username) && Objects.equals(password, appUser.password) && Objects.equals(firstname, appUser.firstname) && Objects.equals(lastname, appUser.lastname) && Objects.equals(roles, appUser.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, firstname, lastname);
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id=" + id +
                ", roles="+ Arrays.toString(roles.toArray()) +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstname + '\'' +
                ", lastName='" + lastname + '\'' +
                '}';
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
    @Transient
    public StringProperty idProperty() {
        return new SimpleStringProperty(id.toString());
    }
    @Transient
    public StringProperty firstnameProperty() {
        return new SimpleStringProperty(firstname);
    }
    @Transient
    public StringProperty lastnameProperty() {
        return new SimpleStringProperty(lastname);
    }
    @Transient
    public StringProperty usernameProperty() {
        return new SimpleStringProperty(username);
    }
    @Transient
    public StringProperty rolesProperty() {
        // Преобразуем коллекцию ролей в строку
        String roles = getRoles().stream()
                .map(role -> role.toString() )
                .collect(Collectors.joining(", "));
        return new SimpleStringProperty(roles);
    }
}
