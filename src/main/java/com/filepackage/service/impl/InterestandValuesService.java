package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.InterestandValuesDto;
import com.filepackage.entity.InterestandValues;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IInterestandValuesRepository;
import com.filepackage.service.IInterestandValuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InterestandValuesService implements IInterestandValuesService<InterestandValuesDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final IInterestandValuesRepository interestandValuesRepository;

    @Autowired
    public InterestandValuesService(IInterestandValuesRepository interestandValuesRepository) {
        this.interestandValuesRepository = interestandValuesRepository;
    }

    @Override
    public InterestandValuesDto getById(Long interestandValuesId) {
        InterestandValues interestandValues = interestandValuesRepository.findById(interestandValuesId)
                .orElseThrow(() -> new ResourceNotFoundException("InterestandValues does not exist with given id: " + interestandValuesId));
        return autoMapper.convertToDto(interestandValues, InterestandValuesDto.class);
    }

    @Override
    public List<InterestandValuesDto> getAll() {
        List<InterestandValues> interestandValuesList = interestandValuesRepository.findAll();
        return interestandValuesList.stream()
                .map(interestandValues -> autoMapper.convertToDto(interestandValues, InterestandValuesDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long interestandValuesId) {
        interestandValuesRepository.findById(interestandValuesId)
                .orElseThrow(() -> new ResourceNotFoundException("InterestandValues does not exist with given id: " + interestandValuesId));
        interestandValuesRepository.deleteById(interestandValuesId);
    }

    @Override
    public InterestandValuesDto update(Long interestandValuesId, InterestandValuesDto updatedInterestandValues) {
        InterestandValues interestandValues = interestandValuesRepository.findById(interestandValuesId)
                .orElseThrow(() -> new ResourceNotFoundException("InterestandValues does not exist with given id: " + interestandValuesId));

        interestandValues.setCreated_at(updatedInterestandValues.getCreated_at());
        interestandValues.setInterest_area(updatedInterestandValues.getInterest_area());
        interestandValues.setInterest_id(interestandValues.getInterest_id());
        interestandValues.setInvestor_id(interestandValues.getInvestor_id());
        interestandValues.setCreated_at(updatedInterestandValues.getCreated_at());

        InterestandValues updatedEntity = interestandValuesRepository.save(interestandValues);
        return autoMapper.convertToDto(updatedEntity, InterestandValuesDto.class);
    }

    @Override
    public InterestandValuesDto createInterestandValues(InterestandValuesDto interestandValuesDto) {
        InterestandValues interestandValues = autoMapper.convertToEntity(interestandValuesDto, InterestandValues.class);
        InterestandValues savedInterestandValues = interestandValuesRepository.save(interestandValues);
        return autoMapper.convertToDto(savedInterestandValues, InterestandValuesDto.class);
    }
}
