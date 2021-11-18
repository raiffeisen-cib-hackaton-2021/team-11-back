package com.codenrock.raifcib21.flashlight.ui;

import com.codenrock.raifcib21.flashlight.model.ChannelType;
import com.codenrock.raifcib21.flashlight.model.CommunicationType;
import com.codenrock.raifcib21.flashlight.model.SegmentType;
import com.codenrock.raifcib21.flashlight.model.UserType;
import com.vaadin.componentfactory.multiselect.MultiComboBox;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.router.Route;
import org.vaadin.maxime.MarkdownArea;

@Route("admin")
public class AdminView extends VerticalLayout {
    public AdminView() {
        add(new H1("Добро пожаловать в панель администратора Flashlight"));
        add(getForm());
    }

    private FormLayout getForm() {
        var layout = new FormLayout();
        layout.add(communicationType());
        layout.add(channelTypes());
        layout.add(userTypes());
        layout.add(segmentTypes());
        layout.add(message());
        layout.add(userIds());
        layout.add(companyIds());
        return layout;
    }

    private ComboBox<CommunicationType> communicationType() {
        var component = new ComboBox<CommunicationType>("Тип коммуникации");
        component.setItems(CommunicationType.values());
        component.setItemLabelGenerator(CommunicationType::name);
        return component;
    }

    private MultiComboBox<ChannelType> channelTypes() {
        var component = new MultiComboBox<ChannelType>("Каналы связи");
        component.setItems(ChannelType.values());
        component.setItemLabelGenerator(ChannelType::name);
        return component;
    }

    private MultiComboBox<UserType> userTypes() {
        var component = new MultiComboBox<UserType>("Категории пользователей");
        component.setItems(UserType.values());
        component.setItemLabelGenerator(UserType::name);
        return component;
    }

    private MultiComboBox<SegmentType> segmentTypes() {
        var component = new MultiComboBox<SegmentType>("Сегменты");
        component.setItems(SegmentType.values());
        component.setItemLabelGenerator(SegmentType::name);
        return component;
    }

    private MarkdownArea message() {
        var component = new MarkdownArea("Сегменты");
        component.setMarkdownLink("https://www.mymarkdownguide.com/md-guide");
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
}
