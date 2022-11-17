package pl.wojtyna.trainings.spring.crowdsorcery.investor.repository.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class InvestorEntity {

    @Id
    private String id;
    private String name;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "investor_profile_id", referencedColumnName = "id")
    private InvestorProfileEntity investorProfile;
}
