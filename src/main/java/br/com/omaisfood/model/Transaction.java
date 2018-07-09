package br.com.omaisfood.model;

import br.com.omaisfood.enumerators.TransactionStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "transactions")
public class Transaction {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    @Column(name = "date", length = 255, nullable = false)
    private String date;

    @NotEmpty
    @Column(name = "value", nullable = false)
    private Float value;

    @NotEmpty
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

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

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
        this.status = status;
    }
}
