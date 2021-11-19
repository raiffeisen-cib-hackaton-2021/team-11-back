package com.codenrock.raifcib21.flashlight.ui;

import com.codenrock.raifcib21.flashlight.entity.MessageToUserEntity;
import com.codenrock.raifcib21.flashlight.repository.MessageToUserRepository;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.DataProvider;
import com.vaadin.flow.router.Route;

@Route
public class MessagesView extends VerticalLayout {

    public MessagesView() {
        setSizeUndefined();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(new H1("Отправленные сообщения"), grid());
    }

    private Grid<MessageToUserEntity> grid() {
        try {
            var repo = VaadinServiceAccessor.get(MessageToUserRepository.class);
            var component = new Grid<>(MessageToUserEntity.class);
            component.setWidth(75f, Unit.PERCENTAGE);
            var entities = repo.findAll();
            component.setDataProvider(DataProvider.ofCollection(entities));
            component.setColumns("id", "communicationType", "channelTypes", "message", "userIds", "userTypes", "segmentTypes", "companyIds", "time");
            setHorizontalComponentAlignment(Alignment.CENTER, component);
            component.asSingleSelect().addValueChangeListener(e -> {
                repo.delete(e.getValue());
                var changed = repo.findAll();
                component.setDataProvider(DataProvider.ofCollection(changed));
                component.getDataProvider().refreshAll();
            });
            return component;
        } catch (Exception e) {
            return null;
        }
    }
}
