package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Slf4j
public class InvestorSecretFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws ServletException, IOException {
        boolean accepted = true;
        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            if (!Objects.equals(httpServletRequest.getHeader("InvestorSecret"), "aspecialsecret")) {
                if (servletResponse instanceof HttpServletResponse httpServletResponse) {
                    accepted = false;
                    log.warn("Rejecting investor request without correct InvestorSecret");
                    httpServletResponse.setStatus(403);
                    httpServletResponse.setContentType("application/json");
                    httpServletResponse.getOutputStream().print("""
                                                                { "reason": "REJECTED_BAD_SECRET" }
                                                                """);
                }
            }
        }
        if (accepted) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
