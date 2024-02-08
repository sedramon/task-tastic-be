package rs.ac.singidunum.tasktastic.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import rs.ac.singidunum.tasktastic.model.Task;

import java.util.List;

public interface TaskRepository extends MongoRepository<Task, ObjectId> {
    Task findById(String id);
    List<Task> findByUser_Id(String userId);
}
