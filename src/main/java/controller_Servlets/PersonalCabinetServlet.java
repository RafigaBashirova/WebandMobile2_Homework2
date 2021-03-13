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

public class PersonalCabinetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null) {
            PrintWriter writer = resp.getWriter();
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<title>Success</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<h1>Please login firstly!!!</h1>");
            writer.println("<a href=\"webMobile2_Homework2_war/auth.jsp\">Auth page</a>");
            writer.println("</body>");
            writer.println("</html>");
        } else {
            resp.sendRedirect("personalCabinet.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        System.out.println("Personal cab");
        HttpSession session = req.getSession(false);
        if(session != null) {
            String currentemail = (String) session.getAttribute("current_email");
            String firstname = req.getParameter("firstname");
            String lastname = req.getParameter("lastname");
            String age = req.getParameter("age");
            String city = req.getParameter("city");
            String country = req.getParameter("country");
            String gender = req.getParameter("gender");

            if (firstname != null && firstname.length() > 0 && lastname != null && lastname.length() > 0
            && age != null && age.length() > 0 && city != null && city.length() > 0 &&
            country != null && country.length() > 0 && gender != null && gender.length() > 0) {
                try {
                    int resultSet = DButils.intqueries("UPDATE users_added SET firstname = ?, lastname = ?, age = ?, city = ?, country = ?, gender = ? WHERE email='"+currentemail+"';", new String[]{firstname, lastname, age, city, country, gender});
                    if (resultSet != 0) {
                        System.out.println("Your information is saved to database..");
                        PrintWriter writer = resp.getWriter();
                        writer.println("<html>");
                        writer.println("<head>");
                        writer.println("<title>Success</title>");
                        writer.println("</head>");
                        writer.println("<body>");
                        writer.println("<h1>Your information successfully saved to the database!!!</h1>");
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
            System.out.println("Please log in firstly!!!");
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
