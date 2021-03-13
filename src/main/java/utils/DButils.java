package utils;

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
    public static ResultSet query(String sql, String... args) throws SQLException {
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


    public static ResultSet queries(String sql) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = DButils.dataSource.getConnection();
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            return resultSet;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("You have problem there");
        } finally {
            if(connection != null) {
                // cant close connection because method is fetching data
            }
        }

        return null;
    }


    public static int intquery(String sql, String[] strings) throws SQLException {
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int result;
        try (Connection connection = DButils.dataSource.getConnection()) {
            statement = connection.prepareStatement(sql);
            result = statement.executeUpdate();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("You have problem there");
        }

        return 0;
    }


}
