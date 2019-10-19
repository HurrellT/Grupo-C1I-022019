package app.api.user;

import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByName(String name);

    Client findClientById(Long id);

    Provider findProviderById(long id);

    //TODO: check to replace the save from spring to go through the class constructor
    //in order to check the validations, and if there's something wrong, send a bad request

}
