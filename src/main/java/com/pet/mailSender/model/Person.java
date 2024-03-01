package com.pet.mailSender.model;

import com.pet.mailSender.model.annotations.CsvField;
import com.pet.mailSender.model.enums.EmailStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
public class Person implements Serializable {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    @CsvField(columnName = "First Name")
    private String firstName;

    @Getter
    @Setter
    @CsvField(columnName = "Last Name")
    private String lastName;

    @Getter
    @Setter
    @CsvField(columnName = "Email")
    private String email;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "campaign_id")
    @Getter
    @Setter
    private Campaign campaign;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private EmailStatus emailStatus;

    public Person(){
        this.emailStatus = EmailStatus.NEW;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", status='" + emailStatus + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return email.equals(person.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
