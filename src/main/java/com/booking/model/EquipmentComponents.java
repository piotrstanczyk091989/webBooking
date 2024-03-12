package com.booking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@TableGenerator(name = "EquipmentComponentsIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "EquipmentComponents", initialValue=100)
@Entity
@Table(name = "EQUIPMENTCOMPONENTS")
@NamedQueries ({
    @NamedQuery(name="EquipmentComponents.findEquipmentComponentById", query="SELECT e FROM EquipmentComponents e WHERE e.id=:id")  
})
public class EquipmentComponents extends AbstractEntity implements Serializable  {
    
    @Id
    @Column(name = "id", updatable = false) 
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EquipmentComponentsIdGen")
    private Long id;    
    
    @NotNull
    @Size(min = 3, max = 32, message = "{constraint.string.length.notinrange}")
    @Column(name = "nameComponent", length = 32, nullable = false, unique = true)
    private String nameComponent;
    
    @NotNull
    @Size(min = 3, max = 32, message = "{constraint.string.length.notinrange}")
    @Column(name = "typeComponent", length = 32, nullable = false)
    private String typeComponent;
    
    @ManyToMany
    @JoinTable(name="Equipments_EquipmentComponents")
    private List<Equipment> equipments = new ArrayList<Equipment>();

    @Override
    public Long getId() {
        return id;
    }

    public String getNameComponent() {
        return nameComponent;
    }

    public void setNameComponent(String nameComponent) {
        this.nameComponent = nameComponent;
    }
    
    public String getTypeComponent() {
        return typeComponent;
    }

    public void setTypeComponent(String typeComponent) {
        this.typeComponent = typeComponent;
    }
    
    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }
       
    @Override
    protected Object getBusinessKey() {
        return nameComponent;
    }
    
}
