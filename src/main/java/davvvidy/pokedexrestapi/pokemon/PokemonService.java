package davvvidy.pokedexrestapi.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PokemonService {

    private PokemonRepository pokemonRepository;

    @Autowired
    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Optional<Pokemon> findById(Long id) {
        return pokemonRepository.findById(id);
    }

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public Pokemon save(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public void delete(Long id) {
        pokemonRepository.deleteById(id);
    }

    public List<Pokemon> findAllByNameContaining(String name) {
        return pokemonRepository.findAllByNameContaining(name);
    }

    public List<Pokemon> findAllByTypeContaining(String type) {
        List<Pokemon> pokemonsByType = pokemonRepository.findAllByPrimaryTypeContaining(type);
        pokemonsByType.addAll(pokemonRepository.findAllBySecondaryTypeContaining(type));
        return pokemonsByType;
    }

    public Page<Pokemon> findAll(Pageable pageable) {
        return pokemonRepository.findAll(pageable);
    }
}
