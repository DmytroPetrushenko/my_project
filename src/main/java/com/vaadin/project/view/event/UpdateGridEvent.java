package com.vaadin.project.view.event;

import org.springframework.context.ApplicationEvent;

public class UpdateGridEvent extends ApplicationEvent {

    public UpdateGridEvent(Object source) {
        super(source);
    }
}
