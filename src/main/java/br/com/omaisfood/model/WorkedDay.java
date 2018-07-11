package br.com.omaisfood.model;

import javax.persistence.*;

@Entity(name = "worked_days")
public class WorkedDay {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
