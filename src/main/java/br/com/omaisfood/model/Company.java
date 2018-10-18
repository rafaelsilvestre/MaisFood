package br.com.omaisfood.model;

import br.com.omaisfood.dto.CompanyForm;
import br.com.omaisfood.dto.CompanyFormEdit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "companies")
@Table(indexes = {@Index(name = "company_name",  columnList="name")})
public class Company extends Generic {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "O nome deve ser informado!")
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    @NotEmpty(message = "A descrição deve ser informada!")
    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "image", length = 255, nullable = true)
    private String image;

    @NotNull(message = "O valor mínimo deve ser informado!")
    @Column(name = "minimum_value", nullable = false)
    private BigDecimal minimumValue;

    @Column(name = "visibility_status")
    private Boolean isVisible = true;

    @Column(name = "is_inactive")
    private Boolean isInactive = false;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @JsonBackReference("user")
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "company")
    @Fetch(FetchMode.SUBSELECT)
    private List<FilterItem> filters;

    @OneToMany(mappedBy = "company")
    @Fetch(FetchMode.SUBSELECT)
    private List<Product> products;

    @OneToMany(mappedBy = "company")
    @Fetch(FetchMode.SUBSELECT)
    private List<Category> categories;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    public Company(){

    }

    public Company(Long id, String name, String description, String image, BigDecimal minimumValue, Boolean isActivated, Address address, User user){
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.minimumValue = minimumValue;
        this.isVisible = isActivated;
        this.address = address;
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public BigDecimal getMinimumValue() {
        return minimumValue;
    }

    public void setMinimumValue(BigDecimal minimumValue) {
        this.minimumValue = minimumValue;
    }

    public Boolean isVisible() {
        return isVisible;
    }

    public void setVisibility(Boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Boolean isInactive() {
        return isInactive;
    }

    public void setInactive(Boolean isInactive) {
        this.isInactive = isInactive;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static Company fromCompanyForm(CompanyForm companyForm) {
        return new Company(null, companyForm.getCompanyName(), companyForm.getDescription(), null, companyForm.getMinimumValue(), null, null, null);
    }

    public static Company fromCompanyFormEdit(CompanyFormEdit companyFormEdit) {
        return new Company(null, companyFormEdit.getCompanyName(), companyFormEdit.getDescription(), null, companyFormEdit.getMinimumValue(), null, null, null);
    }
}
