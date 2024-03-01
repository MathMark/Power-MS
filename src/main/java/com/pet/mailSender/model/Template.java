package com.pet.mailSender.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

@Entity
public class Template implements Serializable {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @NotEmpty(message = "{template.title.empty}")
    @Column(nullable = false, unique = true)
    @Getter @Setter private String title;

    @NotEmpty(message = "{template.subject.empty}")
    @Size(max = 80, message = "{template.subject.toolong}")
    @Column(nullable = false)
    @Getter
    @Setter
    private String subject;

    @NotEmpty(message = "{template.body.empty}")
    @Column(columnDefinition = "TEXT", nullable = false) //mysql type
    @Getter @Setter private String body;

    @Column(columnDefinition = "TEXT")//mysql type
    @Getter @Setter private String signature;

    @OneToMany(mappedBy = "template")
    private Set<Campaign> campaigns;

    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Template template = (Template) o;
        return title.equals(template.title) &&
                subject.equals(template.subject) &&
                body.equals(template.body);
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(id, title, subject, body);
    }

    @Override
    public String toString() {
        return "Template{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                ", body='" + body + '\'' +
                ", signature='" + signature + '\'' +
                '}';
    }
}
