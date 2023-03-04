package pl.wojtyna.trainings.spring.crowdsorcery.problems.transactional.firstcase;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Id;

@Data
@EqualsAndHashCode(of = "id")
public class BorrowerProfile {

    @Id
    private String id;
    private String name;
}
