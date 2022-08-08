package com.vaadin.project.view.event;

import com.vaadin.project.config.Config;
import com.vaadin.project.dto.JournalistAddressDto;
import com.vaadin.project.dto.mapper.JournalistAddressMapper;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.component.JournalistGrid;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

@Service
public class UpdateGridListener implements ApplicationListener<UpdateGridEvent> {

    private final JournalistService service;
    private final JournalistAddressMapper mapper;

    public UpdateGridListener(JournalistService service,
                              JournalistAddressMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    public void onApplicationEvent(UpdateGridEvent event) {
        List<JournalistAddressDto> dtoList = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        Config.getList().stream()
                .map(JournalistGrid::getGrid)
                .forEach(grid -> grid.setItems(dtoList));
    }
}
