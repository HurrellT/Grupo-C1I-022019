package viandasYaModel.Exceptions;

public class MenuPriceInfringement extends Throwable {
    //TODO: Mejorar los mensajes de error (pasar parametros pasados?)
    public MenuPriceInfringement() { super("Los precios son incorrectos");}
}
