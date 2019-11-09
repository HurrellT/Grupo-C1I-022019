package app.api.user;

import app.model.Purchase.Purchase;
import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@EnableAutoConfiguration
//@Validated
public class UserController {


    @Autowired
    private final UserService userService;

    //Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Methods

//  TODO: this one is temporary -- DELETE ME
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    // CREATING -- POST REQUESTS

    @PostMapping("/client")
    public void addClient(@Valid @RequestBody Client client) { userService.addUser(client); }

    @PostMapping("/provider")
    public void addClient(@Valid @RequestBody Provider client) { userService.addUser(client); }

    // GETTING -- GET REQUESTS

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public User findUserNamed(@PathVariable("name") String name) {
        return userService.findUserNamed(name);
    }

    @GetMapping("/providers")
    public List<User> getAllProviders() {
        return userService.getAllProviders();
    }

    @GetMapping("/clients")
    public List<User> getAllClients() {
        return userService.getAllClients();
    }

    @GetMapping("/clientPurchases/{client}")
    public List<Purchase> getClientPurchases(@PathVariable("client") String id){
        long clientId = Long.parseLong(id);
        return userService.findClientById(clientId).getPurchases();
    }

    @GetMapping("/providerGetId/{id}")
    public Provider getProviderById(@PathVariable("id") String id){
        long providerId = Long.parseLong(id);
        return userService.findProviderById(providerId);
    }

    @GetMapping("/providerName/{id}")
    public String getProviderName(@PathVariable("id") String id){
        long providerId = Long.parseLong(id);
        return userService.findProviderById(providerId).name;
    }

    // UPDATING -- PUT REQUESTS

    @PutMapping("/client/{id}")
    public void updateUserWithId(@PathVariable("id") String id, @RequestBody Client user) {
        long userId = Long.parseLong(id);
        userService.updateClient(userId, user);
    }

    @PutMapping("/provider/{id}")
    public void updateProviderWithId(@PathVariable("id") String id, @RequestBody Provider user) {
        long userId = Long.parseLong(id);
        userService.updateProvider(userId, user);
    }

    //DELETION -- DELETE REQUEST

    @DeleteMapping("/user/{id}")
    public void deleteUserWithId(@PathVariable("id") String id) {
        long userId = Long.parseLong(id);
        userService.deleteUserIdentifiedWith(userId);
    }
}
