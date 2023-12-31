package munch;

import java.sql.*;

public class DatabaseInitializer
{

    private static void executeUpdate(String query, Connection connect) throws SQLException
    {
        try (Statement statement = connect.createStatement())
        {
            statement.execute(query);
        }
    }

    public static void createRestaurantsTable(Connection connect) throws SQLException
    {
        String createTable = "create table if not exists Restaurants ("
                + "restID integer primary key auto_increment, "
                + "name varchar(50) not null unique, "
                + "location varchar(100) not null"
                + ")";
        executeUpdate(createTable, connect);
    }


    public static void createUsersTable(Connection connect) throws SQLException
    {
        String createTable = "create table if not exists Users ("
                + "userID integer primary key auto_increment, "
                + "name varchar(50) not null, "
                + "username varchar(50) not null unique, "
                + "password varchar(50) not null"
                + ")";
        executeUpdate(createTable, connect);
    }

    public static void createRatingsTable(Connection connect) throws SQLException
    {
        String createTable = "create table if not exists Ratings ("
                + "ratingID integer primary key auto_increment, "
                + "restID integer, "
                + "rating integer not null, "
                + "userID integer, "
                + "foreign key(userID) references Users(userID), "
                + "foreign key(restID) references Restaurants(restID),"
                + "UNIQUE KEY unique_combination (restID, userID)"
                + ")";
        executeUpdate(createTable, connect);
    }

    public static void createReviewsTable(Connection connect) throws SQLException
    {
        String createTable = "create table if not exists Reviews ("
                + "ratingID integer primary key, "
                + "restID integer, "
                + "review varchar(200), "
                + "foreign key(ratingID) references Ratings(ratingID), "
                + "foreign key(restID) references Restaurants(restID)"
                + ")";
        executeUpdate(createTable, connect);
    }

    public static void initializeDatabase(Connection connect) throws SQLException
    {
        createRestaurantsTable(connect);
        createUsersTable(connect);
        createRatingsTable(connect);
        createReviewsTable(connect);
    }
    public static void dropTables(Connection connect) throws SQLException
    {
        String dropTables = "drop table Reviews;";
        executeUpdate(dropTables, connect);
        dropTables = "drop table Ratings;";
        executeUpdate(dropTables, connect);
        dropTables = "drop table Users;";
        executeUpdate(dropTables, connect);
        dropTables = "drop table Restaurants;";
        executeUpdate(dropTables, connect);
    }
}
