package app.api.menu;

import app.api.user.UserService;
import app.model.Exceptions.MenuAmountConstraintException;
import app.model.Menu.Menu;
import app.model.User.Provider.Provider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@EnableAutoConfiguration
public class MenuController {

    @Autowired
    private final MenuService menuService;
    @Autowired
    private final UserService userService;

    //Constructor
    public MenuController(MenuService menuService, UserService userService) {
        this.menuService = menuService;
        this.userService = userService;
    }

    // CREATING -- POST REQUESTS

    @PostMapping("/menu")
    public void addMenu(@Valid @RequestBody Menu menu) throws MenuAmountConstraintException {
        long providerId = menu.getProviderId();
        Provider provider = userService.findProviderById(providerId);
        menuService.addMenu(menu);
        provider.addMenu(menu);
        userService.updateProvider(providerId, provider);
    }

    // GETTING -- GET REQUESTS

    @RequestMapping(value = "/menu/{name}", method = RequestMethod.GET)
    public Menu findMenuNamed(@PathVariable("name") String name) {
        return menuService.findMenuNamed(name);
    }

    @GetMapping("/menusp/{provider}")
    public List<Menu> getAllProviderMenus(@PathVariable("provider") String id){
        long providerId = Long.parseLong(id);
        return userService.findProviderById(providerId).getMenus();
    }

    @GetMapping("/menusc/{category}")
    public List<Menu> getAllCategoryMenus(@PathVariable("category") String category){
        return menuService.getAllMenus()
                .stream()
                .filter(menu -> (menu.category.toString().toLowerCase()).equals(category))
                .collect(Collectors.toList());
    }

    @GetMapping("/menus")
    public List<Menu> getAllMenus() {

        List<Provider> providers = userService.getAllProviders(0,10 /*TODO: PASARLE PAGINACION POR URL*/);
        List<Menu> menus = new ArrayList<>();
        for (Provider p : providers){
            menus.addAll(p.getMenus());
        }
        return menus;
    }

}
