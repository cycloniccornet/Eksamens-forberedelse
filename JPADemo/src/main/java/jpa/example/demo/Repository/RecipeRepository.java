package jpa.example.demo.Repository;

import jpa.example.demo.Model.Category;
import jpa.example.demo.Model.Recipe;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends CrudRepository<Recipe, Long>{
    Optional<Recipe> findByDescription(String description);
    Optional<Recipe> findByCookTime(Integer cookTime);

}
