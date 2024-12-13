package com.filepackage.service.impl;

import com.filepackage.Exception.AccessDeniedException;
import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.InvestorsDto;
import com.filepackage.entity.Investors;
import com.filepackage.entity.User;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IInvestorsRepository;
import com.filepackage.repository.IUserRepository;
import com.filepackage.service.IInvestorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InvestorsService implements IInvestorsService<InvestorsDto, Long> {

    @Autowired
    AutoMapper autoMapper;

    private final IInvestorsRepository investorsRepository;
    private final IUserRepository userRepository;
    @Autowired
    public InvestorsService(IInvestorsRepository investorsRepository, IUserRepository userRepository) {
        this.investorsRepository = investorsRepository;
        this.userRepository = userRepository;
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new AccessDeniedException("Not authenticated");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userRepository.findByName(userDetails.getUsername())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        //kullanici sadece kendi profilini guncelleyebilir
        if (!investors.getUser().getId().equals(currentUser.getId())) {
            throw new AccessDeniedException("You can only update your own profile");
        }
        investors.setFirst_name(updatedInvestors.getFirst_name());
        investors.setLast_name(updatedInvestors.getLast_name());
        investors.setProfile_picture(updatedInvestors.getProfile_picture());
        investors.setEmail(updatedInvestors.getEmail());
        investors.setPassword(updatedInvestors.getPassword());
        investors.setBio(updatedInvestors.getBio());
        investors.setPhone_number(updatedInvestors.getPhone_number());
        investors.setCreated_at(updatedInvestors.getCreated_at());
        investors.setLocation(updatedInvestors.getLocation());
        investors.setPhone_visibility(updatedInvestors.getPhone_visibility());

//        investors.setInvestor_id(updatedInvestors.getInvestor_id());

        Investors updatedEntity = investorsRepository.save(investors);
        return autoMapper.convertToDto(updatedEntity, InvestorsDto.class);
    }

    @Override
    public InvestorsDto createInvestors(InvestorsDto investorsDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication== null || !authentication.isAuthenticated()) {
            System.out.println("there is something wrong with auth");
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        //kullaniciyi veritabanindan cek
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Investors investors = autoMapper.convertToEntity(investorsDto, Investors.class);
        investors.setUser(user);

        Investors savedInvestors = investorsRepository.save(investors);
        return autoMapper.convertToDto(savedInvestors, InvestorsDto.class);
    }
}
