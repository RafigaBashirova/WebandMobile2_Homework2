package controller_Servlets;

import org.mindrot.jbcrypt.BCrypt;
import utils.DButils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.Base64;

import static org.postgresql.shaded.com.ongres.scram.common.ScramStringFormatting.base64Encode;

public class RegistrationServlet extends HttpServlet {

    private static String EMAIL = "email";
    private static String PASSWORD = "password";
    private static String DIRECTEDPAGEAUTH = "/homework2/auth.jsp";
    private static String SQLQUERY = "INSERT INTO users_of_website VALUES(email, password)";
    private static String EnrcyptionMethod = "asdfgh12345678jhgf";

    private String passwordEncryption(String fetchedPassword){
        return BCrypt.hashpw(fetchedPassword, BCrypt.gensalt());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        HttpSession session = req.getSession();
        System.out.println("thanks...");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);
        String email = req.getParameter(RegistrationServlet.EMAIL);
        String password = req.getParameter(RegistrationServlet.PASSWORD);
        if(email != null && password != null && email.length() > 3 && password.length() > 3){
            try {
                String encryptedPassword = passwordEncryption(password);
                int resultSet = DButils.intqueries("INSERT INTO public.users_added(email, \"passwordEncrypted\") VALUES(?, '"+encryptedPassword+"')", new String[]{email});
                if (resultSet != 0) {
                    System.out.println("User registered! It's time to log into...");
                    resp.sendRedirect(RegistrationServlet.DIRECTEDPAGEAUTH);
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
