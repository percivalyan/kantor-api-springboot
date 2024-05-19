package com.perusahaan.kantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perusahaan.kantor.entity.Department;
import com.perusahaan.kantor.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department updatedDepartment) {
        Optional<Department> existingDepartment = departmentRepository.findById(id);
        if (existingDepartment.isPresent()) {
            updatedDepartment.setId(id);
            return departmentRepository.save(updatedDepartment);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    public Optional<Department> findDepartmentById(Long id) {
        return departmentRepository.findById(id);
    }

    public List<Department> findAllDepartments() {
        return departmentRepository.findAll();
    }
}
