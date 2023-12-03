package munch;

import javafx.util.Pair;

import java.sql.*;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Ratings
{

    public static int addRating(Connection con, int restID, int rating, int userID) throws SQLException
    {
        Statement statement = con.createStatement();
        statement.executeUpdate("insert into Ratings(restID, rating, userID) values ("+restID+ "," + rating + "," + userID + ");");
        String selectRatingIdString = "select ratingID from Ratings where restID = ? and userID = ?";
        PreparedStatement selectRatingId = con.prepareStatement(selectRatingIdString);
        selectRatingId.setInt(1, restID);
        selectRatingId.setInt(2,userID);
        ResultSet rs = selectRatingId.executeQuery();
        if(rs.isBeforeFirst()){
            rs.next();
            return rs.getInt("ratingID");
        }
        return -1;
    }

    public static List<Pair<Rating, Review>> getRatingsAndReviewsForRestaurant(Connection con, int restID) throws  SQLException
    {
        String selectRatingsString = "select ratingID, restID, rating, userID from Ratings where restID = ?";
        PreparedStatement selectRatings = con.prepareStatement(selectRatingsString);
        selectRatings.setInt(1, restID);
        ResultSet ratingsResults = selectRatings.executeQuery();

        List<Pair<Rating, Review>> ratingsAndReviews = new ArrayList<>();
        while(ratingsResults.next())
        {
            String selectReviewString = "select ratingID, restID, review from Reviews where ratingID = ?";
            PreparedStatement selectReview = con.prepareStatement(selectReviewString);
            selectReview.setInt(1, ratingsResults.getInt("ratingID"));
            ResultSet reviewResult = selectReview.executeQuery();
            Rating rating = new Rating(ratingsResults.getInt("ratingID"),
                                       ratingsResults.getInt("restID"),
                                       ratingsResults.getInt("rating"),
                                       ratingsResults.getInt("userID"));
            Review review = null;

            if(reviewResult.isBeforeFirst())
            {

                if (reviewResult.next()) {
                    System.out.println(reviewResult.getString("review"));
                    review = new Review(
                            reviewResult.getInt("ratingID"),
                            reviewResult.getInt("restID"),
                            reviewResult.getString("review")
                    );
                } else {
                    System.out.println("No reviews found.");
                }
            }
            Pair<Rating, Review> entry = new Pair<>(rating,review);
            ratingsAndReviews.add(entry);
        }
        return ratingsAndReviews;
    }
}
