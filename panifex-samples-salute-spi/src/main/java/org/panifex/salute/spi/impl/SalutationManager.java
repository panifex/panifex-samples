package org.panifex.salute.spi.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.aries.blueprint.annotation.Bean;
import org.apache.aries.blueprint.annotation.Bind;
import org.apache.aries.blueprint.annotation.Inject;
import org.apache.aries.blueprint.annotation.ReferenceList;
import org.apache.aries.blueprint.annotation.ReferenceListener;
import org.apache.aries.blueprint.annotation.Unbind;
import org.panifex.salute.spi.SalutationService;

@Bean(id = "salutationManager", factoryMethod = "init")
@ReferenceListener
public class SalutationManager {
    
    private static SalutationManager manager;
    
    public static SalutationManager init() {
        if (manager == null) {
            manager = new SalutationManager();
        }
        return manager;
    }
    
    @Inject
    @ReferenceList(
        availability = "optional",
        serviceInterface = SalutationService.class,
        referenceListeners = @ReferenceListener(ref = "salutationManager"))
    private List<SalutationService> salutationServices = new ArrayList<>();
    
    @Bind
    public void bind(SalutationService salutationService) {
        if (salutationService != null) {
            salutationServices.add(salutationService);
        }
    }
    
    @Unbind
    public void unbind(SalutationService salutationService) {
        if (salutationService != null) {
            salutationServices.remove(salutationService);
        }
    }
    
    public static List<SalutationService> getSalutationServices() {
        return init().salutationServices;
    }
}
