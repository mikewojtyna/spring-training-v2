package pl.wojtyna.trainings.spring.crowdsorcery.problems.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class UserModuleConfiguration {

    @Bean
    public UserService userService(UserRepository userRepository, UserProfileService userProfileService,
                                   JdbcTemplate jdbcTemplate) {
        return new UserService(userRepository, userProfileService, jdbcTemplate);
    }

    @Bean
    public UserProfileService userProfileService(UserProfileRepository userProfileRepository,
                                                 JdbcTemplate jdbcTemplate) {
        return new UserProfileService(userProfileRepository, jdbcTemplate);
    }
}
