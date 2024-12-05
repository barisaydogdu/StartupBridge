package com.filepackage.controller;

import com.filepackage.dto.InvestorsDto;
import com.filepackage.service.impl.InvestorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/investors")
public class InvestorsController {

    @Autowired
    private InvestorsService investorsService;


    @GetMapping
    public ResponseEntity<List<InvestorsDto>> getAllInvestors() {
        List<InvestorsDto> investors = investorsService.getAll();
        return ResponseEntity.ok(investors);
    }


    @PostMapping
    public ResponseEntity<InvestorsDto> addInvestor(@RequestBody InvestorsDto investorsDto) {
        InvestorsDto savedInvestor = investorsService.createInvestors(investorsDto);
        return new ResponseEntity<>(savedInvestor, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<InvestorsDto> getInvestorById(@PathVariable("id") Long investorId) {
        InvestorsDto investorsDto = investorsService.getById(investorId);
        return ResponseEntity.ok(investorsDto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInvestor(@PathVariable("id") Long investorId) {
        investorsService.delete(investorId);
        return ResponseEntity.ok("Investor deleted successfully");
    }


    @PutMapping("/{id}")
    public ResponseEntity<InvestorsDto> updateInvestor(@PathVariable("id") Long investorId, @RequestBody InvestorsDto updatedInvestor) {
        InvestorsDto investorsDto = investorsService.update(investorId, updatedInvestor);
        return ResponseEntity.ok(investorsDto);
    }
}
