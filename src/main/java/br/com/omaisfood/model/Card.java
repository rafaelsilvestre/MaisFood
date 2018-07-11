package br.com.omaisfood.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sun.istack.internal.NotNull;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "credit_cards")
public class Card {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "number", nullable = false)
    private Long number;

    @NotEmpty
    @Column(name = "owner_name", length = 255, nullable = false)
    private String ownerName;

    @NotEmpty
    @Column(name = "cvc", nullable = false)
    private String cvc;

    @NotEmpty
    @Column(name = "expiry_date", length = 255, nullable = false)
    private String expiryDate;

    @NotEmpty
    @Column(name = "brand", length = 255, nullable = false)
    private String brand;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
