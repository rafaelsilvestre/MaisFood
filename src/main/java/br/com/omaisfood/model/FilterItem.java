package br.com.omaisfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "filter_items")
public class FilterItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "filter_id")
    private Filter filter;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
