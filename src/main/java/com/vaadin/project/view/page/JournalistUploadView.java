package com.vaadin.project.view.page;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.project.MainView;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.component.JournalistGrid;
import com.vaadin.project.view.component.JournalistUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

@Route(value = "upload", layout = MainView.class)
public class JournalistUploadView extends Div {

    public JournalistUploadView(@Autowired JournalistService service,
                                @Autowired ApplicationEventPublisher publisher,
                                @Autowired JournalistGrid grid) {

        add(new VerticalLayout(new JournalistUpload(publisher, service), grid));
    }
}
