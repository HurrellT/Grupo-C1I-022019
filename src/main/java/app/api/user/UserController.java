package app.api.user;

import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@EnableAutoConfiguration
public class UserController {


    @Autowired
    private final UserService userService;

    //Constructor
    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Methods

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) {
        userService.addUser(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @RequestMapping(value = "/user/{name}", method = RequestMethod.GET)
    public User findUserNamed(@PathVariable("name") String name) {
        return userService.findUserNamed(name);
    }

    @PutMapping("/user/{id}")
    public void updateUserWithId(@PathVariable("id") String id, @RequestBody User user) {
        long userId = Long.parseLong(id);
        userService.updateUser(userId, user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUserWithId(@PathVariable("id") String id) {
        long userId = Long.parseLong(id);
        userService.deleteUserIdentifiedWith(userId);
    }
}
