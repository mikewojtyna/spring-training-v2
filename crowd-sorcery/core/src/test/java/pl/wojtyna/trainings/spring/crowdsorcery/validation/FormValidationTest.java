package pl.wojtyna.trainings.spring.crowdsorcery.validation;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FormValidationTest {

    @Autowired
    private FormValidatingService validationService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void formValidatingService() {
        assertThatThrownBy(() -> validationService.method("")).isInstanceOf(ConstraintViolationException.class);
        assertThatThrownBy(() -> validationService.secondMethod(new FundraisingForm("description",
                                                                                    100,
                                                                                    ""))).isInstanceOf(
            ConstraintViolationException.class);
        assertThatNoException().isThrownBy(() -> validationService.secondMethod(new FundraisingForm("",
                                                                                                    1,
                                                                                                    "George")));
    }

    @Test
    void restApiGroupsValidation() throws Exception {
        mockMvc.perform(post("/basic/forms").contentType(MediaType.APPLICATION_JSON).content(
                   """
                   {
                       "description": "",
                       "goal": 1000
                   }
                   """)).andDo(print())
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.errors.description").isNotEmpty())
               .andExpect(jsonPath("$.errors.goal").doesNotExist());

        mockMvc.perform(post("/basic/forms").contentType(MediaType.APPLICATION_JSON).content(
                   """
                   {
                       "description": "project description",
                       "goal": 1000
                   }
                   """)).andDo(print())
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.errors.description").doesNotExist())
               .andExpect(jsonPath("$.errors.goal").doesNotExist());

        mockMvc.perform(post("/goal/forms").contentType(MediaType.APPLICATION_JSON).content(
                   """
                   {
                       "description": "project description",
                       "goal": 10
                   }
                   """)).andDo(print())
               .andExpect(status().isBadRequest())
               .andExpect(jsonPath("$.errors.description").doesNotExist())
               .andExpect(jsonPath("$.errors.goal").isNotEmpty());
    }
}
