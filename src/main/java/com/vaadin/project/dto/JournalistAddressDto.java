package com.vaadin.project.dto;

import lombok.Data;

@Data
public class JournalistAddressDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String title;
    private String mediaOutlets;
    private String emailAddress;
    private String twitterName;
    private String twitterUrl;
    private String linkedInUrl;
    private String facebookUrl;
    private String instagramUrl;
    private String mediaOutletPhoneNumber;
    private Long addressId;
    private String mediaOutletAddress;
    private String address2;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
