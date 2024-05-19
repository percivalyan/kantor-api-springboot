package com.perusahaan.kantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perusahaan.kantor.entity.Employee;
import com.perusahaan.kantor.entity.Salary;
import com.perusahaan.kantor.repository.EmployeeRepository;
import com.perusahaan.kantor.repository.SalaryRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SalaryService {

    private final SalaryRepository salaryRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public SalaryService(SalaryRepository salaryRepository, EmployeeRepository employeeRepository) {
        this.salaryRepository = salaryRepository;
        this.employeeRepository = employeeRepository;
    }

    public Salary createSalary(Salary salary) {
        Employee employee = salary.getEmployee();
        if (employee != null && employee.getId() != null) {
            employee = employeeRepository.findById(employee.getId()).orElse(employee);
        }
        salary.setEmployee(employee);
        return salaryRepository.save(salary);
    }

    public Salary updateSalary(Long id, Salary updatedSalary) {
        Optional<Salary> existingSalary = salaryRepository.findById(id);
        if (existingSalary.isPresent()) {
            Salary salary = existingSalary.get();
            salary.setAmount(updatedSalary.getAmount());
            salary.setStartDate(updatedSalary.getStartDate());
            salary.setEndDate(updatedSalary.getEndDate());

            Employee employee = updatedSalary.getEmployee();
            if (employee != null && employee.getId() != null) {
                employee = employeeRepository.findById(employee.getId()).orElse(employee);
                salary.setEmployee(employee);
            } else {
                salary.setEmployee(employee);
            }

            return salaryRepository.save(salary);
        } else {
            return null; // Handle the case where the salary is not found
        }
    }

    public void deleteSalary(Long id) {
        salaryRepository.deleteById(id);
    }

    public Salary findSalaryById(Long id) {
        return salaryRepository.findById(id).orElse(null);
    }

    public List<Salary> findAllSalaries() {
        return salaryRepository.findAll();
    }
}
