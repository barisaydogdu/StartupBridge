package com.filepackage.controller;

import com.filepackage.dto.InterestandValuesDto;
import com.filepackage.service.impl.InterestandValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/interestandvalues")
public class InterestsandValuesController {

    @Autowired
    private InterestandValuesService interestandValuesService;


    @GetMapping
    public ResponseEntity<List<InterestandValuesDto>> getAllInterestandValues() {
        List<InterestandValuesDto> interestandValues = interestandValuesService.getAll();
        return ResponseEntity.ok(interestandValues);
    }


    @PostMapping
    public ResponseEntity<InterestandValuesDto> addInterestandValues(@RequestBody InterestandValuesDto interestandValuesDto) {
        InterestandValuesDto savedInterestandValues = interestandValuesService.createInterestandValues(interestandValuesDto);
        return new ResponseEntity<>(savedInterestandValues, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InterestandValuesDto> getInterestandValuesById(@PathVariable("id") Long interestandValuesId) {
        InterestandValuesDto interestandValuesDto = interestandValuesService.getById(interestandValuesId);
        return ResponseEntity.ok(interestandValuesDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInterestandValues(@PathVariable("id") Long interestandValuesId) {
        interestandValuesService.delete(interestandValuesId);
        return ResponseEntity.ok("Interest and values deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<InterestandValuesDto> updateInterestandValues(@PathVariable("id") Long interestandValuesId, @RequestBody InterestandValuesDto updatedInterestandValues) {
        InterestandValuesDto interestandValuesDto = interestandValuesService.update(interestandValuesId, updatedInterestandValues);
        return ResponseEntity.ok(interestandValuesDto);
    }
}
