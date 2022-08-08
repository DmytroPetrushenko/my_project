package com.vaadin.project;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.project.view.page.JournalistEditorView;
import com.vaadin.project.view.page.JournalistUploadView;

@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends AppLayout {
    public MainView(){

        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Email Navigator");
        HorizontalLayout header = new HorizontalLayout(
                new DrawerToggle(),
                logo
        );

        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);
        header.setWidth("100%");

        logo.getStyle()
                .set("font-size", "var(--lumo-font-size-l)")
                .set("line-height", "var(--lumo-size-l)")
                .set("margin", "0 var(--lumo-space-m)");

        addToNavbar(header);
    }

    private void createDrawer() {
        RouterLink editorViewLink = new RouterLink("EDITOR", JournalistEditorView.class);
        RouterLink uploadViewLink = new RouterLink("TO UPLOAD", JournalistUploadView.class);
        editorViewLink.setHighlightCondition(HighlightConditions.sameLocation());
        addToDrawer(new VerticalLayout(editorViewLink, uploadViewLink));
    }


}
