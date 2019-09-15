package viandasYaModel.User.Client;

public class ClientFactory {
    public static Client tomasHurrell() {
        return new Client("Tomas", "Hurrell",
                "Unq 123", "Bernal",
                "hurrelltomas@gmail.com", "+5491198765432");
    }
    public static Client federicoMartinez() {
        return new Client("Federico", "Martinez",
                "Saenz 123", "Avellaneda",
                "fedejmartinez@gmail.com", "+5491166213955");
    }
}
