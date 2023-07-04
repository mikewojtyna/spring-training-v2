package pl.wojtyna.trainings.spring.crowdsorcery.validation;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FundraisingFormValidationController {

    @PostMapping("/basic/forms")
    public void createAndCheckBasicForm(@Validated(BasicFundraisingFormValidation.class) @RequestBody FundraisingForm form) {

    }

    @PostMapping("/goal/forms")
    public void createAndCheckGoalForm(@Validated(GoalFundraisingFormValidation.class) @RequestBody FundraisingForm form) {

    }
}
