package model;

public class Ranking {
    private float score;
    private int userRank;
    private String username;

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getUserRank() {
        return userRank;
    }

    public void setUserRank(int userRank) {
        this.userRank = userRank;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Ranking(float score, int userRank, String username) {
        this.score = score;
        this.userRank = userRank;
        this.username = username;
    }

    public Ranking() {
    }
}
