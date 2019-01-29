package com.company;

public class Player {
    private int id;
    private String login;
    private boolean isEliminated;
    private int score;
    private int finalScore;

    public Player(int id, String login) {
        this.id = id;
        this.login = login;
        this.isEliminated = false;
        this.score = 0;
        this.finalScore = 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isEliminated() {
        return isEliminated;
    }

    public void setEliminated(boolean eliminated) {
        isEliminated = eliminated;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(int finalScore) {
        this.finalScore = finalScore;
    }
}
