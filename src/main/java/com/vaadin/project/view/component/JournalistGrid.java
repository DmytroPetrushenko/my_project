package com.vaadin.project.view.component;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.project.dto.JournalistAddressDto;
import com.vaadin.project.dto.mapper.JournalistAddressMapper;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.event.ChooseFormEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class JournalistGrid extends Div {
    private final JournalistService service;
    private final JournalistAddressMapper mapper;
    @Autowired
    private ApplicationEventPublisher publisher;
    private Grid<JournalistAddressDto> grid;
    private JournalistAddressDto selectedItem;

    public JournalistGrid(JournalistService service,
                          JournalistAddressMapper mapper) {
        this.service = service;
        this.mapper = mapper;

        createGrid();
        add(grid);
    }

    private void createGrid() {
        createGridComponent();
        addColumnsToGrid();
    }

    private void addColumnsToGrid() {
        grid.addColumn(JournalistAddressDto::getFirstName).setHeader("First name");
        grid.addColumn(JournalistAddressDto::getLastName).setHeader("Last Name")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getTitle).setHeader("Title")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getEmailAddress).setHeader("Email")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getTwitterName).setHeader("Twitter Name")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getTwitterUrl).setHeader("Twitter Url")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getLinkedInUrl).setHeader("LinkId Url")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getFacebookUrl).setHeader("Facebook Url")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getInstagramUrl).setHeader("Instagram Url")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getMediaOutletPhoneNumber).setHeader("Phone number")
                .setSortable(true).setAutoWidth(true);
        grid.addColumn(JournalistAddressDto::getMediaOutletAddress).setHeader("Address")
                .setSortable(true).setAutoWidth(true);
    }

    private void createGridComponent() {
        grid = new Grid<>(JournalistAddressDto.class, false);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setAllRowsVisible(true);
        List<JournalistAddressDto> dtoList = service.findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
        grid.setItems(dtoList);

        grid.addSelectionListener(event -> {
            Optional<JournalistAddressDto> selectedItemOptional = event.getFirstSelectedItem();
            if (selectedItemOptional.isPresent()) {
                selectedItem = selectedItemOptional.get();
                updateForm();
            }
        });
    }

    public void updateForm() {
        ChooseFormEvent event = new ChooseFormEvent(this);
        publisher.publishEvent(event);
    }

    public Grid<JournalistAddressDto> getGrid() {
        return grid;
    }

    public JournalistAddressDto getSelectedItem() {
        return selectedItem;
    }
}
