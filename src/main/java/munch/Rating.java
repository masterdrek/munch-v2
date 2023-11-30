package munch;

public class Rating {
    public int ratingID;
    public int restID;
    public int rating;
    public int userID;

    public Rating(int ratingID, int restID, int rating, int userID) {
        this.ratingID = ratingID;
        this.restID = restID;
        this.rating = rating;
        this.userID = userID;
    }
}
