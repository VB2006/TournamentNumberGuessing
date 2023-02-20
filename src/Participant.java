public class Participant {
    private int playerNum;
    public int score;
    public Participant() {
        playerNum = 0;
    }
    public Participant(int playerNum) {
        this.playerNum = playerNum;
    }
    public int getPlayerNum() {
        return playerNum;
    }
    public void setPlayerNum(int newPlayerNum) {
        playerNum = newPlayerNum;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int newScore) {
        score = newScore;
    }
}
