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

    @Autowired
    private AdminActionService adminActionService;

    // Get all admin actions
    @GetMapping
    public ResponseEntity<List<AdminActionDto>> getAllActions() {
        List<AdminActionDto> actions = adminActionService.getAll();
        return ResponseEntity.ok(actions);
    }

    // Add new admin action
    @PostMapping
    public ResponseEntity<AdminActionDto> addAction(@RequestBody AdminActionDto adminActionDto) {
        // Validate action type
        if (adminActionDto.getActionType() == null || !isValidActionType(adminActionDto.getActionType())) {
            return new ResponseEntity<>("Invalid action type", HttpStatus.BAD_REQUEST);
        }
        
        AdminActionDto savedAction = adminActionService.createAction(adminActionDto);
        return new ResponseEntity<>(savedAction, HttpStatus.CREATED);
    }

    // Get admin action by ID
    @GetMapping("{id}")
    public ResponseEntity<AdminActionDto> getActionById(@PathVariable("id") Long actionId) {
        AdminActionDto adminActionDto = adminActionService.getById(actionId);
        return ResponseEntity.ok(adminActionDto);
    }

    // Delete admin action by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAction(@PathVariable("id") Long actionId) {
        adminActionService.delete(actionId);
        return ResponseEntity.ok("Admin action deleted successfully");
    }

    // Update admin action by ID
    @PutMapping("{id}")
    public ResponseEntity<AdminActionDto> updateAction(@PathVariable("id") Long actionId, @RequestBody AdminActionDto updatedAction) {
        // Validate action type
        if (updatedAction.getActionType() == null || !isValidActionType(updatedAction.getActionType())) {
            return new ResponseEntity<>("Invalid action type", HttpStatus.BAD_REQUEST);
        }

        AdminActionDto adminActionDto = adminActionService.update(actionId, updatedAction);
        return ResponseEntity.ok(adminActionDto);
    }

    // Helper method to validate action type
    private boolean isValidActionType(String actionType) {
        return actionType.equals("Kullanıcı Yönetimi") ||
               actionType.equals("Proje Yönetimi") ||
               actionType.equals("İçerik Yönetimi") ||
               actionType.equals("Bildirim");
    }
}
