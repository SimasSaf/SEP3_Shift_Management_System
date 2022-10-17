package group3.model;

import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Validated
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    @Size(min = 3, max = 12)
    private String username;

    @Column(name = "password", nullable = false)
    @Size(min = 3, max = 17)
    private String password;

    @Column(name = "first_name", nullable = false)
    @Size(max = 25)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    @Size(max = 25)
    private String lastName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "phone_number", nullable = false)
    @Size(min = 7, max = 10)
    private String phoneNumber;

    @Column(name = "auth_level", nullable = false)
    private String authLevel;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(long id, String username, String password, String firstName, String lastName, String authLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.authLevel = authLevel;
    }

    public User(long id, String username, String password, String firstName, String lastName, String email, String phoneNumber, String authLevel) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.authLevel = authLevel;
    }

    public User(long id) {
        this.id = id;
    }

    public User(String firstName, String lastName, String phoneNumber, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    public long getId() {return id;}

    public void setId(long id) {this.id = id;}

    public String getUsername() {return username;}

    public void setUsername(String username) {this.username = username;}

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getFirstName() {return firstName;}

    public void setFirstName(String firstName) {this.firstName = firstName;}

    public String getLastName() {return lastName;}

    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getEmail() {return email;}

    public void setEmail(String email) {this.email = email;}

    public String getPhoneNumber() {return phoneNumber;}

    public void setPhoneNumber(String phoneNumber) {this.phoneNumber = phoneNumber;}

    public String getAuthLevel() {return authLevel;}

    public void setAuthLevel(String authLevel) {this.authLevel = authLevel;}
}
