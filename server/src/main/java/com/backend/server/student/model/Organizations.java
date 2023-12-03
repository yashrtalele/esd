package com.backend.server.student.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "organizations")
public class Organizations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "address")
    private String address;

    @OneToMany(mappedBy = "organizations")
    private Set<Placement> placements = new HashSet<>();

    public Organizations(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Organizations() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
