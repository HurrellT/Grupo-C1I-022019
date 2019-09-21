package app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.model.User.Provider.Provider;
import app.model.User.Provider.ProviderFactory;
import app.model.User.User;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class UserController {

    //Parameters

    private final UserService userService;

    //Constructor

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Methods

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody Provider user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
}
