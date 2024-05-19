package com.perusahaan.kantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perusahaan.kantor.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}

