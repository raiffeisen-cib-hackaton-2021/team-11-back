package com.codenrock.raifcib21.flashlight.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ui/admin")
public class AdminView extends VerticalLayout {
    public AdminView() {
        add(new H1("Welcome to Flashlight admin panel."));
    }
}
