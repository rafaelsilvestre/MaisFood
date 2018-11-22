package br.com.omaisfood.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "districts")
public class District {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "O nome deve ser informado!")
    @Column(name = "name", length = 255, nullable = false)
    private String name;

    public District() { }

    public District(Long id, String name) {
        this.id = id;
        this.name = name;
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
}
