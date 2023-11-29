package munch;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Ratings
{
    public static void addRating(Connection con, int restID, int rating, int userID) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.executeUpdate("insert into Ratings(restID, rating, userID) values ("+restID+ "," + rating + "," + userID + ");");
    }
}
