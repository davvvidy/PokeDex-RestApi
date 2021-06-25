package davvvidy.pokedexrestapi;

import davvvidy.pokedexrestapi.pokemon.Pokemon;
import davvvidy.pokedexrestapi.pokemon.PokemonService;
import davvvidy.pokedexrestapi.user.User;
import davvvidy.pokedexrestapi.user.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements InitializingBean {

    private PokemonService pokemonService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public DbInit(PokemonService pokemonService, UserService userService, PasswordEncoder passwordEncoder) {
        this.pokemonService = pokemonService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        pokemonService.save(new Pokemon(1L, "Bulbasaur", "Grass", "Poison", 318, 45, 49, 49, 65, 65, 45, 1, false, "There is a plant seed on its back right from the day this Pokémon is born. The seed slowly grows larger."));
        pokemonService.save(new Pokemon(2L, "Ivysaur", "Grass", "Poison", 405, 60, 62, 63, 80, 80, 60, 1, false, "When the bulb on its back grows large, it appears to lose the ability to stand on its hind legs."));
        pokemonService.save(new Pokemon(3L, "Venosaur", "Grass", "Poison", 525, 80, 82, 83, 100, 100, 80, 1, false, "Its plant blooms when it is absorbing solar energy. It stays on the move to seek sunlight."));
        pokemonService.save(new Pokemon(4L, "Charmander", "Fire", null, 309, 39, 52, 43, 60, 50, 65, 1, false, "It has a preference for hot things. When it rains, steam is said to spout from the tip of its tail."));
        pokemonService.save(new Pokemon(5L, "Charmeleon", "Fire", null, 405, 58, 64, 58, 80, 65, 80, 1, false, "It has a barbaric nature. In battle, it whips its fiery tail around and slashes away with sharp claws."));
        pokemonService.save(new Pokemon(6L, "Charizard", "Fighting", "Flying", 534, 78, 84, 78, 109, 85, 100, 1, false, "It spits fire that is hot enough to melt boulders. It may cause forest fires by blowing flames."));
        pokemonService.save(new Pokemon(7L, "Squirtle", "Water", null, 314, 44, 48, 65, 50, 64, 43, 1, false, "When it retracts its long neck into its shell, it squirts out water with vigorous force."));
        pokemonService.save(new Pokemon(8L, "Wartortle", "Water", null, 405, 59, 63, 80, 65, 80, 58, 1, false, "It is recognized as a symbol of longevity. If its shell has algae on it, that Wartortle is very old."));
        pokemonService.save(new Pokemon(9L, "Blastoise", "Water", null, 530, 79, 83, 100, 85, 105, 78, 1, false, "It crushes its foe under its heavy body to cause fainting. In a pinch, it will withdraw inside its shell."));
        pokemonService.save(new Pokemon(10L, "Caterpie", "Bug", "Normal", 195, 45, 30, 35, 20, 20, 45, 1, false, "For protection, it releases a horrible stench from the antenna on its head to drive away enemies."));

        userService.save(new User("admin1", passwordEncoder.encode("pass1"), "ADMIN"));
        userService.save(new User("user1", passwordEncoder.encode("pass1"), "USER"));
    }
}
