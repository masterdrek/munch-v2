package munch;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Reviews {
    public static void attachReviewToRating(Connection con,
                                            int ratingID,
                                            int restID,
                                            String review) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.executeUpdate("insert into Reviews(ratingID, restID, review) values("+ratingID+", " + restID + ", '" + review + "');");
    }
}
