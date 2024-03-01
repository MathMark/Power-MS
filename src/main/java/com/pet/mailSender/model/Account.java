package com.pet.mailSender.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Account implements Serializable {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @NotEmpty(message = "{account.firstName.notEmpty}")
    @Pattern(regexp = "[A-Z][a-z]+", message = "{account.firstName.notValid}")
    private String firstName;

    @Getter
    @Setter
    @NotEmpty(message = "{account.lastName.notEmpty}")
    @Pattern(regexp = "[A-Z][a-z]+", message = "{account.lastName.notValid}")
    private String lastName;

    @Getter
    @Setter
    @Pattern(regexp = "[a-zA-Z0-9\\._-]+@[a-zA-Z0-9\\._-]+\\.[a-zA-Z0-9\\._-]+", message = "{account.email.noAt}")
    @Column(unique = true)
    private String email;

    @Getter
    @Setter
    @NotEmpty(message = "{account.password.notEmpty}")
    private String password;

    @OneToMany(mappedBy = "account")
    @Getter
    @Setter
    private Set<Campaign> campaigns;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return email.equals(account.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
