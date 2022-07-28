package com.vaadin.project.service;

import com.vaadin.project.model.Journalist;

import java.io.Serializable;
import java.util.List;

public interface JournalistService extends Serializable {
    List<Journalist> findAll();

    Journalist save(Journalist journalist);
}
