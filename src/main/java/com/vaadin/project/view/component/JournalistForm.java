package com.vaadin.project.view.component;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.project.model.Address;
import com.vaadin.project.model.Journalist;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.event.UpdateGridEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class JournalistForm extends FormLayout {
    private final ApplicationEventPublisher publisher;
    private final JournalistService service;
    private final TextField firstName = new TextField("First name");
    private final TextField lastName = new TextField("Second name");
    private final TextField title = new TextField("Title");
    private final TextField mediaOutlets = new TextField("Media outlets");
    private final EmailField emailAddress = new EmailField("Email");
    private final TextField twitterName = new TextField("Twitter name");
    private final TextField twitterUrl = new TextField("Twitter Url");
    private final TextField linkedInUrl = new TextField("LinkId Url");
    private final TextField facebookUrl = new TextField("Facebook Url");
    private final TextField instagramUrl = new TextField("Instagram Url");
    private final TextField mediaOutletPhoneNumber = new TextField("Phone number");
    private final TextField mediaOutletAddress = new TextField("Media outlet address");
    private final TextField address2 = new TextField("Address 2");
    private final TextField city = new TextField("City");
    private final TextField state = new TextField("State");
    private final TextField country = new TextField("Country");
    private final TextField zipCode = new TextField("Zip Code");
    private final Button save = new Button("SAVE");
    private final Button delete = new Button("DELETE");
    private final Button close = new Button("CLOSE");
    private final Binder<Journalist> binder = new Binder<>(Journalist.class);
    private final Binder<Address> addressBinder = new Binder<>(Address.class);

    public JournalistForm(ApplicationEventPublisher publisher, JournalistService service) {
        this.publisher = publisher;
        this.service = service;

        add(addFieldLayout(), addButtonLayout());

        binder.forField(firstName).bind(Journalist::getFirstName, Journalist::setFirstName);
        binder.forField(lastName).bind(Journalist::getLastName, Journalist::setLastName);
        binder.forField(title).bind(Journalist::getTitle, Journalist::setTitle);
        binder.forField(mediaOutlets).bind(Journalist::getMediaOutlets, Journalist::setMediaOutlets);
        binder.forField(emailAddress).bind(Journalist::getEmailAddress,Journalist::setEmailAddress);
        binder.forField(twitterName).bind(Journalist::getTwitterName, Journalist::setTwitterName);
        binder.forField(twitterUrl).bind(Journalist::getTwitterUrl, Journalist::setTwitterUrl);
        binder.forField(linkedInUrl).bind(Journalist::getLinkedInUrl, Journalist::setLinkedInUrl);
        binder.forField(facebookUrl).bind(Journalist::getFacebookUrl, Journalist::setFacebookUrl);
        binder.forField(instagramUrl).bind(Journalist::getInstagramUrl, Journalist::setInstagramUrl);
        binder.forField(mediaOutletPhoneNumber).bind(Journalist::getMediaOutletPhoneNumber,
                Journalist::setMediaOutletPhoneNumber);
        addressBinder.forField(mediaOutletAddress).bind(Address::getMediaOutletAddress,
                Address::setMediaOutletAddress);
        addressBinder.forField(address2).bind(Address::getAddress2, Address::setAddress2);
        addressBinder.forField(city).bind(Address::getCity, Address::setCity);
        addressBinder.forField(state).bind(Address::getState, Address::setState);
        addressBinder.forField(country).bind(Address::getCountry, Address::setAddress2);
        addressBinder.forField(zipCode).bind(Address::getZipCode, Address::setZipCode);
        clearForm();

        save.addClickListener(event -> {
            Journalist journalist = binder.getBean();
            Address address = addressBinder.getBean();
            journalist.setAddress(address);
            service.save(journalist);
            Notification show = Notification.show(binder.getBean().getClass().getSimpleName()
                    + " details stored.");
            show.setPosition(Notification.Position.TOP_END);
            updateGrid();
            clearForm();
        });

        delete.addClickListener(event -> {
            service.delete(binder.getBean());
            Notification show = Notification.show(binder.getBean().getClass().getSimpleName()
                    + " details deleted.");
            show.setPosition(Notification.Position.TOP_END);
            updateGrid();
            clearForm();
        });

        close.addClickListener(event -> clearForm());
    }

    private void updateGrid() {
        UpdateGridEvent event = new UpdateGridEvent(this);
        publisher.publishEvent(event);
    }

    private void clearForm() {
        binder.setBean(new Journalist());
        addressBinder.setBean(new Address());
    }

    private HorizontalLayout addButtonLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        return new HorizontalLayout(save, delete, close);
    }

    private VerticalLayout addFieldLayout() {
        VerticalLayout fieldsLayout = new VerticalLayout(firstName, lastName, title,
                mediaOutlets, emailAddress, twitterName, twitterUrl,
                linkedInUrl, facebookUrl, instagramUrl, mediaOutletPhoneNumber,
                mediaOutletAddress, address2, city, state, country, zipCode);
        fieldsLayout.setAlignItems(FlexComponent.Alignment.STRETCH);
        return fieldsLayout;
    }

    public void updateForm(Journalist journalist) {
        binder.setBean(journalist);

    }
}
