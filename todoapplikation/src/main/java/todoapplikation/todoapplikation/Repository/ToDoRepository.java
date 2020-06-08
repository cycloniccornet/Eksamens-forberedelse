package todoapplikation.todoapplikation.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import todoapplikation.todoapplikation.Model.ToDoItem;

@Repository
public interface ToDoRepository extends CrudRepository<ToDoItem, Long> {
}
