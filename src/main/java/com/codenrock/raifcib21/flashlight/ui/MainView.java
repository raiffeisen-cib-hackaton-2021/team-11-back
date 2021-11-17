package com.codenrock.raifcib21.flashlight.ui;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("ui")
public class MainView extends VerticalLayout {
    public MainView() {
        add(new H1("Welcome to Flashlight."));
    }
}
