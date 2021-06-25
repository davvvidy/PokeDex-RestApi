package davvvidy.pokedexrestapi.pokemon;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokemons")
public class PokemonApi {
    private final PokemonService pokemonService;

    public PokemonApi(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @ApiOperation(value = "Find all pokemons saved in database.")
    @GetMapping("/all")
    public List<Pokemon> getAll() {
        return pokemonService.findAll();
    }

    @ApiOperation(value = "Find pokemon by id in database.")
    @GetMapping
    public Pokemon getById(@ApiParam(value = "Unique id of pokemon.") @RequestParam Long id) {
        return pokemonService.findById(id).get();
    }

    @ApiOperation(value = "Save pokemon to database from obtained json data.", notes = "Only logged in users can do it.")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pokemon addPokemon(@ApiParam(value = "Pokemon object.") @RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon);
    }

    @ApiOperation(value = "Update pokemon to database from obtained json data.", notes = "Only admin can do it.")
    @Secured({"ROLE_ADMIN"})
    @PutMapping
    public Pokemon updatePokemon(@ApiParam(value = "Pokemon object.") @RequestBody Pokemon pokemon) {
        return pokemonService.save(pokemon);
    }

    @ApiOperation(value = "Delete pokemon from database.", notes = "Only admin can do it.")
    @Secured({"ROLE_ADMIN"})
    @DeleteMapping
    public void deletePokemon(@ApiParam(value = "Unique id of pokemon.") @RequestParam Long id) {
        pokemonService.delete(id);
    }

    @ApiOperation(value = "Find all pokemons with name that contains given string.", notes = "Case insensitive.")
    @GetMapping("/name")
    public List<Pokemon> getByName(@ApiParam(value = "Pokemon name to search.") @RequestParam String name) {
        return pokemonService.findAllByNameContaining(name);
    }

    @ApiOperation(value = "Find all pokemons with type that contains given string.", notes = "Case insensitive, can be primary or secondary.")
    @GetMapping("/type")
    public List<Pokemon> getByType(@ApiParam(value = "Pokemon type to search.") @RequestParam String type) {
        return pokemonService.findAllByTypeContaining(type);
    }

    @ApiOperation(value = "Find all pokemons, but show only 5 at once.", notes = "Ascending by id.")
    @GetMapping("/paginated")
    public Page<Pokemon> getAllPaginated(@ApiParam(value = "Number of page.") @RequestParam int pageNo) {
        return pokemonService.findAll(PageRequest.of(pageNo, 5));
    }
}
