package pl.wojtyna.trainings.spring.crowdsorcery.problems.scoring;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "investor_data")
@Data
@Entity
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
public class InvestorData {

    @Id
    private String id;
    private int score;
    private boolean vip;
}
