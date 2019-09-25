package app.model.Exceptions;

public class NoEnoughCreditException extends  RuntimeException {

    public NoEnoughCreditException() {
        super("No posee suficiente credito para realizar la compra");
    }

}
