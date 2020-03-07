package app.model.User.Client;

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
    public static Client juan() {
        return new Client("Juan", "Lopez",
                "Rp 1233", "Paris",
                "jlp1412334542@gmail.com", "+5491166242443");
    }

    public static Client josesito() {
        return new Client("Jose", "Sito",
                "calle falsa 123", "Estado X", "josesito@gmail.com",
                "+5491166242467");
    }
}
