package com.perusahaan.kantor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.perusahaan.kantor.entity.Leave;
import com.perusahaan.kantor.service.LeaveService;

import java.util.List;

@RestController
@RequestMapping("/leaves")
public class LeaveController {
    private final LeaveService leaveService;

    @Autowired
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @PostMapping
    public ResponseEntity<Leave> createLeave(@RequestBody Leave leave) {
        Leave createdLeave = leaveService.createLeave(leave);
        return new ResponseEntity<>(createdLeave, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leave> updateLeave(@PathVariable Long id, @RequestBody Leave leave) {
        Leave updatedLeave = leaveService.updateLeave(id, leave);
        if (updatedLeave != null) {
            return new ResponseEntity<>(updatedLeave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLeave(@PathVariable Long id) {
        leaveService.deleteLeave(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leave> findLeaveById(@PathVariable Long id) {
        Leave leave = leaveService.findLeaveById(id);
        if (leave != null) {
            return new ResponseEntity<>(leave, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Leave>> findAllLeaves() {
        List<Leave> leaves = leaveService.findAllLeaves();
        return new ResponseEntity<>(leaves, HttpStatus.OK);
    }
}
