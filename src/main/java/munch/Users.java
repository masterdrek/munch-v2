package munch;
import java.sql.*;

public class Users
{
    public static void addUser(Connection con, String name, String username, String password) throws SQLException
    {
        String insertString = "insert into Users(name, username, password) values(?,?,?)";
        PreparedStatement insertUser = con.prepareStatement(insertString);
        insertUser.setString(1, name);
        insertUser.setString(2, username);
        insertUser.setString(3,password);
        insertUser.executeUpdate();
    }
    public static void checkUser(Connection con, String username, String password) throws SQLException
    {
        String selectString = "select userID, name, username, password from Users where username = ?";
        PreparedStatement selectUser = con.prepareStatement(selectString);
        selectUser.setString(1, username);
        ResultSet rs = selectUser.executeQuery();

        while (rs.next()) {
            String readUsername = rs.getString("username");
            String readPassword = rs.getString("password");
            System.out.println("userid : " + readUsername);
            System.out.println("username : " + readPassword);
        }

    }
}
