package com.mydiaries.chefdiary.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mydiaries.chefdiary.user.validators.UniqueMailId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "user_details")
@SecondaryTable(name = "user_password", pkJoinColumns = @PrimaryKeyJoinColumn(name = "user_id"))
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private long id;

    @Column(name = "first_name")
    @NotNull(message = "First Name is mandatory.")
    private String firstName;

    @Column(name = "last_name")
    @NotNull(message = "Last Name is mandatory.")
    private String lastName;

    @Column(name = "user_name", unique = true)
    @NotNull(message = "User Name is mandatory.")
    private String userName;

    @Column(name = "mail_id", unique = true)
    @NotNull(message = "Mail Id is mandatory.")
    @UniqueMailId(message = "Given mail Id already exists.")
    @Email(message = "Enter a valid E-mail.")
    private String mailId;

    @Column(table = "user_password")
    @NotNull(message = "Password is mandatory.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMailId() {
        return mailId;
    }

    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
