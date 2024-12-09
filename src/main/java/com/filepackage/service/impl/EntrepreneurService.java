package com.filepackage.service.impl;

import com.filepackage.Exception.ResourceNotFoundException;
import com.filepackage.dto.EntrepreneurDto;
import com.filepackage.entity.Entrepreneur;
import com.filepackage.entity.User;
import com.filepackage.mapper.AutoMapper;
import com.filepackage.repository.IEntrepreneurRepository;
import com.filepackage.repository.IUserRepository;
import com.filepackage.service.IEntrepreneurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntrepreneurService implements IEntrepreneurService {

    private final IEntrepreneurRepository entrepreneurRepository;
    private final IUserRepository userRepository;
    private final AutoMapper autoMapper;

    @Autowired
    public EntrepreneurService(IEntrepreneurRepository entrepreneurRepository, AutoMapper autoMapper, IUserRepository userRepository) {
        this.entrepreneurRepository = entrepreneurRepository;
        this.autoMapper = autoMapper;
        this.userRepository = userRepository;
    }

    @Override
    public EntrepreneurDto getById(Long entrepreneurId) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur not found with ID: " + entrepreneurId));
        return autoMapper.convertToDto(entrepreneur, EntrepreneurDto.class);
    }

    @Override
    public List<EntrepreneurDto> getAll() {
        List<Entrepreneur> entrepreneurs = entrepreneurRepository.findAll();
        return entrepreneurs.stream()
                .map(entrepreneur -> autoMapper.convertToDto(entrepreneur, EntrepreneurDto.class))
                .collect(Collectors.toList());
    }

  /*  @Override
    public EntrepreneurDto createEntrepreneur(EntrepreneurDto entrepreneurDto) {
        Entrepreneur entrepreneur = autoMapper.convertToEntity(entrepreneurDto, Entrepreneur.class);

        Entrepreneur savedEntrepreneur = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(savedEntrepreneur, EntrepreneurDto.class);
    }*/

  /*  @Override
    public EntrepreneurDto createEntrepreneur (EntrepreneurDto entrepreneurDto) {
        Entrepreneur entrepreneur = autoMapper.convertToEntity(entrepreneurDto, Entrepreneur.class);

        // User ile ilişkiyi kur
        User user = userRepository.findById(entrepreneurDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + entrepreneurDto.getUserId()));
        entrepreneur.setUser(user);

        Entrepreneur savedEntrepreneur = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(savedEntrepreneur, EntrepreneurDto.class);
    }*/


    @Override
    public EntrepreneurDto createEntrepreneur(EntrepreneurDto entrepreneurDto) {
        // Giriş yapmış kullanıcının kimliğini al
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            System.out.println("there is some error with auth");
        }

        // Kullanıcının bilgilerini al
        //getprincipal giris yapmis kullanicinin kimlik bilgilerini doner
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();

        // Kullanıcıyı veritabanından çek
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        // Entrepreneur entity'sini oluştur ve kullanıcıyla ilişkilendir
        Entrepreneur entrepreneur = autoMapper.convertToEntity(entrepreneurDto, Entrepreneur.class);
        entrepreneur.setUser(user); // Kullanıcı ID'sini otomatik ekle

        // Entrepreneur kaydet
        Entrepreneur savedEntrepreneur = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(savedEntrepreneur, EntrepreneurDto.class);
    }


    @Override
    public EntrepreneurDto update(Long entrepreneurId, EntrepreneurDto updatedEntrepreneurDto) {
        Entrepreneur entrepreneur = entrepreneurRepository.findById(entrepreneurId)
                .orElseThrow(() -> new ResourceNotFoundException("Entrepreneur not found with ID: " + entrepreneurId));

        entrepreneur.setFirstName(updatedEntrepreneurDto.getFirstName());
        entrepreneur.setLastName(updatedEntrepreneurDto.getLastName());
        entrepreneur.setProfilePicture(updatedEntrepreneurDto.getProfilePicture());
        entrepreneur.setEmail(updatedEntrepreneurDto.getEmail());
        entrepreneur.setPassword(updatedEntrepreneurDto.getPassword());
        entrepreneur.setBio(updatedEntrepreneurDto.getBio());
        entrepreneur.setPhoneNumber(updatedEntrepreneurDto.getPhoneNumber());
        entrepreneur.setPhoneVisibility(updatedEntrepreneurDto.getPhoneVisibility());

        Entrepreneur savedEntrepreneur = entrepreneurRepository.save(entrepreneur);
        return autoMapper.convertToDto(savedEntrepreneur, EntrepreneurDto.class);
    }

    @Override
    public void delete(Long entrepreneurId) {
        if (!entrepreneurRepository.existsById(entrepreneurId)) {
            throw new ResourceNotFoundException("Entrepreneur not found with ID: " + entrepreneurId);
        }
        entrepreneurRepository.deleteById(entrepreneurId);
    }
}
