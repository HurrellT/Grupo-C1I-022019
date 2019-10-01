package app.db;

import app.api.user.UserRepository;
import app.model.User.Client.ClientFactory;
import app.model.User.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void whenFindingUserById_ThenCorrect() {
        userRepository.save(ClientFactory.tomasHurrell());
        assertThat(userRepository.findById(1L)).isInstanceOf(Optional.class);
    }

    @Test
    public void whenFindingAllUsers_thenCorrect() {
        userRepository.save(ClientFactory.tomasHurrell());
        userRepository.save(ClientFactory.federicoMartinez());
        assertThat(userRepository.findAll()).isInstanceOf(List.class);
    }

    @Test
    public void whenSavingCustomer_thenCorrect() {
        userRepository.save(ClientFactory.tomasHurrell());
        User user = userRepository.findById(1L).orElseGet(()
                -> ClientFactory.tomasHurrell());
        assertThat(user.name).isEqualTo("Bob");
    }

}
