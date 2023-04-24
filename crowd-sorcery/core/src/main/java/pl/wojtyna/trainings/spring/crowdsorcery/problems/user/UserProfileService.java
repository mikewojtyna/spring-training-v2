package pl.wojtyna.trainings.spring.crowdsorcery.problems.user;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class UserProfileService {

    private final UserProfileRepository userProfileRepository;
    private final JdbcTemplate jdbcTemplate;

    public UserProfileService(UserProfileRepository userProfileRepository, JdbcTemplate jdbcTemplate) {
        this.userProfileRepository = userProfileRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public UserProfile createUserProfile(User user) {
        var userProfile = new UserProfile();
        userProfile.setId(UUID.randomUUID().toString());
        userProfile.setUser(user);
        return userProfileRepository.saveAndFlush(userProfile);
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateUserName(String userId) {
        jdbcTemplate.update("UPDATE uzer SET name=? WHERE id=?", "Henry", userId);
    }
}
