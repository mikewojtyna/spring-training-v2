package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;
import pl.wojtyna.trainings.spring.crowdsorcery.security.PasswordChecker;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomBasicAuthFilter extends GenericFilterBean {

    private final PasswordChecker passwordChecker;

    public CustomBasicAuthFilter(PasswordChecker passwordChecker) {
        this.passwordChecker = passwordChecker;
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException {
        boolean accepted = true;
        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            var password = httpServletRequest.getHeader("Authorization");
            if (password == null || !passwordChecker.isCorrect(password)) {
                if (servletResponse instanceof HttpServletResponse httpServletResponse) {
                    accepted = false;
                    log.warn("Rejecting request. Basic authentication Authorization header is incorrect.");
                    httpServletResponse.setStatus(401);
                    httpServletResponse.setContentType("application/json");
                    httpServletResponse.getOutputStream().print("""
                                                                { "reason": "REJECTED_BAD_BASIC_AUTH" }
                                                                """);
                }
            }
        }
        if (accepted) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
