package com.filepackage.controller;

import com.filepackage.dto.EntrepreneurDto;
import com.filepackage.service.impl.EntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/entrepreneurs")
public class EntrepreneurController {

    private final EntrepreneurService entrepreneurService;

    @Autowired
    public EntrepreneurController(EntrepreneurService entrepreneurService) {
        this.entrepreneurService = entrepreneurService;
    }

    @GetMapping
    public ResponseEntity<List<EntrepreneurDto>> getAllEntrepreneurs() {
        List<EntrepreneurDto> entrepreneurs = entrepreneurService.getAll();
        return ResponseEntity.ok(entrepreneurs);
    }

    @PostMapping
    public ResponseEntity<EntrepreneurDto> addEntrepreneur(@RequestBody EntrepreneurDto entrepreneurDto) {
        EntrepreneurDto savedEntrepreneur = entrepreneurService.createEntrepreneur(entrepreneurDto);
        return new ResponseEntity<>(savedEntrepreneur, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntrepreneurDto> getEntrepreneurById(@PathVariable("id") Long entrepreneurId) {
        EntrepreneurDto entrepreneurDto = entrepreneurService.getById(entrepreneurId);
        return ResponseEntity.ok(entrepreneurDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteEntrepreneur(@PathVariable("id") Long entrepreneurId) {
        entrepreneurService.delete(entrepreneurId);
        return ResponseEntity.ok("Entrepreneur deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntrepreneurDto> updateEntrepreneur(
            @PathVariable("id") Long entrepreneurId,
            @RequestBody EntrepreneurDto updatedEntrepreneur) {
        EntrepreneurDto entrepreneurDto = entrepreneurService.update(entrepreneurId, updatedEntrepreneur);
        return ResponseEntity.ok(entrepreneurDto);
    }
}
