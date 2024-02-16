package rs.ac.singidunum.tasktastic.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import rs.ac.singidunum.tasktastic.model.Task;
import rs.ac.singidunum.tasktastic.repository.TaskRepository;
import rs.ac.singidunum.tasktastic.repository.UserRepository;

import java.time.*;
import java.util.Date;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks(){return taskRepository.findAll();}

    public Task getTaskById(String id){return taskRepository.findById(id);}

    public Task addTask(Task task){
        task.setUser(userRepository.findById(task.getUser().getId()));
        return taskRepository.save(task);
    }

    public List<Task> getTasksByUserId(String id) {
        // When fetching tasks by user ID, use the findByUserIdWithUser() method
        return taskRepository.findByUser_Id(id);
    }

    public Task updateTaskisFinished(String id, boolean isFinished){
        Task existingTask = taskRepository.findById(id);
        existingTask.setFinished(isFinished);
        return taskRepository.save(existingTask);
    }

    public ResponseEntity<String> deleteTask(@PathVariable String taskId) {
        ObjectId objectId = new ObjectId(taskId);
        if (taskRepository.existsById(objectId)) {
            taskRepository.deleteById(objectId);
            return ResponseEntity.ok("Task deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }
    }

}
