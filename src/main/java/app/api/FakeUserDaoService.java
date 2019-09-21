package app.api;

import org.springframework.stereotype.Repository;
import app.model.User.Provider.Provider;
import app.model.User.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository("fakeDao")
public class FakeUserDaoService implements UserDao {

    //Parameters

    private static List<User> fakeDb = new ArrayList<>();

    //Methods

    @Override
    public int insertUser(UUID id, Provider user) {
        fakeDb.add(new Provider(id, user.name,
                user.latitude, user.longitude));
        return 1;
    }

    @Override
    public List<User> selectAllUsers() {
        return fakeDb;
    }
}
