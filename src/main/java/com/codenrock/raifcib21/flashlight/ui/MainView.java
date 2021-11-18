package com.codenrock.raifcib21.flashlight.ui;

import com.codenrock.raifcib21.flashlight.entity.MessageFromUserEntity;
import com.codenrock.raifcib21.flashlight.repository.MessageFromUserRepository;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;

@Route
public class MainView extends VerticalLayout {

    public MainView() {
        setSizeUndefined();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(new H1("Обратная связь"), grid());

    }

    private Grid<MessageFromUserEntity> grid() {
        try {
            var component = new Grid<>(MessageFromUserEntity.class);
            component.setWidth(75f, Unit.PERCENTAGE);
            var entities = VaadinServiceAccessor.get(MessageFromUserRepository.class).findAll();
            component.setDataProvider(DataProvider.ofCollection(entities));
            component.setColumns("id", "sourceType", "channelType", "liked", "disliked", "message", "userId", "companyId", "time");
            setHorizontalComponentAlignment(Alignment.CENTER, component);
            return component;
        } catch (Exception e) {
            return null;
        }
    }
}
