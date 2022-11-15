package pl.wojtyna.trainings.spring.crowdsorcery.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
@Order(1)
public class RequestBenchmarkingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        log.debug("Started  request benchmarking");
        var start = System.currentTimeMillis();
        filterChain.doFilter(servletRequest, servletResponse);
        var end = System.currentTimeMillis();
        log.debug("Request took {}ms", end - start);
    }
}
