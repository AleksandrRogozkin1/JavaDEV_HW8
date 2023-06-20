package org.example;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.ZoneId;



@WebFilter("/time")
public class TimeZoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws IOException, ServletException {
        String timezone = req.getParameter("timezone");
        if(timezone == null || validTimeZone(timezone.replaceAll(" ", "+"))){
            chain.doFilter(req,resp);
        }else {
            resp.setContentType("text/html");
            resp.getWriter().write("ERROR 400 Invalid timezone");
            resp.setStatus(400);
            resp.getWriter().close();
        }
    }
    private boolean validTimeZone(String timezone) {
        try {
            ZoneId.of(timezone);
            return true;
        }catch (DateTimeException e){
            return false;
        }
    }
}
