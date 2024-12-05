package com.filepackage.controller;

import com.filepackage.dto.AdminActionDto;
import com.filepackage.service.impl.AdminActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin-actions")
public class AdminActionController {

    private final AdminActionService adminActionService;

    @Autowired
    public AdminActionController(AdminActionService adminActionService) {
        this.adminActionService = adminActionService;
    }

    @GetMapping
    public ResponseEntity<List<AdminActionDto>> getAllActions() {
        List<AdminActionDto> actions = adminActionService.getAll();
        return ResponseEntity.ok(actions);
    }

    @PostMapping
    public ResponseEntity<AdminActionDto> addAction(@RequestBody AdminActionDto adminActionDto) {
        AdminActionDto savedAction = adminActionService.createAdminAction(adminActionDto);
        return new ResponseEntity<>(savedAction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdminActionDto> getActionById(@PathVariable("id") Long actionId) {
        AdminActionDto adminActionDto = adminActionService.getById(actionId);
        return ResponseEntity.ok(adminActionDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable("id") Long actionId) {
        adminActionService.delete(actionId);
        return ResponseEntity.ok("Admin action deleted successfully");
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdminActionDto> updateAction(
            @PathVariable("id") Long actionId,
            @RequestBody AdminActionDto updatedAdminActionDto) {
        AdminActionDto updatedAction = adminActionService.update(actionId, updatedAdminActionDto);
        return ResponseEntity.ok(updatedAction);
    }
}
