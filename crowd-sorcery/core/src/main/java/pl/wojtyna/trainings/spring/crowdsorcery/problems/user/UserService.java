package pl.wojtyna.trainings.spring.crowdsorcery.problems.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UserService {

    private final UserRepository userRepository;
    private final UserProfileService userProfileService;
    private final JdbcTemplate jdbcTemplate;

    public UserService(UserRepository userRepository,
                       UserProfileService userProfileService,
                       JdbcTemplate jdbcTemplate) {
        this.userRepository = userRepository;
        this.userProfileService = userProfileService;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void createNewUser() {
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        var managedUser = userRepository.saveAndFlush(user);
        var userProfile = userProfileService.createUserProfile(managedUser);
        user.setUserProfile(userProfile);
    }

    @Transactional
    public String createNewUserWithoutOrm() {
        var user = new User();
        user.setId(UUID.randomUUID().toString());
        jdbcTemplate.update("INSERT INTO uzer(id, name) VALUES (?, ?)", user.getId(), "George");
        return user.getId();
    }

    @Transactional
    public void updateUserName(String userId) {
        jdbcTemplate.update("UPDATE uzer SET name=? WHERE id=?", "Martin", userId);
        userProfileService.updateUserName(userId);
    }
}
