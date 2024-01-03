package rs.ac.singidunum.tasktastic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
}
