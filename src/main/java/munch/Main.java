package munch;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main
{
    static Connection connect;

    public static void main(String[] args)
    {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://ambari-node5.csc.calpoly.edu:3306/cs365munch?user=cs365munch&password=cs365munchpass");
            System.out.println("connected to database");

            DatabaseInitializer.initializeDatabase(connect);
            Users.addUser(connect, "test name", "test username", "test password");
            Restaurants.addRestaurant(connect, "McDonalds", "SLO");
            System.out.println("all tables created successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
