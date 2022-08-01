package com.vaadin.project;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.project.dao.JournalistDao;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.JournalistFormDiv;
import com.vaadin.project.view.JournalistGridDiv;
import com.vaadin.project.view.JournalistUploadDiv;
import com.vaadin.project.view.connector.ConnectGridToForm;
import com.vaadin.project.view.connector.ConnectGridToFormImpl;
import org.springframework.beans.factory.annotation.Autowired;

@Route("")
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {
    public MainView(@Autowired JournalistService journalistService){
        JournalistGridDiv grid = new JournalistGridDiv(journalistService);
        ConnectGridToForm connectGridToForm = new ConnectGridToFormImpl(grid.getGrid(), journalistService);

        JournalistFormDiv form = new JournalistFormDiv(connectGridToForm, journalistService);
        JournalistUploadDiv upload = new JournalistUploadDiv(connectGridToForm, journalistService);

        HorizontalLayout horizontalLayout = new HorizontalLayout(grid, new VerticalLayout(upload, form));
        horizontalLayout.setSizeFull();
        add(horizontalLayout);
    }
}
