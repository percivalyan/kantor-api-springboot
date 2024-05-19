package com.perusahaan.kantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perusahaan.kantor.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

