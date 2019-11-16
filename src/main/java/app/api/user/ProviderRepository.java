package app.api.user;

import app.model.User.Client.Client;
import app.model.User.Provider.Provider;
import app.model.User.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepository
        extends CrudRepository<Provider, Long>,
        PagingAndSortingRepository<Provider, Long> {

    //TODO: check to replace the save from spring to go through the class constructor
    //in order to check the validations, and if there's something wrong, send a bad request

}
