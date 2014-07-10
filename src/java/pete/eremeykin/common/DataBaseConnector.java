/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Pete
 */
package pete.eremeykin.common;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DataBaseConnector {

    Connection connection;

    public DataBaseConnector() {
        connection = getConnection();
    }

    @Override
    protected void finalize() throws Throwable {
        connection.close();
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Context context = new InitialContext();
            DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/TestDB");
            Locale.setDefault(Locale.ENGLISH);
            connection = dataSource.getConnection();
            return connection;
        } catch (NamingException | SQLException e) {
            return null;
        }
    }

    public static void executeSQLcmd(Connection connection, String sqlCmd) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.executeQuery(sqlCmd);
    }

    public static void insertInfo(Connection connection, String login, String password) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"USERS_INFO\" VALUES (?, ?)");
        preparedStatement.setNString(1, login);
        password = HashCoder.getSHA(password);
        preparedStatement.setNString(2, password);
        preparedStatement.executeUpdate();
    }

    public static boolean checkPassword(Connection connection, String login, String password) {
        return false;
    }

}
