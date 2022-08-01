package com.vaadin.project.view.connector;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.service.JournalistService;

public class ConnectGridToFormImpl implements ConnectGridToForm {
    private final Grid<Journalist> grid;
    private final JournalistService service;

    public ConnectGridToFormImpl(Grid<Journalist> grid, JournalistService service) {
        this.grid = grid;
        this.service = service;
    }

    @Override
    public void update() {
        grid.setItems(service.findAll());
    }
}
