package org.panifex.salute.spi.impl;

import java.util.List;

import org.panifex.salute.spi.SalutationService;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zul.Messagebox;

public class SalutationContentVM {
    private String name;
    private SalutationService selectedService;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public SalutationService getSelectedService() {
        return selectedService;
    }
    
    public void setSelectedService(SalutationService selectedService) {
        this.selectedService = selectedService;
    }
    
    public List<SalutationService> getSalutationServices() {
        return SalutationManager.getSalutationServices();
    }
    
    @Command
    public void salute() {
        Messagebox.show(selectedService.salute(name));
    }
}
