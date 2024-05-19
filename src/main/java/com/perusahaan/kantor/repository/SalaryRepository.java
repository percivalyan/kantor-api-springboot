package com.perusahaan.kantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perusahaan.kantor.entity.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Long> {
}

