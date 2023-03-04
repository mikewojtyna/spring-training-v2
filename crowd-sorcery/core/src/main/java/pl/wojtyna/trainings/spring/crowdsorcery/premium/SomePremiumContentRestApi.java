package pl.wojtyna.trainings.spring.crowdsorcery.premium;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/premium")
public class SomePremiumContentRestApi {

    @GetMapping
    public SomeContent someContent(@AuthenticationPrincipal Jwt jwt) {
        return new SomeContent(jwt.getSubject(),
                               jwt.getClaim("given_name"),
                               jwt.getClaim("family_name"),
                               "some content created for " + jwt.getSubject());
    }
}
