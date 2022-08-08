package com.vaadin.project.view.event;

import org.springframework.context.ApplicationEvent;

public class ChooseFormEvent extends ApplicationEvent {

    public ChooseFormEvent(Object source) {
        super(source);
    }
}
