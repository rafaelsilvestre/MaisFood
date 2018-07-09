package br.com.omaisfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "cards")
public class Card {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "number", length = 255, nullable = false)
    private String number;

    @NotEmpty
    @Column(name = "owner_name", length = 255, nullable = false)
    private String ownerName;

    @NotEmpty
    @Column(name = "cvc", nullable = false)
    private int cvc;

    @NotEmpty
    @Column(name = "expiry_date", length = 255, nullable = false)
    private String expiryDate;

    @NotEmpty
    @Column(name = "brand", length = 255, nullable = false)
    private String brand;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
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
