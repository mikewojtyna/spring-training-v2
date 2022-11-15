package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
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
@Order(2)
public class DangerousFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        boolean accepted = true;
        if (servletRequest instanceof HttpServletRequest httpServletRequest) {
            if (Objects.equals(httpServletRequest.getParameter("redirectTo"), "http://dangerous.com")) {
                if (servletResponse instanceof HttpServletResponse httpServletResponse) {
                    log.warn("Rejecting dangerous request");
                    httpServletResponse.setStatus(400);
                    httpServletResponse.setContentType("application/json");
                    httpServletResponse.getOutputStream().print("""
                                                                { "reason": "REJECTED_DANGEROUS_REQUEST" }
                                                                """);
                    accepted = false;
                }
            }
        }
        if (accepted) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
