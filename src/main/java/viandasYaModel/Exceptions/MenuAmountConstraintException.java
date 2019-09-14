package viandasYaModel.Exceptions;

public class MenuAmountConstraintException extends Throwable {
    public MenuAmountConstraintException() {
        super("Supera la cantidad máxima de 20 menus. Elimine o modifique algun menu para continuar.");
    }
}
