package com.perusahaan.kantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perusahaan.kantor.entity.Employee;
import com.perusahaan.kantor.entity.Task;
import com.perusahaan.kantor.repository.EmployeeRepository;
import com.perusahaan.kantor.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository, EmployeeRepository employeeRepository) {
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
    }

    public Task createTask(Task task) {
        Employee employee = task.getEmployee();
        if (employee != null && employee.getId() != null) {
            employee = employeeRepository.findById(employee.getId()).orElse(employee);
        }
        task.setEmployee(employee);
        return taskRepository.save(task);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> existingTask = taskRepository.findById(id);
        if (existingTask.isPresent()) {
            Task task = existingTask.get();
            task.setName(updatedTask.getName());
            task.setDeadline(updatedTask.getDeadline());
            task.setStatus(updatedTask.getStatus());

            Employee employee = updatedTask.getEmployee();
            if (employee != null && employee.getId() != null) {
                employee = employeeRepository.findById(employee.getId()).orElse(employee);
                task.setEmployee(employee);
            } else {
                task.setEmployee(employee);
            }

            return taskRepository.save(task);
        } else {
            return null; // Handle the case where the task is not found
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }
}
