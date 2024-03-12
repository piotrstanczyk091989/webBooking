package com.booking.web.equipmentcomponents;

import com.booking.model.EquipmentComponents;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

@Named("listEquipmentComponentsPageBean")
@RequestScoped
public class ListEquipmentComponentsPageBean {

    public ListEquipmentComponentsPageBean() {
    }
    
    @PostConstruct
    private void initModel() {
        equipmentComponets = equipmentComponentsSession.matchComponent(searchTypeComponent,searchNameComponent);
        
        equipmentComponetsDataModel = new ListDataModel<>(equipmentComponets);
    }
    
    @Inject 
    private EquipmentComponentsSession equipmentComponentsSession;

    private List<EquipmentComponents> equipmentComponets;
    private DataModel<EquipmentComponents> equipmentComponetsDataModel;

    public DataModel<EquipmentComponents> getEquipmentComponetsDataModel() {
        return equipmentComponetsDataModel;
    }

    private String searchTypeComponent = "";
    private String searchNameComponent = "";

    public String getSearchTypeComponent() {
        return searchTypeComponent;
    }

    public void setSearchTypeComponent(String searchTypeComponent) {
        this.searchTypeComponent = searchTypeComponent;
    }

    public String getSearchNameComponent() {
        return searchNameComponent;
    }

    public void setSearchNameComponent(String searchNameComponent) {
        this.searchNameComponent = searchNameComponent;
    }
    
    public void refresh() {
        initModel();
    }

    public void clean() {
        searchTypeComponent = "";
        searchNameComponent = "";
    }
    
    public String editEquipmentComponents() {
        return equipmentComponentsSession.downloadEquipmentComponentsToEdit(equipmentComponetsDataModel.getRowData());
    }
    
    public String deleteEquipmentComponents() {
        return equipmentComponentsSession.downloadEquipmentComponentsToDelete(equipmentComponetsDataModel.getRowData());
    }

    public String listEquipmentWithComponet() {
        return equipmentComponentsSession.listEquipmentWithComponent(equipmentComponetsDataModel.getRowData());
    }
    
}
