package controller_Servlets;

import org.mindrot.jbcrypt.BCrypt;
import utils.DButils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthenticationServlet extends HttpServlet {

    private static String CURRENT_USER = "current_email";
    private static String CURRENT_PASSWORD  = "current_password";
    private static String EMAILS = "emails";
    private static String PASSWORDS = "passwords";
    private static String DIRECTEDPAGEPERSONALCAB = "/homework2/personalCabinet.jsp";


    private boolean checkHash(String password,String hash) {
        return BCrypt.checkpw(password, hash);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        System.out.println("auth page");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String email = req.getParameter(AuthenticationServlet.EMAILS);
        String password = req.getParameter(AuthenticationServlet.PASSWORDS);
        if(email != null && password != null && email.length() > 3 && password.length() > 3){
            try {
                ResultSet resultSet = DButils.executeQeuryAsSelect("SELECT * from users_added WHERE email=? LIMIT 1", new String[]{email});
                if (resultSet != null && resultSet.next() && resultSet.getInt("userid") > 0) {
                    if (checkHash(password, resultSet.getString("passwordEncrypted"))) {
                        HttpSession session = req.getSession();
                        session.setAttribute("is_authorize", "true");
                        session.setAttribute(AuthenticationServlet.CURRENT_USER, email);
                        session.setAttribute(AuthenticationServlet.CURRENT_PASSWORD, password);
                        System.out.println("User logged in! It's time to fill personal information...");
                        resp.sendRedirect(AuthenticationServlet.DIRECTEDPAGEPERSONALCAB);
                    }
                }else{
                    System.out.println("Use valid email or password!!!");
                    req.setAttribute("error_message", new String[] {"no user"});
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("SQL exception");
                req.setAttribute("error_message", new String[] {"error while shcb"});
            }
        }else{
            System.out.println("Use valid email or password!!");
            req.setAttribute("error_message", new String[] {"validation errro"});
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
