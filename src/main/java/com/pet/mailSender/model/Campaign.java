package com.pet.mailSender.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Campaign implements Serializable {
    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    private Long delay;

    @Getter
    @Setter
    @OneToMany(mappedBy = "campaign", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Person> people = new HashSet<>();

    public void addPeople(Set<Person> people){
        people.forEach(p -> p.setCampaign(this));
        this.people = people;
    }

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "template_id", nullable = false)
    @Getter
    @Setter
    private Template template;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "email_statistics_id")
    @Getter
    @Setter
    private EmailStatistics emailStatistics;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "account_id")
    @Getter
    @Setter
    private Account account;

    @Override
    public String toString() {
        return "Campaign{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", delay=" + delay +
                ", people=" + people +
                ", template=" + template +
                ", emailStatistics=" + emailStatistics +
                ", account=" + account +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return id.equals(campaign.id) &&
                title.equals(campaign.title) &&
                delay.equals(campaign.delay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, delay);
    }
}
