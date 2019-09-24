package viandasYaModel.Exceptions;

public class MenuMinimumAmountInfringement extends Throwable {

    //TODO: Mejorar los mensajes de error (pasar parametros pasados?)
    public MenuMinimumAmountInfringement() { super("Las cantidades minimas se pisan");}
}
