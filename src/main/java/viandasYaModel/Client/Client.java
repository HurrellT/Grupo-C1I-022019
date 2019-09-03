package viandasYaModel.Client;

public class Client {

    //Parameters
    public String name;
    public String lastname;
    public String address;
    public String state;    //TODO: change to State ENUM ?
    public String email;
    public int phone;   //TODO: change to PhoneNumber ENUM to handle prefix per country?

    //Constructor
    public Client(String name, String lastname,
                  String address, String state,
                  String email, int phoneNumber) {
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.state = state;
        this.email = email;
        this.phone = phoneNumber;
    }

    public void changeNameTo(String newName) {
        this.name = newName;
    }

    //Methods

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getLastname() {
//        return lastname;
//    }
//
//    public void setLastname(String lastname) {
//        this.lastname = lastname;
//    }
}
