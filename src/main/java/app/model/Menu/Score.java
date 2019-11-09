package app.model.Menu;

import app.model.Exceptions.ScoreRateOutOfBoundsException;

import javax.persistence.*;

@Entity
@Table(name = "score")
public class Score {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
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
