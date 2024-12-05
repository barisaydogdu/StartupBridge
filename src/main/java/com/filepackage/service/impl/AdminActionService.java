package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.AdminActionDto;
import com.filepackage.entity.AdminAction;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IAdminActionRepository;
import com.filepackage.service.IAdminActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminActionService implements IAdminActionService {

    private final IAdminActionRepository adminActionRepository;
    private final AutoMapper autoMapper;

    @Autowired
    public AdminActionService(IAdminActionRepository adminActionRepository, AutoMapper autoMapper) {
        this.adminActionRepository = adminActionRepository;
        this.autoMapper = autoMapper;
    }

    @Override
    public List<AdminActionDto> getAll() {
        List<AdminAction> actions = adminActionRepository.findAll();
        return actions.stream()
                .map(action -> autoMapper.convertToDto(action, AdminActionDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public AdminActionDto createAdminAction(AdminActionDto adminActionDto) {
        AdminAction adminAction = autoMapper.convertToEntity(adminActionDto, AdminAction.class);
        AdminAction savedAction = adminActionRepository.save(adminAction);
        return autoMapper.convertToDto(savedAction, AdminActionDto.class);
    }

    @Override
    public AdminActionDto getById(Long actionId) {
        AdminAction adminAction = adminActionRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin Action not found with ID: " + actionId));
        return autoMapper.convertToDto(adminAction, AdminActionDto.class);
    }

    @Override
    public void delete(Long actionId) {
        if (!adminActionRepository.existsById(actionId)) {
            throw new ResourceNotFoundException("Admin Action not found with ID: " + actionId);
        }
        adminActionRepository.deleteById(actionId);
    }

    @Override
    public AdminActionDto update(Long actionId, AdminActionDto updatedActionDto) {
        AdminAction adminAction = adminActionRepository.findById(actionId)
                .orElseThrow(() -> new ResourceNotFoundException("Admin Action not found with ID: " + actionId));

        adminAction.setAdminId(updatedActionDto.getAdminId());
        adminAction.setActionType(updatedActionDto.getActionType());
        adminAction.setTargetId(updatedActionDto.getTargetId());
        adminAction.setActionDescription(updatedActionDto.getActionDescription());

        AdminAction savedAction = adminActionRepository.save(adminAction);
        return autoMapper.convertToDto(savedAction, AdminActionDto.class);
    }
}
