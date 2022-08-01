package com.vaadin.project.view;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MultiFileMemoryBuffer;
import com.vaadin.project.service.JournalistService;
import com.vaadin.project.view.connector.ConnectGridToForm;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.InputStream;

public class JournalistUploadDiv extends Div {

    public JournalistUploadDiv(ConnectGridToForm connectGridToForm,
                               JournalistService journalistService) {
        MultiFileMemoryBuffer buffer = new MultiFileMemoryBuffer();
        Upload upload = new Upload(buffer);

        upload.addSucceededListener(event -> {
            String fileName = event.getFileName();
            InputStream stream = buffer.getInputStream(fileName);
            journalistService.parseToJdbc(stream);
            connectGridToForm.update();
        });

        setSizeFull();
        add(upload);
    }



}
