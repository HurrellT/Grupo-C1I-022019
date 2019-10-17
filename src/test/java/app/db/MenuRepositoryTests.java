package app.db;


import app.api.menu.MenuRepository;
import app.model.Exceptions.MenuMinimumAmountInfringement;
import app.model.Exceptions.MenuPriceInfringement;
import app.model.Menu.Menu;
import app.model.Menu.MenuFactory;
import app.model.User.Client.ClientFactory;
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
public class MenuRepositoryTests {


    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void whenFindingMenuById_ThenCorrect() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        menuRepository.save(MenuFactory.empanadas());
        assertThat(menuRepository.findById(1L)).isInstanceOf(Optional.class);
    }

    @Test
    public void whenFindingAllMenus_thenCorrect() throws MenuMinimumAmountInfringement, MenuPriceInfringement {
        menuRepository.save(MenuFactory.burgerMenu());
        menuRepository.save(MenuFactory.pizzaMenu());
        assertThat(menuRepository.findAll()).isInstanceOf(List.class);
    }

    @Test
    public void whenSavingMenu_thenCorrect() throws MenuMinimumAmountInfringement, MenuPriceInfringement {

        Menu burgers = MenuFactory.burgerMenu();

        menuRepository.save(burgers);
        Menu menu = menuRepository.findById(1L).orElseGet(()-> burgers);
        assertThat(menu.getName()).isEqualTo("Burguer Menu");
    }
}
