package app.api.user;

import app.model.Purchase.Purchase;
import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    //Parameters
    @Autowired
    private final UserRepository userRepository;

    //Constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //Methods
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public List<User> getAllProviders() {
        return this.getAllUsers()
                .stream()
                .filter(user -> (user.type).equals("provider"))
                .collect(Collectors.toList());
    }

    public List<User> getAllClients() {
        return this.getAllUsers()
                .stream()
                .filter(user -> (user.type).equals("client"))
                .collect(Collectors.toList());
    }

    public User findUserNamed(String name) {
        return userRepository.findByName(name);
    }

    public void updateClient(long userId, Client client) {
        Client foundUser = findClientById(userId);

        foundUser.name = client.name;
        foundUser.lastname = client.lastname;
        foundUser.state = client.state;
        foundUser.address = client.address;
        foundUser.email = client.email;
        foundUser.phone = client.phone;
        foundUser.accountCredit = client.accountCredit;

        userRepository.save(foundUser);
    }

    public User findUserById(long id) {
        return userRepository.findById(id).get();
    }

    public Client findClientById(long id) {
        return userRepository.findClientById(id);
    }

    public void deleteUserIdentifiedWith(long userId) {
        User oldUser = findUserById(userId);
        userRepository.delete(oldUser);
    }

    public void updateProvider(long userId, Provider provider) {
        Provider foundProvider = findProviderById(userId);

        foundProvider.name = provider.name;
        foundProvider.state = provider.state;
        foundProvider.address = provider.address;
        foundProvider.email = provider.email;
        foundProvider.phone = provider.phone;
        foundProvider.accountCredit = provider.accountCredit;
        foundProvider.logo = provider.logo;
        foundProvider.latitude = provider.latitude;
        foundProvider.longitude = provider.longitude;
        foundProvider.description = provider.description;
        foundProvider.website = provider.website;
        foundProvider.officeHoursFrom = provider.officeHoursFrom;
        foundProvider.officeHoursTo = provider.officeHoursTo;
        foundProvider.officeDaysFrom = provider.officeDaysFrom;
        foundProvider.officeDaysTo = provider.officeDaysTo;
        foundProvider.menus = provider.menus;


        userRepository.save(foundProvider);
    }

    public Provider findProviderById(long id) {
        return userRepository.findProviderById(id);
    }

    public List<Purchase> getAllPurchases() {
        return userRepository.findByType("client").getPurchases();
    }
}
