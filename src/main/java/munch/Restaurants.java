package munch;
import java.sql.*;

public class Restaurants
{

    public static void clearRestaurantTable(Connection con) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.executeUpdate("drop table Restaurants");
    }
    public static void addRestaurant(Connection con, String name, String location) throws SQLException
    {
        Statement statement = con.createStatement();
        String insert = "insert into Restaurants(name, location) values ('" + name + "', '" + location + "');";
        statement.executeUpdate(insert);

    }

    public static void getAllRestaurants(Connection con) throws SQLException
    {
        ResultSet rs;
        String rName, location;
        Statement statement = con.createStatement();
        rs = statement.executeQuery("select * from Restaurants");

        while (rs.next())
        {
            rName = rs.getString(2);
            location = rs.getString(3);
            System.out.println("Restaurant Name:" + rName);
            System.out.println("Location: " + location);
        }
    }

    public static void getRestaurantsReviewedByUser(Connection con, int userID) throws SQLException
    {
        ResultSet rs;
        String rName, location;
        Statement statement = con.createStatement();
        rs = statement.executeQuery("select r.name, r.location "
                                    + "from Users u "
                                    + "join Reviews rev on u.userID = rev.userID "
                                    + "join Ratings rat on rev.ratingID = rat.ratingID "
                                    + "join Restaurants rest on rat.restID = rest.restID"
                                    + "where u.userID = " + userID);
        while (rs.next())
        {
            rName = rs.getString(1);
            location = rs.getString(2);
            System.out.println("Restaurant Name: " + rName);
            System.out.println("Location: " + location);
        }
    }
}
