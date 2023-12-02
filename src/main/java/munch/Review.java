package munch;

public class Review {
    public int ratingID;
    public int restID;
    public String review;

    public Review(int ratingID, int restID, String review) {
        this.ratingID = ratingID;
        this.restID = restID;
        this.review = review;
    }

    public String getReview()
    {
        return review;
    }
}
