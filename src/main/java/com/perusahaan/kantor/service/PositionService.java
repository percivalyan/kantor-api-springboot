package com.perusahaan.kantor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.perusahaan.kantor.entity.Position;
import com.perusahaan.kantor.repository.PositionRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PositionService {
    private final PositionRepository positionRepository;

    @Autowired
    public PositionService(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    public Position createPosition(Position position) {
        return positionRepository.save(position);
    }

    public Position updatePosition(Long id, Position updatedPosition) {
        Optional<Position> existingPosition = positionRepository.findById(id);
        if (existingPosition.isPresent()) {
            updatedPosition.setId(id);
            return positionRepository.save(updatedPosition);
        } else {
            // Handle not found
            return null;
        }
    }

    public void deletePosition(Long id) {
        positionRepository.deleteById(id);
    }

    public Optional<Position> findPositionById(Long id) {
        return positionRepository.findById(id);
    }

    public List<Position> findAllPositions() {
        return positionRepository.findAll();
    }
}
