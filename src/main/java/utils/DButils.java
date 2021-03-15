package utils;

/**
 *                             Database Queries used:
 * create table users_added (
 * 	userId serial primary key,
 * 	email varchar(50) unique not null,
 * 	passwordEncrypted varchar(100) not null,
 * 	firstname varchar(30),
 * 	lastname varchar(30),
 * 	age varchar(15),
 * 	city varchar(30),
 * 	country varchar(30),
 * 	gender varchar(20),
 * 	courseName varchar(50)
 * )
 *
 * create table courses_of_website (
 * 	courseId serial primary key,
 * 	courseName varchar(50) not null
 * 	)
 *
 * 	insert into courses_of_website values(1, "WebandMobile2");
 * 	insert into courses_of_website values(2, "Calculus2");
 * 	insert into courses_of_website values(3, "Principles of Information Systems");
 *
 * **/

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DButils {

    static DataSource dataSource;

    static {
        System.out.println("Went into the block!!!");
        try {
            Context initContext = new InitialContext();
            Context webContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) webContext.lookup("jdbc/users_of_website");
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    //This is to execute queries as "select"
    public static ResultSet executeQeuryAsSelect(String sql, String... args) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DButils.dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i <= args.length - 1; i++) {
                statement.setString(i + 1, args[i]);
            }
            resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("You have problem there");
       } finally {
            if(connection != null) {
//                connection.close();
            }
        }
        return null;
    }

    //This is for executing queries as "insert, update, delete" which returns int
    public static int intqueries(String sql, String... args) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result;
        try {
            connection = DButils.dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            for (int i = 0; i <= args.length - 1; i++) {
                statement.setString(i + 1, args[i]);
            }
            result = statement.executeUpdate();

            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("You have problem there");
        }
        finally {
            if(connection != null) {
                connection.close();
            }
        }

        return 0;
    }


}
