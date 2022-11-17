package pl.wojtyna.trainings.spring.crowdsorcery.borrower.jpa;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@EqualsAndHashCode(of = "id")
public class BorrowerEntity {

    @Id
    private String id;
    private String name;
}
