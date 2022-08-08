package com.vaadin.project.view.page;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.splitlayout.SplitLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.project.MainView;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.component.JournalistForm;
import com.vaadin.project.view.component.JournalistGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Route(value = "", layout = MainView.class)
@Component
public class JournalistEditorView extends VerticalLayout {

    public JournalistEditorView(@Autowired JournalistGrid grid,
                                @Autowired JournalistForm form) {


        SplitLayout splitLayout = new SplitLayout();
        splitLayout.addToPrimary(grid);
        splitLayout.addToSecondary(form);
        splitLayout.setSplitterPosition(70);
        splitLayout.setWidth("100%");
        add(splitLayout);
    }
}
