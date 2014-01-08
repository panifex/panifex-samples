package org.panifex.salute.spi.impl;

import org.apache.aries.blueprint.annotation.Bean;
import org.apache.aries.blueprint.annotation.Bind;
import org.apache.aries.blueprint.annotation.Inject;
import org.apache.aries.blueprint.annotation.Reference;
import org.apache.aries.blueprint.annotation.ReferenceListener;
import org.apache.aries.blueprint.annotation.Unbind;
import org.panifex.module.api.menu.AppMenuService;
import org.panifex.module.api.menu.OpenContentMenuAction;

@Bean(id = "salutationMenuBinder")
@ReferenceListener
public class SalutationMenuBinder {

    @Inject
    @Reference(
        availability = "optional",
        serviceInterface = AppMenuService.class,
        referenceListeners = @ReferenceListener(ref = "salutationMenuBinder"))
    private AppMenuService menuService;
    
    private OpenContentMenuAction action;
    
    public SalutationMenuBinder() {
        action = new OpenContentMenuAction("salutation", "salutation");
        action.setLabel("Salutation");
    }

    @Bind
    public void bind(AppMenuService menuService) {
        this.menuService = menuService;
        
        menuService.bindMenuItem(action);
    }
    
    @Unbind
    public void unbind(AppMenuService menuService) {
        if (menuService != null) {
            menuService.unbindMenuItem(action);
        }
        this.menuService = null;
    }
}
