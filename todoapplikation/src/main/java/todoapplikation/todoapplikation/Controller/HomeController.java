package todoapplikation.todoapplikation.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import todoapplikation.todoapplikation.Model.ToDoItem;
import todoapplikation.todoapplikation.Repository.ToDoRepository;

import java.util.Optional;

@Controller
public class HomeController {

    @Autowired
    ToDoRepository toDoRepository;


    // Read All
    @CrossOrigin(origins = "*")
    @GetMapping("/todo/")
    public ResponseEntity<Iterable<ToDoItem>> readAll() {
        Iterable<ToDoItem> toDoItems = toDoRepository.findAll();
        if (toDoItems.iterator().hasNext()) {
            return ResponseEntity.status(200).body(toDoItems);
        } else {
            return ResponseEntity.status(200).body(toDoItems);
        }
   }
    
    // Read one
    @CrossOrigin(origins = "*")
    @GetMapping("/todo/{id}")
    public ResponseEntity<Optional<ToDoItem>> getOne(@PathVariable Long id){
        Optional<ToDoItem> toDoItem = toDoRepository.findById(id);

        if (toDoItem.isPresent()){
            return ResponseEntity.status(200).body(toDoItem);
        } else {
            return ResponseEntity.status(404).body(toDoItem);
        }
    }

    //Insert
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping("/todo")
    public ResponseEntity<String> insert(@ModelAttribute ToDoItem toDoItem){
        ToDoItem saveToDoItem = toDoRepository.save(toDoItem);
        return ResponseEntity.status(201).header("Location", "/todo/" + saveToDoItem.getId()).body("{msg : 'to do created'}");
    }

    //Delete
    @CrossOrigin(origins = "*")
    @DeleteMapping("/todo/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) { // ResponceEntity er en indpakning - gør at man kan sende header body osv.
    toDoRepository.deleteById(id);
    return ResponseEntity.status(200).body("{msg : 'to do deleted'}");
    }

    //Update
    @CrossOrigin(origins = "*")
    @PutMapping("/todo/")
    public ResponseEntity<String> update(@ModelAttribute ToDoItem toDoItem){
        toDoRepository.save(toDoItem);
        return ResponseEntity.status(204).body("{msg : 'to do updated'}");
    }
}
