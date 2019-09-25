package app.model.Exceptions;

public class NoItemsInTheOrderException extends  RuntimeException {

    public NoItemsInTheOrderException() {
        super("El pedido no tiene ningún item");
    }

}