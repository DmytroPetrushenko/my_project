package com.vaadin.project.dao;

import com.vaadin.project.model.Journalist;
import java.io.Serializable;
import java.util.List;

public interface JournalistDao extends Serializable {
    List<Journalist> findAll();

    Journalist save(Journalist journalist);
}
