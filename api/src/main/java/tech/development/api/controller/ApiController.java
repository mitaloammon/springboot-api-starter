package tech.development.api.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping(path = "/users/{userId}/tasks")

public class ApiController {

    private final Map<Integer, List<Task>> userTasks = new HashMap<>();
    private final ObjectMapper objectMapper;

    public ApiController(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @GetMapping
    public ResponseEntity<List<Task>> getTasksByUser(@PathVariable int userId) {
        List<Task> tasks = userTasks.getOrDefault(userId, new ArrayList<>());
        return ResponseEntity.ok(tasks);
    }

    @PostMapping
    public ResponseEntity<Task> createTaskForUser(@PathVariable int userId, @RequestBody Task task) {
        task.setUserId(userId);
        task.setUserName("Username " + userId);
        userTasks.putIfAbsent(userId, new ArrayList<>());
        userTasks.get(userId).add(task);
        return ResponseEntity.ok(task);
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTasksForUser(@PathVariable int userId) {
        userTasks.remove(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{taskIndex}")
    public ResponseEntity<Map<String, Object>> updateTaskForUser(
        @PathVariable int userId,
        @PathVariable int taskIndex,
        @RequestBody Task updatedTask
    ) 
    {
        List<Task> tasks = userTasks.get(userId);

        if (tasks == null || taskIndex < 0 || taskIndex >= tasks.size()) {
            return ResponseEntity.notFound().build();
        }

        updatedTask.setUserId(userId);
        updatedTask.setUserName("Username " + userId);

        tasks.set(taskIndex, updatedTask);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Task updated successfully");
        response.put("task", updatedTask);

        return ResponseEntity.ok(response);
    }

}
