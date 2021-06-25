package davvvidy.pokedexrestapi.pokemon;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findAllByNameContaining(String name);

    List<Pokemon> findAllByPrimaryTypeContaining(String type);

    List<Pokemon> findAllBySecondaryTypeContaining(String type);

    List<Pokemon> findAll();

    Page<Pokemon> findAll(Pageable pageable);

}
