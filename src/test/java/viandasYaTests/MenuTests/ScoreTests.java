package viandasYaTests.MenuTests;

import org.junit.Test;
import viandasYaModel.Exceptions.ScoreRateOutOfBoundsException;
import viandasYaModel.Menu.Score;
import static org.junit.Assert.*;


public class ScoreTests {

    @Test
    public void testScoreFrom1To5() throws ScoreRateOutOfBoundsException {
        Score score = new Score();
        assertEquals(0,score.score);
        score.rateWith(3);
        assertEquals(3,score.score);
    }

    @Test(expected = ScoreRateOutOfBoundsException.class)
    public void testCannotScoreBelow1AndAbove5Points() throws ScoreRateOutOfBoundsException {
        Score score = new Score();
        assertEquals(0,score.score);
        score.rateWith(6);
    }
}
