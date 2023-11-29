package munch;
import java.sql.*;

public class Restaurants
{
    public static void addRestaurant(Connection con, String name, String location) throws SQLException
    {
        Statement statement = con.createStatement();
        String insert = "insert into Restaurants(name, location) values ('" + name + "', '" + location + "');";
        statement.executeUpdate(insert);

    }
}
