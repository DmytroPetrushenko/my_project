package com.vaadin.project.view;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.dao.JournalistDao;
import org.springframework.stereotype.Component;

@Component
public class JournalistGridDiv extends Div {
    private Grid<Journalist> grid;
    private Grid.Column<Journalist> firstNameColumn;
    private Grid.Column<Journalist> secondNameColumn;
    private Grid.Column<Journalist> emailColumn;

    public JournalistGridDiv(JournalistDao service) {
        addClassName("journalist-grid-div");
        setSizeFull();
        createGrid(service);
        add(grid);
    }

    private void createGrid(JournalistDao service) {
        creatGridComponent(service);
        addColumnsToGrid();
    }

    private void addColumnsToGrid() {
        firstNameColumn = grid.addColumn(Journalist::getFirstName).setHeader("First name");
        secondNameColumn = grid.addColumn(Journalist::getSecondName).setHeader("Second name");
        emailColumn = grid.addColumn(Journalist::getEmail).setHeader("Email");

    }

    private void creatGridComponent(JournalistDao service) {
        grid = new Grid<>();
        grid.setSelectionMode(Grid.SelectionMode.MULTI);
        grid.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_COLUMN_BORDERS);
        grid.setHeightFull();
        grid.setAllRowsVisible(true);
        grid.setItems(service.findAll());
    }

    public Grid<Journalist> getGrid() {
        return grid;
    }
}
