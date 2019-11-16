package app.api.user;

import app.model.Exceptions.ResourceNotFoundException;
import app.model.Purchase.Purchase;
import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    //Parameters
    @Autowired
    private final UserRepository userRepository;
    private final ProviderRepository providerRepository;

    //Constructor
    public UserService(UserRepository userRepository, ProviderRepository providerRepository) {
        this.userRepository = userRepository;
        this.providerRepository = providerRepository;
    }

    //Methods
    public User addUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers(int page, int size) {
        Page<User> resultPage = userRepository.findAll(PageRequest.of(page,size));
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException();
        }

        return resultPage.getContent();
    }

    public List<Provider> getAllProviders(Integer page, Integer size) {
        Page<Provider> resultPage = providerRepository.findAll(PageRequest.of(page, size));
        if (page > resultPage.getTotalPages()) {
            throw new ResourceNotFoundException();
        }

        return resultPage.getContent();
//        return this.getAllUsers(page,size)
//                .stream()
//                .filter()
//                .collect(Collectors.toList());
    }

    public List<User> getAllClients(Integer page, Integer size) {
        return this.getAllUsers(page, size)
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
        foundUser.setPurchases(client.getPurchases());

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

    public void updateUserCredit(User user) {
        userRepository.save(user);
    }
}
