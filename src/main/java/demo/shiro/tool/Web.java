package demo.shiro.tool;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Web {

    private static final Logger logger = LoggerFactory.getLogger(Web.class);

    private static final String jspPrefix = "/WEB-INF/jsp/";
    private static final String jspSuffix = ".jsp";

    public static void forward(String jsp) {
        HttpServletRequest request = Context.getRequest();
        HttpServletResponse response = Context.getResponse();
        try {
            request.getRequestDispatcher(jspPrefix + jsp + jspSuffix).forward(request, response);
        } catch (ServletException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }

    public static void redirect(String url) {
        HttpServletRequest request = Context.getRequest();
        HttpServletResponse response = Context.getResponse();
        try {
            response.sendRedirect(request.getContextPath() + url);
        } catch (IOException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }

    public static class Context {

        private static ThreadLocal<Context> container = new ThreadLocal<Context>();

        private HttpServletRequest request;
        private HttpServletResponse response;

        private Context(HttpServletRequest request, HttpServletResponse response) {
            this.request = request;
            this.response = response;
        }

        public static void init(HttpServletRequest request, HttpServletResponse response) {
            container.set(new Context(request, response));
        }

        public static void destroy() {
            container.remove();
        }

        public static HttpServletRequest getRequest() {
            return container.get().request;
        }

        public static HttpServletResponse getResponse() {
            return container.get().response;
        }
    }
}
