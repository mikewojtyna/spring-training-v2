package pl.wojtyna.trainings.spring.crowdsorcery.validation;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public record FundraisingForm(@NotEmpty(groups = BasicFundraisingFormValidation.class) String description,
                              @Min(value = 100, groups = GoalFundraisingFormValidation.class) int goal,
                              @NotEmpty String borrower) {
}
