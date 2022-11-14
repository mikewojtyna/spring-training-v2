package pl.wojtyna.trainings.spring.crowdsorcery.external;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;

public class InvestorProfileRestApiServer {

    public static void main(String[] args) throws Exception {
        var server = new Server(8080);
        ServletContextHandler servletContextHandler = new ServletContextHandler(null, "/investor/api/v0");
        servletContextHandler.addServlet(new ServletHolder(new InvestorProfileServlet()), "/profiles");
        server.setHandler(servletContextHandler);
        try {
            server.start();
            server.join();
        }
        finally {
            server.destroy();
        }
    }

    private static class InvestorProfileServlet extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            resp.setContentType("application/json");
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println("""
                                     {
                                         "score":  25,
                                         "isVip":  true,
                                         "referralLink": "https://wojtyna.pl?ref=7890"
                                     }
                                     """);
        }
    }
}
