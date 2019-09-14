package viandasYaModel.Menu;

import viandasYaModel.Exceptions.ScoreRateOutOfBoundsException;

public class Score {

    public int score;

    public Score() {
        this.score = 0;
    }

    public void rateWith(int scoreRate) throws ScoreRateOutOfBoundsException {
        if (scoreRate > 1 && scoreRate < 5)
            score+=scoreRate;
        else {
            throw new ScoreRateOutOfBoundsException();
        }
    }
}
