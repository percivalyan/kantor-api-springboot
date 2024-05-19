package com.perusahaan.kantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perusahaan.kantor.entity.Attendance;
import com.perusahaan.kantor.entity.Employee;
import com.perusahaan.kantor.repository.AttendanceRepository;
import com.perusahaan.kantor.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AttendanceService(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    public Attendance createAttendance(Attendance attendance) {
        Employee employee = attendance.getEmployee();
        if (employee != null && employee.getId() != null) {
            employee = employeeRepository.findById(employee.getId()).orElse(employee);
        }
        attendance.setEmployee(employee);
        return attendanceRepository.save(attendance);
    }

    public Attendance updateAttendance(Long id, Attendance updatedAttendance) {
        Optional<Attendance> existingAttendance = attendanceRepository.findById(id);
        if (existingAttendance.isPresent()) {
            Attendance attendance = existingAttendance.get();
            attendance.setDate(updatedAttendance.getDate());
            attendance.setStatus(updatedAttendance.getStatus());

            Employee employee = updatedAttendance.getEmployee();
            if (employee != null && employee.getId() != null) {
                employee = employeeRepository.findById(employee.getId()).orElse(employee);
                attendance.setEmployee(employee);
            } else {
                attendance.setEmployee(employee);
            }

            return attendanceRepository.save(attendance);
        } else {
            return null; // Handle the case where the attendance is not found
        }
    }

    public void deleteAttendance(Long id) {
        attendanceRepository.deleteById(id);
    }

    public Attendance findAttendanceById(Long id) {
        return attendanceRepository.findById(id).orElse(null);
    }

    public List<Attendance> findAllAttendances() {
        return attendanceRepository.findAll();
    }
}
