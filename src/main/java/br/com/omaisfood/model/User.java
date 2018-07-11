package br.com.omaisfood.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity(name = "users")
@Table(indexes = {@Index(name = "user_email",  columnList="email")})
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotEmpty
    @Column(name = "last_name", length = 255, nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "email", length = 255, nullable = false, unique = true)
    private String email;

    @NotEmpty
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    private List<Card> cards;

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Address address) {
        this.addresses.add(address);
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(Card card) {
        this.cards.add(card);
    }
}
