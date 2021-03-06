package br.com.omaisfood.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "adresses")
public class Address {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Identificador do endereço não pode estar vazio")
    @Column(name = "identifier", length = 255, nullable = false)
    private String identifier;

    @NotEmpty(message = "O estado não pode estar vazio")
    @Column(name = "state", length = 255, nullable = false)
    private String state;

    @NotEmpty(message = "A cidade não pode estar vazio")
    @Column(name = "city", length = 255, nullable = false)
    private String city;

    @NotEmpty(message = "O endereço não pode estar vazio")
    @Column(name = "street", length = 255, nullable = false)
    private String street;

    @NotEmpty(message = "O bairro não pode estar vazio")
    @Column(name = "district", length = 255, nullable = false)
    private String district;

    @Column(name = "number", length = 255, nullable = false)
    private int number;

    @NotEmpty
    @Column(name = "complement", length = 255, nullable = false)
    private String complement;

    @NotNull(message = "Erro ao identificar usuário")
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

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
