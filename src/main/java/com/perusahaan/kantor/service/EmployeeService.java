package com.perusahaan.kantor.service;

import com.perusahaan.kantor.entity.Department;
import com.perusahaan.kantor.entity.Employee;
import com.perusahaan.kantor.entity.Position;
import com.perusahaan.kantor.repository.DepartmentRepository;
import com.perusahaan.kantor.repository.EmployeeRepository;
import com.perusahaan.kantor.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Transactional
    public Employee createEmployee(Employee employee) {
        // Ensure the Department and Position entities are managed before saving Employee
        Department department = employee.getDepartment();
        if (department != null && department.getId() != null) {
            department = departmentRepository.findById(department.getId()).orElse(department);
        }
        employee.setDepartment(department);

        Position position = employee.getPosition();
        if (position != null && position.getId() != null) {
            position = positionRepository.findById(position.getId()).orElse(position);
        }
        employee.setPosition(position);

        return employeeRepository.save(employee);
    }

    @Transactional
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setFirstName(employeeDetails.getFirstName());
            employee.setLastName(employeeDetails.getLastName());
            employee.setEmail(employeeDetails.getEmail());
            employee.setPhone(employeeDetails.getPhone());

            Department departmentDetails = employeeDetails.getDepartment();
            if (departmentDetails != null && departmentDetails.getId() != null) {
                Department department = departmentRepository.findById(departmentDetails.getId()).orElse(departmentDetails);
                employee.setDepartment(department);
            } else {
                employee.setDepartment(departmentDetails);
            }

            Position positionDetails = employeeDetails.getPosition();
            if (positionDetails != null && positionDetails.getId() != null) {
                Position position = positionRepository.findById(positionDetails.getId()).orElse(positionDetails);
                employee.setPosition(position);
            } else {
                employee.setPosition(positionDetails);
            }

            return employeeRepository.save(employee);
        } else {
            return null; // Handle the case where the Employee is not found
        }
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
