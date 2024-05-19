package com.perusahaan.kantor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.perusahaan.kantor.entity.Position;
import com.perusahaan.kantor.service.PositionService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/positions")
public class PositionController {
    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @PostMapping
    public Position createPosition(@RequestBody Position position) {
        return positionService.createPosition(position);
    }

    @PutMapping("/{id}")
    public Position updatePosition(@PathVariable Long id, @RequestBody Position position) {
        return positionService.updatePosition(id, position);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionService.deletePosition(id);
    }

    @GetMapping("/{id}")
    public Optional<Position> findPositionById(@PathVariable Long id) {
        return positionService.findPositionById(id);
    }

    @GetMapping
    public List<Position> findAllPositions() {
        return positionService.findAllPositions();
    }
}
