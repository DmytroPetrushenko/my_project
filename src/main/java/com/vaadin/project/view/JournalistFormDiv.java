package com.vaadin.project.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.project.dao.JournalistDao;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.view.connector.ConnectGridToForm;

public class JournalistFormDiv extends Div {
    private TextField firstName = new TextField("First Name");
    private TextField secondName = new TextField("Second Name");
    private EmailField email = new EmailField("Email");
    private Button save = new Button("SAVE");
    private Binder<Journalist> binder = new Binder<>(Journalist.class);

    public JournalistFormDiv(ConnectGridToForm connectGridToForm, JournalistDao service) {
        addClassName("journalist-div");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        save.addClickListener(event -> {
            service.save(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
            connectGridToForm.update();
        });
    }

    private void clearForm() {
        binder.setBean(new Journalist());
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        return buttonLayout;
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();
        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(firstName, secondName, email);
        return formLayout;
    }

    private Component createTitle() {
        return new H3("Journalist information");
    }
}
