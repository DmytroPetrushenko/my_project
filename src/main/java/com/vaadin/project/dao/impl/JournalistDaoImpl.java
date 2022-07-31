package com.vaadin.project.dao.impl;

import com.vaadin.project.dao.JournalistDao;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.repository.JournalistRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class JournalistDaoImpl implements JournalistDao {
    private final JournalistRepository repository;

    public JournalistDaoImpl(JournalistRepository repository) {
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
