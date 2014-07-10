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

    private Connection getConnection() {
        connection = null;
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

    public void executeSQLcmd(String sqlCmd) throws SQLException {
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        statement.executeQuery(sqlCmd);
    }

    public void insertInfo(String login, String password) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO \"USERS_INFO\" VALUES (?, ?)");
        preparedStatement.setNString(1, login);
        password = Utils.getSHA(password);
        preparedStatement.setNString(2, password);
        preparedStatement.executeUpdate();
    }

    public boolean checkPassword(String login, String password) throws SQLException, NoSuchAlgorithmException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT password FROM login_password WHERE login = ?");
        preparedStatement.setNString(1, login);
        password = Utils.getSHA(password);
        preparedStatement.setNString(2, password);
        ResultSet result = preparedStatement.executeQuery();
        result.getNString(1);
        return false;
    }

}
