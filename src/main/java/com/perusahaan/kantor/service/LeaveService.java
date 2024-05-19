package com.perusahaan.kantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perusahaan.kantor.entity.Employee;
import com.perusahaan.kantor.entity.Leave;
import com.perusahaan.kantor.repository.EmployeeRepository;
import com.perusahaan.kantor.repository.LeaveRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    private final LeaveRepository leaveRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public LeaveService(LeaveRepository leaveRepository, EmployeeRepository employeeRepository) {
        this.leaveRepository = leaveRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional
    public Leave createLeave(Leave leave) {
        Employee employee = leave.getEmployee();
        if (employee != null && employee.getId() != null) {
            employee = employeeRepository.findById(employee.getId()).orElse(employee);
        }
        leave.setEmployee(employee);
        return leaveRepository.save(leave);
    }

    @Transactional
    public Leave updateLeave(Long id, Leave updatedLeave) {
        Optional<Leave> existingLeave = leaveRepository.findById(id);
        if (existingLeave.isPresent()) {
            Leave leave = existingLeave.get();
            leave.setStartDate(updatedLeave.getStartDate());
            leave.setEndDate(updatedLeave.getEndDate());
            leave.setReason(updatedLeave.getReason());

            Employee employee = updatedLeave.getEmployee();
            if (employee != null && employee.getId() != null) {
                employee = employeeRepository.findById(employee.getId()).orElse(employee);
                leave.setEmployee(employee);
            } else {
                leave.setEmployee(employee);
            }

            return leaveRepository.save(leave);
        } else {
            return null; // Handle the case where the leave is not found
        }
    }

    @Transactional
    public void deleteLeave(Long id) {
        leaveRepository.deleteById(id);
    }

    @Transactional
    public Leave findLeaveById(Long id) {
        return leaveRepository.findById(id).orElse(null);
    }

    @Transactional
    public List<Leave> findAllLeaves() {
        return leaveRepository.findAll();
    }
}
