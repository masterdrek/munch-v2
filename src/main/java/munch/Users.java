package munch;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Users
{
    public static void addUser(Connection con, String name, String username, String password) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.executeUpdate("insert into Users(name, username, password) values('"+name+"', '" + username + "', '" + password + "');");
    }
}
