package br.com.omaisfood.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "district_items")
public class DistrictItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "district_id")
    private District district;

    @NotNull
    @ManyToOne
    @JsonBackReference("company")
    @JoinColumn(name = "company_id")
    private Company company;

    public DistrictItem() { }

    public DistrictItem(District district, Company company) {
        this.district = district;
        this.company = company;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
