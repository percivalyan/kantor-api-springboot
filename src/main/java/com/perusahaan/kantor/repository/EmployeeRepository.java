package com.perusahaan.kantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perusahaan.kantor.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}

