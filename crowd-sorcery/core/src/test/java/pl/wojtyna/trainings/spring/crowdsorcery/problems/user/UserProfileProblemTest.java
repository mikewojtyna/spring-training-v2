package pl.wojtyna.trainings.spring.crowdsorcery.problems.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserProfileProblemTest {

    @Autowired
    private UserService userService;

    @Test
    void testWithOrm() {
        userService.createNewUser();
    }

    @Test
    void testWithoutOrm() {
        var userId = userService.createNewUserWithoutOrm();
        userService.updateUserName(userId);
    }
}
