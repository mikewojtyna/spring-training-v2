package pl.wojtyna.trainings.spring.crowdsorcery.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class IdentityRepositoryBackedUserDetailsService implements UserDetailsService {

    private final IdentityRepository identityRepository;

    public IdentityRepositoryBackedUserDetailsService(IdentityRepository identityRepository) {
        this.identityRepository = identityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return identityRepository.findById(username)
                                 .map(crowdSorceryIdentity -> new User(crowdSorceryIdentity.id(),
                                                                       crowdSorceryIdentity.password(),
                                                                       List.of(new SimpleGrantedAuthority("USER"))))
                                 .orElseThrow(() -> new UsernameNotFoundException("Username %s not found.".formatted(
                                     username)));
    }
}
