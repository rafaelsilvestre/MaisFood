package br.com.omaisfood.model;

import br.com.omaisfood.model.enumerators.OrderStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity(name = "orders")
@Table(indexes = {@Index(name = "order_status",  columnList="status")})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @NotNull
    @Column(name = "status", length = 255, nullable = false)
    private OrderStatus status;

    @NotNull
    private Float totalPrice;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @ManyToOne
    @JsonBackReference("company")
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "order")
    @Fetch(FetchMode.SELECT)
    private List<OrderItem> orderItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
