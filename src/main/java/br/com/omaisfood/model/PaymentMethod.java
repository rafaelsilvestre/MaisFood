package br.com.omaisfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "payment_methods")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "is_presential", nullable = false)
    private Boolean isPresential;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getPresential() {
        return isPresential;
    }

    public void setPresential(Boolean presential) {
        isPresential = presential;
    }
}
