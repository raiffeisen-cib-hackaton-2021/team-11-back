package com.codenrock.raifcib21.flashlight.ui;

import com.codenrock.raifcib21.flashlight.model.ChannelType;
import com.codenrock.raifcib21.flashlight.model.CommunicationType;
import com.codenrock.raifcib21.flashlight.model.MessageToUser;
import com.codenrock.raifcib21.flashlight.model.SegmentType;
import com.codenrock.raifcib21.flashlight.model.UserType;
import com.codenrock.raifcib21.flashlight.service.MessageToUserService;
import com.vaadin.componentfactory.multiselect.MultiComboBox;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.vaadin.maxime.MarkdownArea;

import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.stream.Collectors;

@Route("admin")
public class AdminView extends VerticalLayout {

    private final ComboBox<CommunicationType> communicationType = communicationType();
    private final MultiComboBox<ChannelType> channelTypes = channelTypes();
    private final MultiComboBox<UserType> userTypes = userTypes();
    private final MultiComboBox<SegmentType> segmentTypes = segmentTypes();
    private final TextArea userIds = userIds();
    private final TextArea companyIds = companyIds();
    private final MarkdownArea message = message();

    public AdminView() {
        setSizeUndefined();
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);
        add(new H1("Ручная отправка сообщений"), getForm(), submitButton());
    }

    private FormLayout getForm() {
        var form = new FormLayout();
        form.setWidth(75f, Unit.PERCENTAGE);
        form.setResponsiveSteps(
                new FormLayout.ResponsiveStep("25em", 1),
                new FormLayout.ResponsiveStep("32em", 2));
        form.add(communicationType, channelTypes, userTypes, segmentTypes, userIds, companyIds);
        form.add(message, 2);
        setHorizontalComponentAlignment(Alignment.CENTER, form);
        return form;
    }

    private ComboBox<CommunicationType> communicationType() {
        var component = new ComboBox<CommunicationType>("Тип коммуникации");
        component.setRequired(true);
        component.setItems(CommunicationType.values());
        component.setItemLabelGenerator(CommunicationType::name);
        return component;
    }

    private MultiComboBox<ChannelType> channelTypes() {
        var component = new MultiComboBox<ChannelType>("Каналы связи");
        component.setRequired(true);
        component.setItems(ChannelType.values());
        component.setItemLabelGenerator(ChannelType::name);
        return component;
    }

    private MultiComboBox<UserType> userTypes() {
        var component = new MultiComboBox<UserType>("Категории пользователей");
        component.setRequired(true);
        component.setItems(UserType.values());
        component.setItemLabelGenerator(UserType::name);
        return component;
    }

    private MultiComboBox<SegmentType> segmentTypes() {
        var component = new MultiComboBox<SegmentType>("Сегменты");
        component.setRequired(true);
        component.setItems(SegmentType.values());
        component.setItemLabelGenerator(SegmentType::name);
        return component;
    }

    private MarkdownArea message() {
        var component = new MarkdownArea();
        component.setMarkdownLink("https://www.markdownguide.org/getting-started/");
        return component;
    }

    private TextArea userIds() {
        var component = new TextArea("Идентификаторы пользователей");
        component.setPlaceholder("Через запятую");
        return component;
    }

    private TextArea companyIds() {
        var component = new TextArea("Идентификаторы компаний");
        component.setPlaceholder("Через запятую");
        return component;
    }

    private Button submitButton() {
        var component = new Button("Отправить сообщение");
        component.addClickListener(this::onSubmit);
        component.setDisableOnClick(true);
        component.addClickShortcut(Key.ENTER);
        component.addThemeVariants(ButtonVariant.LUMO_SUCCESS);
        return component;
    }

    private void onSubmit(ClickEvent<Button> event) {
        var notification = new Notification();
        notification.setDuration(3000);
        try {
            var service = VaadinServiceAccessor.get(MessageToUserService.class);
            var toUser = MessageToUser.builder()
                    .communicationType(communicationType.getValue())
                    .channelTypes(channelTypes.getValue())
                    .userTypes(userTypes.getValue())
                    .segmentTypes(segmentTypes.getValue())
                    .userIds(Collections.emptyList())
                    .companyIds(Collections.emptyList())
                    .message(message.getValue())
                    .build();
            if (userIds.getValue() != null && !userIds.getValue().isEmpty()) {
                toUser.setUserIds(Arrays.stream(userIds.getValue().split(",")).map(UUID::fromString).collect(Collectors.toSet()));
            }
            if (userIds.getValue() != null && !userIds.getValue().isEmpty()) {
                toUser.setCompanyIds(Arrays.stream(companyIds.getValue().split(",")).map(UUID::fromString).collect(Collectors.toSet()));
            }
            toUser = service.persist(toUser);
            service.send(toUser);
            notification.setText("Сообщение отправлено");
            notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        } catch (Exception e) {
            notification.setText("Сообщение не отправлено");
            notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
            throw new RuntimeException(e);
        } finally {
            notification.open();
            event.getSource().setEnabled(true);
        }
    }
}
