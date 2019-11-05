package app.api.menu;

import app.api.user.UserService;
import app.model.Menu.Menu;
import app.model.Menu.MenuCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public void addMenu(@Valid @RequestBody Menu menu) { menuService.addMenu(menu); }

    // GETTING -- GET REQUESTS

    @RequestMapping(value = "/menu/{name}", method = RequestMethod.GET)
    public Menu findMenuNamed(@PathVariable("name") String name) {
        return menuService.findMenuNamed(name);
    }

    @GetMapping("/menus")
    public List<Menu> getAllProviderMenus(@PathVariable("id") String id){
        long providerId = Long.parseLong(id);
        return userService.findProviderById(providerId).getMenus();
    }

    @GetMapping("/menus/{category}")
    public List<Menu> getAllCategoryMenus(@PathVariable("category")MenuCategory category){
        return menuService.getAllMenus()
               .stream()
                .filter(menu -> (menu.category) == category)
                .collect(Collectors.toList());
    }
}
