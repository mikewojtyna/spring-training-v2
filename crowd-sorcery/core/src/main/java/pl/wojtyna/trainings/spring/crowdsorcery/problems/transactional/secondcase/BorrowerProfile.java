package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.secondcase;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class BorrowerProfile {

    @Id
    private String id;
    private String name;
}
