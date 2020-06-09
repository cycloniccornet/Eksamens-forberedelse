package jpa.example.demo.Controller;

import jpa.example.demo.Model.Category;
import jpa.example.demo.Repository.CategoryRepository;
import jpa.example.demo.Repository.RecipeRepository;
import org.hibernate.sql.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CategoryController {

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @GetMapping("/")
    private String index(){
        for (Category category : categoryRepository.findAll()) {
            System.out.println(category.getDescription());
        }
        return "index";
    }

    @GetMapping("/one")
    public String one(){
        System.out.println(categoryRepository.findById(Integer.toUnsignedLong(1)).get().getDescription());
    return "index";
    }

    @GetMapping("/catdesc")
    public String getCategory(){
        System.out.println(categoryRepository.findByDescription("American").get().getDescription());
        return "index";
    }

    @GetMapping("/recipe")
    public String getRecipe(){
    System.out.println("recipe: " + recipeRepository.findByDescription("porrige").get().getDescription());
    System.out.println("Cook Time: " + recipeRepository.findByDescription("porrige").get().getCookTime());
    System.out.println("Notes: " + recipeRepository.findByDescription("porrige").get().getNotes().getDescription());
    System.out.println("found by Cook Time: " + recipeRepository.findByCookTime(20).get().getDescription());
    return "index";
    }

    @GetMapping("/category")
    public String insertCategory(Model model){
        model.addAttribute(new Category());
        return "category";
    }

    @PostMapping("/category")
    public String saveCategory(@ModelAttribute Category category){
        categoryRepository.save(category);
        return "redirect:/";
    }
}
