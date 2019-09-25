package app.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import app.model.User.Provider.Provider;
import app.model.User.User;

import java.util.List;

@Service
public class UserService {

    //Parameters

    private final UserDao userDao;

    //Constructor

    @Autowired
    public UserService(@Qualifier("fakeDao") UserDao userDao) {
        this.userDao = userDao;
    }

    //Methoids

    public int addUser(Provider user) {
        return userDao.insertUser(user);
    }

    public List<User> getAllUsers() {
        return userDao.selectAllUsers();
    }

}
