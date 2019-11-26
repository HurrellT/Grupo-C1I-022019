package app.api.user;

import app.model.Exceptions.NoEnoughCreditException;
import app.model.Purchase.Purchase;
import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

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

        // Credit management

    @PostMapping("/withdrawCredit/{id}/{amount}")
    public void withdrawCredit(@PathVariable("id") String id, @PathVariable("amount") String amount) throws NoEnoughCreditException {
        long userId = Long.parseLong(id);
        User user = userService.findUserById(userId);
        try {
            user.subtractCredit(Double.parseDouble(amount));
        }
        catch (NoEnoughCreditException exc) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, exc.getMessage(), exc);
        }
        userService.updateUserCredit(user);
    }

    @PostMapping("/depositCredit/{id}/{amount}")
    public void depositCredit(@PathVariable("id") String id, @PathVariable("amount") String amount) {
        long userId = Long.parseLong(id);
        User user = userService.findUserById(userId);
        user.addCredit(Double.parseDouble(amount));
        userService.updateUserCredit(user);
    }

    // GETTING -- GET REQUESTS

    @GetMapping("/totalUsers")
    public Integer getAllUsersSize() {
        return userService.getAllUsersSize();
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, params = { "page", "size" })
    public List<User> getAllUsers(@RequestParam("page") Integer page,
                                  @RequestParam("size") Integer size) {
        return userService.getAllUsers(page, size);
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public User findUserNamed(@PathVariable("name") String name) {
        return userService.findUserNamed(name);
    }

    @GetMapping("/client/{email}")
    public Client findClientByEmail(@PathVariable("email") String email) {
        return userService.findClientByEmail(email);
    }

    @GetMapping("/user/{email}")
    public User findUserByEmail(@PathVariable("email") String email) {
        return userService.findUserByEmail(email);
    }

    @GetMapping("/clientIsRegistered/{email}")
    public boolean clientIsRegistered(@PathVariable("email") String email) {
        return userService.clientIsRegistered(email);
    }

    @GetMapping(value = "/providers", params = { "page", "size" })
    public List<Provider> getAllProviders(@RequestParam("page") Integer page,
                                          @RequestParam("size") Integer size) {
        return userService.getAllProviders(page, size);
    }

    @GetMapping(value = "/clients", params = { "page", "size" })
    public List<User> getAllClients(@RequestParam("page") Integer page,
                                    @RequestParam("size") Integer size) {
        return userService.getAllClients(page, size);
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
