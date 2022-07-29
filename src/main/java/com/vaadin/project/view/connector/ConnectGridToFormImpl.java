package com.vaadin.project.view.connector;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.project.dao.JournalistDao;
import com.vaadin.project.model.Journalist;

public class ConnectGridToFormImpl implements ConnectGridToForm {
    private final Grid<Journalist> grid;
    private final JournalistDao service;

    public ConnectGridToFormImpl(Grid<Journalist> grid, JournalistDao service) {
        this.grid = grid;
        this.service = service;
    }

    @Override
    public void update() {
        grid.setItems(service.findAll());
    }
}
