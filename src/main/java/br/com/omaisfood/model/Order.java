package br.com.omaisfood.model;

import br.com.omaisfood.enumerators.OrderStatus;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @NotEmpty
    @Column(name = "status", length = 255, nullable = false)
    private OrderStatus status;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @NotEmpty
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @NotEmpty
    @OneToMany(mappedBy = "order")
    @Fetch(FetchMode.SUBSELECT)
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
}
