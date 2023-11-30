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

    /* checkUser: given login info, checks if username/password correct in database, if valid, returns userID, otherwise returns failed string*/
    public static String checkUser(Connection con, String username, String password) throws SQLException
    {
        String selectString = "select userID, name, username, password from Users where username = ?";
        PreparedStatement selectUser = con.prepareStatement(selectString);
        selectUser.setString(1, username);
        ResultSet rs = selectUser.executeQuery();
        rs.next();
        String readUserID = rs.getString("userID");
        String readPassword = rs.getString("password");
        System.out.println("userid : " + readUserID);
        System.out.println("password : " + readPassword);
        if(readPassword.equals(password))
        {
            return readUserID;
        }
        else
        {
            return "failed login";
        }

    }
}
