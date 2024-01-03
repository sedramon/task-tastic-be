package rs.ac.singidunum.tasktastic.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import rs.ac.singidunum.tasktastic.model.User;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findById(String id);
    User findByUsername(String name);
}
