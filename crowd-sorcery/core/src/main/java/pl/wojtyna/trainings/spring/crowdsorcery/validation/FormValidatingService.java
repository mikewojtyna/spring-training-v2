package pl.wojtyna.trainings.spring.crowdsorcery.validation;


import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Validated
public class FormValidatingService {

    public void method(@NotEmpty String param) {

    }

    public void secondMethod(@Valid FundraisingForm record) {
    }
}
