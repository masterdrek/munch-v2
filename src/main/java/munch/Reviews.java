package munch;
import java.sql.*;

public class Reviews {
    public static void attachReviewToRating(Connection con,
                                            int ratingID,
                                            int restID,
                                            String review) throws SQLException
    {
        Statement statement = con.createStatement();
        String insertString = "insert into Reviews(ratingID, restID, review) values(?,?,?);";
        PreparedStatement insertReview = con.prepareStatement(insertString);
        insertReview.setInt(1, ratingID);
        insertReview.setInt(2, restID);
        insertReview.setString(3, review);
        insertReview.executeUpdate();
    }
}
