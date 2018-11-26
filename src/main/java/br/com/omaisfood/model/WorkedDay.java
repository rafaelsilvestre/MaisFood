package br.com.omaisfood.model;

import br.com.omaisfood.model.enumerators.TypeDay;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "worked_day")
public class WorkedDay {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private TypeDay day;

    @NotEmpty
    private String startTime;

    @NotEmpty
    private String endTime;

    private boolean enabled;

    @NotNull
    @ManyToOne
    @JsonBackReference("workedDayCompany")
    @JoinColumn(name = "company_id")
    private Company company;

    public WorkedDay() { }

    public WorkedDay(TypeDay day, String startTime, String endTime, boolean enabled, Company company) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.enabled = enabled;
        this.company = company;
    }

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
