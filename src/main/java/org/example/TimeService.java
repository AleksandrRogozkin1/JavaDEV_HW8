package org.example;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static java.time.ZoneOffset.UTC;

@WebServlet(value = "/time")
public class TimeService extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String timezone = request.getParameter("timezone");
        timezone = (timezone == null) ? String.valueOf(UTC) : timezone.replaceAll(" ", "+");
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print( ZonedDateTime.now(ZoneId.of(timezone))
                        .format(DateTimeFormatter.ofPattern("dd-MM-yyyy hh:mm:ss 'UTC'X")));
        response.setHeader("Refresh", "5");
        out.close();
    }
}
