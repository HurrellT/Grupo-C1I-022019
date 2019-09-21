package app.api;

import app.model.User.Provider.Provider;
import app.model.User.User;

import java.util.List;
import java.util.UUID;

public interface UserDao {

    int insertUser(UUID id, Provider user);

    default int insertUser(Provider provider) {
        UUID id = UUID.randomUUID();
        return insertUser(id, provider);
    }

    List<User> selectAllUsers();
}
