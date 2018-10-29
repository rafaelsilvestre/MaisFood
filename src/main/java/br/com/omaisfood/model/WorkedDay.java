package br.com.omaisfood.model;

import br.com.omaisfood.model.enumerators.TypeDay;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "worked_day")
public class WorkedDay {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private TypeDay day;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;

    @NotEmpty
    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeDay getDay() {
        return day;
    }

    public void setDay(TypeDay day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
