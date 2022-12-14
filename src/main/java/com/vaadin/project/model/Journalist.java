package com.vaadin.project.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Journalist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @OneToOne
    private Address address;
}
