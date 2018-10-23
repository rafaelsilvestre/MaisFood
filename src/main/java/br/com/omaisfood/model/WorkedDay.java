package br.com.omaisfood.model;

import br.com.omaisfood.model.enumerators.TypeDay;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "worked_days")
public class WorkedDay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "day", nullable = false)
    private TypeDay day;

    @NotEmpty
    @Size(min = 5, max = 5)
    @Column(name = "start_time", nullable = false)
    private String startTime;

    @NotEmpty
    @Size(min = 5, max = 5)
    @Column(name = "end_time", nullable = false)
    private String endTime;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference("company")
    private Company company;

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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
