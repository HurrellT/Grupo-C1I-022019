package viandasYaModel.Exceptions;

public class ScoreRateOutOfBoundsException extends Exception {
    public ScoreRateOutOfBoundsException() {
        super("El puntaje debe ser entre 1 y 5");
    }
}
