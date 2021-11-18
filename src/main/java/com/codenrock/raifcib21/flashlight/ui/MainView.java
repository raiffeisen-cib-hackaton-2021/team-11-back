package com.codenrock.raifcib21.flashlight.ui;

import com.codenrock.raifcib21.flashlight.entity.MessageFromUserEntity;
import com.codenrock.raifcib21.flashlight.repository.MessageFromUserRepository;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;

@Route
@SpringComponent
public class MainView extends VerticalLayout {
    private final MessageFromUserRepository repository;

    public MainView(MessageFromUserRepository repository) {
        this.repository = repository;
        setSizeUndefined();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(new H1("Обратная связь"), grid());

    }

    private Grid<MessageFromUserEntity> grid() {
        var component = new Grid<>(MessageFromUserEntity.class);
        component.setWidth(75f, Unit.PERCENTAGE);
        component.setItems(repository.findAll());
        component.setColumns("id", "sourceType", "channelType", "liked", "disliked", "message", "userId", "companyId", "time");
        setHorizontalComponentAlignment(Alignment.CENTER, component);
        return component;
    }
}
