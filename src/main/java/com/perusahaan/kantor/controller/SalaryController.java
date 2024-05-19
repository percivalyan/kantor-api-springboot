package com.perusahaan.kantor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perusahaan.kantor.entity.Salary;
import com.perusahaan.kantor.service.SalaryService;

import java.util.List;

@RestController
@RequestMapping("/salaries")
public class SalaryController {
    private final SalaryService salaryService;

    @Autowired
    public SalaryController(SalaryService salaryService) {
        this.salaryService = salaryService;
    }

    @PostMapping
    public ResponseEntity<Salary> createSalary(@RequestBody Salary salary) {
        Salary createdSalary = salaryService.createSalary(salary);
        return new ResponseEntity<>(createdSalary, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Salary> updateSalary(@PathVariable Long id, @RequestBody Salary salary) {
        Salary updatedSalary = salaryService.updateSalary(id, salary);
        if (updatedSalary != null) {
            return new ResponseEntity<>(updatedSalary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSalary(@PathVariable Long id) {
        salaryService.deleteSalary(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Salary> findSalaryById(@PathVariable Long id) {
        Salary salary = salaryService.findSalaryById(id);
        if (salary != null) {
            return new ResponseEntity<>(salary, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Salary>> findAllSalaries() {
        List<Salary> salaries = salaryService.findAllSalaries();
        return new ResponseEntity<>(salaries, HttpStatus.OK);
    }
}
