package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.InvestorsDto;
import com.filepackage.entity.Investors;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IInvestorsRepository;
import com.filepackage.service.IInvestorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestorsService implements IInvestorsService<InvestorsDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final IInvestorsRepository investorsRepository;

    @Autowired
    public InvestorsService(IInvestorsRepository investorsRepository) {
        this.investorsRepository = investorsRepository;
    }

    @Override
    public InvestorsDto getById(Long investorsId) {
        Investors investors = investorsRepository.findById(investorsId)
                .orElseThrow(() -> new ResourceNotFoundException("Investors does not exist with given id: " + investorsId));
        return autoMapper.convertToDto(investors, InvestorsDto.class);
    }

    @Override
    public List<InvestorsDto> getAll() {
        List<Investors> investorsList = investorsRepository.findAll();
        return investorsList.stream()
                .map(investors -> autoMapper.convertToDto(investors, InvestorsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long investorsId) {
        investorsRepository.findById(investorsId)
                .orElseThrow(() -> new ResourceNotFoundException("Investors does not exist with given id: " + investorsId));
        investorsRepository.deleteById(investorsId);
    }

    @Override
    public InvestorsDto update(Long investorsId, InvestorsDto updatedInvestors) {
        Investors investors = investorsRepository.findById(investorsId)
                .orElseThrow(() -> new ResourceNotFoundException("Investors does not exist with given id: " + investorsId));

        investors.setInvestor_id(updatedInvestors.getInvestor_id());
        investors.setEmail(updatedInvestors.getEmail());
        investors.setPassword(updatedInvestors.getPassword());
        investors.setBio(updatedInvestors.getBio());
        investors.setLocation(updatedInvestors.getLocation());
        investors.setCreated_at(updatedInvestors.getCreated_at());
        investors.setFirst_name(updatedInvestors.getFirst_name());
        investors.setLast_name(updatedInvestors.getLast_name());
        investors.setPhone_number(updatedInvestors.getPhone_number());
        investors.setPhone_visibility(updatedInvestors.getPhone_visibility());
        investors.setProfile_picture(updatedInvestors.getProfile_picture());

        Investors updatedEntity = investorsRepository.save(investors);
        return autoMapper.convertToDto(updatedEntity, InvestorsDto.class);
    }

    @Override
    public InvestorsDto createInvestors(InvestorsDto investorsDto) {
        Investors investors = autoMapper.convertToEntity(investorsDto, Investors.class);
        Investors savedInvestors = investorsRepository.save(investors);
        return autoMapper.convertToDto(savedInvestors, InvestorsDto.class);
    }
}
