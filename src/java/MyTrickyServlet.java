
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author matheszabi
 */
public class MyTrickyServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int number = 1;
        try {
            number = Integer.parseInt(req.getParameter("number"));
        } catch (Throwable t) {
        }

        try {
            System.out.println("Servlet no. " + number + " called.");

            URL url = new URL(req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getRequestURI() + "?number=" + (number + 1));
            Object content = url.getContent();
            resp.setContentType("plain/text");
            resp.getWriter().write("OK: " + content);

        } catch (Throwable e) {
            String message = "Reached " + number + " of connections";
            System.out.println(message);
            System.out.println(e);
            resp.getWriter().write(message);
        }
    }
}
