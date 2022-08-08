package com.vaadin.project.view.component;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.event.UpdateGridEvent;
import java.io.InputStream;
import org.springframework.context.ApplicationEventPublisher;


public class JournalistUpload extends Div {
    private final ApplicationEventPublisher publisher;
    private final JournalistService service;

    public JournalistUpload(ApplicationEventPublisher publisher,
                            JournalistService service) {
        this.publisher = publisher;
        this.service = service;

        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream stream = buffer.getInputStream(fileName);
            this.service.parseToJdbc(stream);
            updateGrid();
        });
        add(upload);
    }

    private void updateGrid() {
        UpdateGridEvent event = new UpdateGridEvent(this);
        publisher.publishEvent(event);
    }
}
