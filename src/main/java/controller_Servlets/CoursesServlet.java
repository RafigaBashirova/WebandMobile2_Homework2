package controller_Servlets;

import utils.DButils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class CoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        System.out.println("Course Enrollment!");
        HttpSession session = req.getSession(false);
        if(session != null) {
            String currentEmail = (String) session.getAttribute("current_email");
            String courseName = req.getParameter("courseName");
            if(courseName != null && courseName.length() > 0) {
                try {
                    int resultSet = DButils.intqueries("UPDATE users_added SET coursename = ? WHERE email = ?", new String[]{courseName, currentEmail});
                    if (resultSet != 0) {
                        System.out.println("Your enrollment information is saved to database..");
                        PrintWriter writer = resp.getWriter();
                        writer.println("<html>");
                        writer.println("<head>");
                        writer.println("<title>Success</title>");
                        writer.println("</head>");
                        writer.println("<body>");
                        writer.println("<h1>You have successfully enrolled to the course!!!</h1>");
                        writer.println("</body>");
                        writer.println("</html>");
                    } else {
                        System.out.println("Use valid information!!!");
                        req.setAttribute("error_message", new String[]{"no information is set"});
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("SQL exception");
                    req.setAttribute("error_message", new String[]{"error while SQL connection"});
                }
            } else {
                System.out.println("Use valid information!!");
                req.setAttribute("error_message", new String[]{"validation error"});
            }
        }else{
            System.out.println("Please login firstly!!!");
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();
    }
}
