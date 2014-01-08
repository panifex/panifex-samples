package org.panifex.salute.spi.impl;

import org.apache.aries.blueprint.annotation.Bean;
import org.apache.aries.blueprint.annotation.Service;
import org.panifex.module.api.content.AbstractContent;
import org.panifex.module.api.content.Content;
import org.zkoss.bind.DefaultBinder;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

@Bean(id = "salutationContent")
@Service(interfaces = Content.class)
public class SalutationContent extends AbstractContent {

    public SalutationContent() {
        super("Salutation", "salutation");
    }
    @Override
    public Component createBody() {
        Div body = new Div();
        
        DefaultBinder binder = new DefaultBinder();
        binder.init(body, new SalutationContentVM(), null);
        
        body.setAttribute("vm", binder.getViewModel());
        
        createComponents(binder, body);
        
        binder.loadComponent(body, true);
        
        return body;
    }
    
    private void createComponents(DefaultBinder binder, Component body) {
        Vlayout vlayout = new Vlayout();
        body.appendChild(vlayout);
        
        createSalutationChooserArea(binder, vlayout);
        
        createInputNameArea(binder, vlayout);
        
        createButtonArea(binder, vlayout);
    }
    private void createButtonArea(DefaultBinder binder, Vlayout vlayout) {
        // button
        Button saluteButton = new Button("Salute");
        vlayout.appendChild(saluteButton);
        
        // bind command
        binder.addCommandBinding(saluteButton, Events.ON_CLICK, "'salute'", null);
        
        // bind disabled
        binder.addPropertyLoadBindings(saluteButton, "disabled", "empty vm.selectedService", null, null, null, null, null);
    }
    
    private void createInputNameArea(DefaultBinder binder, Vlayout vlayout) {
        Hlayout hlayout = new Hlayout();
        vlayout.appendChild(hlayout);
        
        // label
        Label nameLabel = new Label("Your name :");
        hlayout.appendChild(nameLabel);
        
        // text box
        Textbox nameTextbox = new Textbox();
        hlayout.appendChild(nameTextbox);
        
        // bind name
        binder.addPropertyLoadBindings(nameTextbox, "value", "vm.name", null, null, null, null, null);
        binder.addPropertySaveBindings(nameTextbox, "value", "vm.name", null, null, null, null, null, null, null);
    }
    
    private void createSalutationChooserArea(DefaultBinder binder, Vlayout vlayout) {
        Hlayout hlayout = new Hlayout();
        vlayout.appendChild(hlayout);
        
        // label
        Label chooseSalutionLabel = new Label("Salutation :");
        hlayout.appendChild(chooseSalutionLabel);
        
        // combobox
        Combobox salutationCombo = new Combobox();
        hlayout.appendChild(salutationCombo);
        
        // bind model
        binder.addPropertyLoadBindings(salutationCombo, "model", "vm.salutationServices", null, null, null, null, null);
        
        // bind selected item
        binder.addPropertyLoadBindings(salutationCombo, "selectedItem", "vm.selectedService", null, null, null, null, null);
        binder.addPropertySaveBindings(salutationCombo, "selectedItem", "vm.selectedService", null, null, null, null, null, null, null);
    }

}
