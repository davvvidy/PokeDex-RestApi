package davvvidy.pokedexrestapi.user;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "Save user to database from obtained json data.")
    @PostMapping
    public User save(@ApiParam(value = "User object.") @RequestBody User user) {
        return userService.save(user);
    }

    @ApiOperation(value = "Find all users saved in database.", notes = "Only admin can do it.")
    @Secured({"ROLE_ADMIN"})
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }
}
