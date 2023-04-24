package pl.wojtyna.trainings.spring.crowdsorcery.problems.user;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Table(name = "uzer_profile")
@Entity
@Data
@EqualsAndHashCode(of = "id")
public class UserProfile {

    @Id
    private String id;
    @OneToOne
    @JoinColumn(name = "uzer")
    private User user;
}
