package app.api.user;

import app.model.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public User findUserNamed(String name) {
        return userRepository.findByName(name);
    }

    public void updateUser(long userId, User user) {
        User foundUser = findById(userId);
        userRepository.delete(foundUser);
        foundUser.name = user.name;
        foundUser.state = user.state;
        foundUser.address = user.address;
        foundUser.email = user.email;
        foundUser.phone = user.phone;
        foundUser.accountCredit = user.accountCredit;

        userRepository.save(foundUser);
    }

    public User findById(long id) {
        return userRepository.findById(id).get();
    }
}
