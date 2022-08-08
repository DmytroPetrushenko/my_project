package com.vaadin.project.view.event;

import com.vaadin.project.dto.JournalistAddressDto;
import com.vaadin.project.dto.mapper.JournalistAddressMapper;
import com.vaadin.project.view.component.JournalistForm;
import com.vaadin.project.view.component.JournalistGrid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ChooseFormListener implements ApplicationListener<ChooseFormEvent> {
    @Autowired
    private JournalistForm form;
    private final JournalistAddressMapper mapper;

    public ChooseFormListener(JournalistAddressMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onApplicationEvent(ChooseFormEvent event) {
        Object source = event.getSource();
        if (source.getClass().equals(JournalistGrid.class)) {
            JournalistGrid grid = (JournalistGrid) source;
            JournalistAddressDto selectedItem = grid.getSelectedItem();
            form.updateForm(mapper.toModel(selectedItem));
        };
    }
}
