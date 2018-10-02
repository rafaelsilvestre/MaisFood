package br.com.omaisfood.model;

import br.com.omaisfood.dto.CompanyForm;
import br.com.omaisfood.dto.UserEditForm;
import br.com.omaisfood.model.enumerators.Permission;
import br.com.omaisfood.utils.Utils;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    @JsonBackReference
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @JsonInclude()
    @Transient
    private String gravatar;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "permissions")
    private List<Integer> permissions = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address addressDefault;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    private List<Address> addresses;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user")
    @Fetch(FetchMode.SELECT)
    private List<Card> cards;

    @OneToOne(cascade = CascadeType.REMOVE, mappedBy = "user")
    private Company company;

    User() {
        this.setPermissions(Permission.ADMIN);
    }

    User(@NotNull String name, @NotNull String lastName, @NotNull String email) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
    }

    User(Long id, String name, String lastName, String email, String password, Address addressDefault, List<Address> addresses, List<Card> cards) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.addressDefault = addressDefault;
        this.addresses = addresses;
        this.cards = cards;
        this.setPermissions(Permission.ADMIN);
    }

    User(Long id, String name, String lastName, String email, String password, Address addressDefault) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.addressDefault = addressDefault;
        this.setPermissions(Permission.ADMIN);
    }

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

    public String getGravatar() {
        if (getEmail() != null){
            gravatar = Utils.getAvatarByEmail(getEmail());
        }
        return gravatar;
    }

    public void setGravatar(String gravatar) {
        this.gravatar = gravatar;
    }

    public List<Permission> getPermissions() {
        return permissions.stream().map(x -> Permission.toEnum(x)).collect(Collectors.toList());
    }

    public void setPermissions(Permission permission) {
        this.permissions.add(permission.getIdentifier());
    }

    public Address getAddressDefault() {
        return addressDefault;
    }

    public void setAddressDefault(Address addressDefault) {
        this.addressDefault = addressDefault;
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

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public static User fromCompanyForm(CompanyForm companyForm) {
        return new User(null, companyForm.getOwnerName(), companyForm.getOwnerLastname(), companyForm.getEmail(), companyForm.getPassword(), null);
    }

    public static User fromUserEditForm(Long id, UserEditForm userEditForm) {
        return new User(id,  userEditForm.getName(), userEditForm.getLastName(), userEditForm.getEmail(), null, null);
    }
}
