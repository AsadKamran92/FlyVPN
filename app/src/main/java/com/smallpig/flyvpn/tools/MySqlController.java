package com.smallpig.flyvpn.tools;

import com.smallpig.flyvpn.core.Properties;

import java.sql.*;

public class MySqlController {

    Connection con;

    public MySqlController() throws ClassNotFoundException,SQLException, IllegalAccessException, InstantiationException {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
    }

    public boolean RegisterUser(String user, String password) throws SQLException {
        con = DriverManager.getConnection(Properties.mysqlURL, "smallpig", "156318aq");
        Statement state = con.createStatement();

        String sqlselect = "select * from users where user='" + user + "'";
        ResultSet result = state.executeQuery(sqlselect);
        while (result.next()) {
            con.close();
            return false;
        }

        String sqlinsert = "insert into users values('" + user + "','" + password + "',0)";
        state.executeUpdate(sqlinsert);

        con.close();
        return true;
    }

    public boolean LoginUser(String user, String password) throws SQLException {
        con = DriverManager.getConnection(Properties.mysqlURL, "smallpig", "156318aq");
        Statement state = con.createStatement();
        String sql = "select * from users where user='" + user + "' and password='" + password + "'";
        ResultSet result = state.executeQuery(sql);

        while (result.next()) {
            con.close();
            return true;
        }

        con.close();
        return false;
    }
}