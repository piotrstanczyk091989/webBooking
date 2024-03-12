package com.booking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@TableGenerator(name = "EquipmentIdGen", table = "GENERATOR", pkColumnName = "ENTITY_NAME", valueColumnName = "ID_RANGE", pkColumnValue = "Equipment", initialValue=100)
@Entity
@Table(name = "Equipment")
@NamedQueries ({
    @NamedQuery(name="Equipment.findEquipmentFromLab", query="SELECT e FROM Equipment e WHERE e.lab.number=:number AND e.conditionEquipment=true"),
    @NamedQuery(name="Equipment.findEquipmentById", query="SELECT e FROM Equipment e WHERE e.id=:id "),
    //@NamedQuery(name="Equipment.findEquipmentWithEquipmentComponent", query="SELECT e FROM Equipment e WHERE e.equipmentComponents.id=:id ")    
        
})        
public class Equipment extends AbstractEntity implements Serializable{
    

    @Id
    @Column(name = "id", updatable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "EquipmentIdGen")
    private Long id;
    
    @NotNull
    @Size(min=1,max=2,message="{constraint.string.length.notinrange}")
    @Pattern(regexp="[0-9]{1,2}",message = "{constraint.string.incorrectStationNumber}")
    @Column(name = "stationNumber", length = 32, nullable = false)
    private String stationNumber;
        
    @NotNull
    @Size(min=1,max=32,message="{constraint.string.length.notinrange}")
    @Column(name = "stationType", length = 32, nullable = false)
    private String stationType;   
    
    @Column(name = "conditionEquipment", nullable = false)
    private boolean conditionEquipment = true;

    @ManyToOne
    @JoinColumn(name = "lab_id", nullable=false)
    private Lab lab;
    
    @ManyToMany(mappedBy = "equipments")    
    private List<EquipmentComponents> equipmentComponents = new ArrayList<EquipmentComponents>();
    
    @OneToMany(mappedBy = "equipmentReservation", cascade={CascadeType.PERSIST, CascadeType.REMOVE})   
    private List<Reservation> reservations = new ArrayList();
    
    @Override
    public Long getId() {
        return id;
    } 

    public String getStationNumber() {
        return stationNumber;
    }

    public void setStationNumber(String stationNumber) {
        this.stationNumber = stationNumber;
    }

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }
    
    public List<EquipmentComponents> getEquipmentComponents() {
        return equipmentComponents;
    }

    public void setEquipmentComponents(List<EquipmentComponents> equipmentComponents) {
        this.equipmentComponents = equipmentComponents;
    }

    public Lab getLab() {
        return lab;
    }

    public void setLab(Lab lab) {
        this.lab = lab;
    }
    
    public boolean isConditionEquipment() {
        return conditionEquipment;
    }

    public void setConditionEquipment(boolean conditionEquipment) {
        this.conditionEquipment = conditionEquipment;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    protected Object getBusinessKey() {
        return id;
    }
    
}
