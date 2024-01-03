package rs.ac.singidunum.tasktastic.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.tasktastic.model.Task;

public interface TaskRepository extends MongoRepository<Task, ObjectId> {
    Task findById(String id);
    Task findByUserId(String userId);
}
