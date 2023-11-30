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

    public static void getRestaurantsRatedByUser(Connection con, int userID) throws SQLException
    {
        ResultSet rs;
        String rName, location;
        String query = "select distinct r.name, r.location "
                        + "from Restaurants r "
                        + "join Ratings ra on r.restID = ra.restID "
                        + "where ra.userID = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(query))
        {
            preparedStatement.setInt(1, userID);
            rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                rName = rs.getString(1);
                location = rs.getString(2);
                System.out.println("Restaurant Name: " + rName);
                System.out.println("Location: " + location);
            }

        }

    }
}
