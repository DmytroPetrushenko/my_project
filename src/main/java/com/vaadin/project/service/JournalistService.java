package com.vaadin.project.service;

import com.vaadin.project.model.Journalist;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;

public interface JournalistService {
    void parseToJdbc(InputStream inputStream);

    List<Journalist> findAll();

    Journalist save(Journalist journalist);

    void delete(Journalist journalist);
}
