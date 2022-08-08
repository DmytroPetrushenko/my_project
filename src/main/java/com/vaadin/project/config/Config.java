package com.vaadin.project.config;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.project.dto.mapper.JournalistAddressMapper;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.component.JournalistGrid;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class Config {
    private static final List<JournalistGrid> list = new ArrayList<>();
    private final JournalistService service;
    private final JournalistAddressMapper mapper;

    public Config(JournalistService service,
                  JournalistAddressMapper mapper, ApplicationEventPublisher publisher) {
        this.service = service;
        this.mapper = mapper;
    }

    @Bean
    @Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public JournalistGrid getGrid() {
        JournalistGrid grid = new JournalistGrid(service, mapper);
        list.add(grid);
        return grid;
    }

    public static List<JournalistGrid> getList() {
        return list;
    }
}

