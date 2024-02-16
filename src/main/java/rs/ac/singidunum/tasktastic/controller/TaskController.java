package rs.ac.singidunum.tasktastic.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.tasktastic.model.Task;
import rs.ac.singidunum.tasktastic.model.User;
import rs.ac.singidunum.tasktastic.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping()
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<List<Task>>(taskService.getAllTasks(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Task> getOneTask(@PathVariable String id) {
        return new ResponseEntity<Task>(taskService.getTaskById(id), HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Task> addTask(@RequestBody Task task) {
        task.setFinished(false);
        return new ResponseEntity<Task>(taskService.addTask(task), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTaskToFinished(@PathVariable String id, @RequestBody Task task) {
        return new ResponseEntity<Task>(taskService.updateTaskisFinished(id, task.isFinished()), HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable String id) {
        System.out.println("Fetching tasks for user ID: " + id);
        List<Task> tasks = taskService.getTasksByUserId(id);
        System.out.println("Number of tasks found: " + tasks.size());
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @DeleteMapping("/{taskId}")
    public ResponseEntity<String> removeTask(@PathVariable String taskId) {
        return taskService.deleteTask(taskId);
    }
}
