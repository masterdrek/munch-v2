package munch;
import java.sql.*;

public class Restaurants
{

    public static void addRestaurant(Connection con, String name, String location) throws SQLException {
        String insertQuery = "insert into Restaurants (name, location) values (?, ?)";

        try (PreparedStatement preparedStatement = con.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, location);

            preparedStatement.executeUpdate();
        }
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
    public static Restaurant getRestaurantInfo(String restaurantName) throws SQLException {
        Connection con = MunchApp.connect;
        String selectString = "select restID, name, location from Restaurants where name = ?";
        PreparedStatement selectRestaurant = con.prepareStatement(selectString);
        selectRestaurant.setString(1, restaurantName);
        ResultSet rs = selectRestaurant.executeQuery();
        if(!rs.isBeforeFirst()){
            return null;
        }
        rs.next();
        Restaurant restaurant = new Restaurant(rs.getInt("restID"), rs.getString("name"), rs.getString("location"));
        return restaurant;
    }



}
