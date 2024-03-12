
package com.booking.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "DataAdministrator")
@DiscriminatorValue("ADMIN")
@NamedQueries({
    @NamedQuery(name = "Administrator.findAll", query = "SELECT d FROM Administrator d"),
    @NamedQuery(name = "Administrator.findByAlarmNumber", query = "SELECT d FROM Administrator d WHERE d.alarmNumber = :alarmNumber")})
public class Administrator extends Account implements Serializable {

    public Administrator() {
    }
    
    @NotNull
    @Size(max=12,message="{constraint.string.length.toolong}")
    @Column(name = "AlarmNumber", unique=true, nullable=false, length=12)
    private String alarmNumber;

    @OneToMany(mappedBy = "whoMade")
    private List<BookingLab> booking = new ArrayList<BookingLab>();

    public List<BookingLab> getBooking() {
        return booking;
    }

    public String getAlarmNumber() {
        return alarmNumber;
    }

    public void setAlarmNumber(String number) {
        this.alarmNumber = number;
    }
}
