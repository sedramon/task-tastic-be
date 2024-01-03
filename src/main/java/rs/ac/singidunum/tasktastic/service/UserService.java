package rs.ac.singidunum.tasktastic.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.tasktastic.model.User;
import rs.ac.singidunum.tasktastic.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(String id) {
        return userRepository.findById(id);
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(String id, User user) {

        Optional<User> optionalUser = userRepository.findById(new ObjectId(id));

        if (!optionalUser.isPresent()) {
            return null;
        }

        User existingUser = optionalUser.get();

        existingUser.setUsername(user.getUsername());
        existingUser.setEmail(user.getEmail());
        existingUser.setPassword(user.getPassword());

        return userRepository.save(existingUser);
    }

    public void deleteUser(String id) {
        userRepository.deleteById(new ObjectId(id));
    }
}
