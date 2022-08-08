package com.vaadin.project.dto.mapper;

import com.vaadin.project.dto.JournalistAddressDto;
import com.vaadin.project.model.Address;
import com.vaadin.project.model.Journalist;
import org.springframework.stereotype.Component;

@Component
public class JournalistAddressMapper {

    public Journalist toModel(JournalistAddressDto dto) {
        Journalist journalist = new Journalist();
        journalist.setId(dto.getId());
        journalist.setFirstName(dto.getFirstName());
        journalist.setLastName(dto.getLastName());
        journalist.setTitle(dto.getTitle());
        journalist.setEmailAddress(dto.getEmailAddress());
        journalist.setMediaOutlets(dto.getMediaOutlets());
        journalist.setTwitterName(dto.getTwitterName());
        journalist.setTwitterUrl(dto.getTwitterUrl());
        journalist.setLinkedInUrl(dto.getLinkedInUrl());
        journalist.setFacebookUrl(dto.getFacebookUrl());
        journalist.setInstagramUrl(dto.getInstagramUrl());
        journalist.setMediaOutletPhoneNumber(dto.getMediaOutletPhoneNumber());
        Address address = new Address();
        address.setId(dto.getAddressId());
        address.setMediaOutletAddress(dto.getMediaOutletAddress());
        address.setAddress2(dto.getAddress2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setCountry(dto.getCountry());
        address.setZipCode(dto.getZipCode());
        journalist.setAddress(address);
        return journalist;
    }

    public JournalistAddressDto toDto(Journalist journalist) {
        JournalistAddressDto dto = new JournalistAddressDto();
        dto.setId(journalist.getId());
        dto.setFirstName(journalist.getFirstName());
        dto.setLastName(journalist.getLastName());
        dto.setTitle(journalist.getTitle());
        dto.setMediaOutlets(journalist.getMediaOutlets());
        dto.setEmailAddress(journalist.getEmailAddress());
        dto.setEmailAddress(journalist.getEmailAddress());
        dto.setTwitterName(journalist.getTwitterName());
        dto.setTwitterUrl(journalist.getTwitterUrl());
        dto.setLinkedInUrl(journalist.getLinkedInUrl());
        dto.setFacebookUrl(journalist.getFacebookUrl());
        dto.setInstagramUrl(journalist.getInstagramUrl());
        dto.setMediaOutletPhoneNumber(journalist.getMediaOutletPhoneNumber());
        Address address = journalist.getAddress();
        dto.setAddressId(address.getId());
        dto.setMediaOutletAddress(address.getMediaOutletAddress());
        dto.setAddress2(address.getAddress2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setCountry(address.getCountry());
        dto.setZipCode(address.getZipCode());
        return dto;
    }
}
