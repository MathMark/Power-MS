package com.pet.mailSender.model;

import com.pet.mailSender.model.enums.CampaignStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "email_statistics")
public class EmailStatistics {

    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Integer id;

    @Getter
    @Setter
    private int sentEmailsCount;

    @Getter
    @Setter
    private int rejectedEmailsCount;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private CampaignStatus campaignStatus;

    @OneToOne(mappedBy = "emailStatistics")
    @Getter
    @Setter
    private Campaign campaign;

    @Getter
    @Setter
    private int progress;

    @Override
    public String toString() {
        return "EmailStatistics{" +
                "id=" + id +
                ", sentEmailsCount=" + sentEmailsCount +
                ", rejectedEmailsCount=" + rejectedEmailsCount +
                ", campaignStatus=" + campaignStatus +
                '}';
    }
}
