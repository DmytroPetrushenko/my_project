package com.vaadin.project.service.impl;

import com.vaadin.project.service.JournalistService;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.repository.JournalistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalistServiceImpl implements JournalistService {
    private final JournalistRepository repository;

    public JournalistServiceImpl(JournalistRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Journalist> findAll() {
        return repository.findAll();
    }

    @Override
    public Journalist save(Journalist journalist) {
        return repository.save(journalist);
    }
}
