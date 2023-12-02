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
    public static User getUserInfo() throws SQLException {
        Connection con = MunchApp.connect;
        int userID = MunchApp.currentUserID;
        String selectString = "select userID, name, username, password from Users where userID = ?";
        PreparedStatement selectUser = con.prepareStatement(selectString);
        selectUser.setInt(1, userID);
        ResultSet rs = selectUser.executeQuery();
        if(!rs.isBeforeFirst()){
            return null;
        }
        rs.next();
        User user = new User(userID, rs.getString("name"), rs.getString("username"), rs.getString("password"));
        return user;
    }
    /* checkUser: given login info, checks if username/password correct in database, if valid, returns userID, otherwise returns failed string*/
    public static String checkUser(Connection con, String username, String password) throws SQLException
    {
        String selectString = "select userID, name, username, password from Users where username = ?";
        PreparedStatement selectUser = con.prepareStatement(selectString);
        selectUser.setString(1, username);
        ResultSet rs = selectUser.executeQuery();
        if(!rs.isBeforeFirst()){
            return "username not found";
        }
        rs.next();
        int readUserID = rs.getInt("userID");
        String readPassword = rs.getString("password");
        System.out.println("userid : " + readUserID);
        System.out.println("password : " + readPassword);
        if(readPassword.equals(password))
        {
            return Integer.toString(readUserID);
        }
        else
        {
            return "password incorrect";
        }

    }
}
