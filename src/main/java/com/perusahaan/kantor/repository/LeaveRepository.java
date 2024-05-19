package com.perusahaan.kantor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.perusahaan.kantor.entity.Leave;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
}

