package com.backend.server.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "specialization")
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "specialization_id")
    private Long specializationId;
    @Column(name = "code")
    private Integer code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "year")
    private Integer year;
    @Column(name = "credits_required")
    private Integer creditsRequired;
    @JsonIgnore
    @OneToOne(mappedBy = "specialization")
    private Students student;

    @JsonIgnore
    @OneToMany(mappedBy = "specialization")
    private Set<PlacementFilter> placementFilter = new HashSet<>();

    public Specialization(Long specializationId, Integer code, String name, String description, Integer year,
            Integer creditsRequired) {
        this.specializationId = specializationId;
        this.code = code;
        this.name = name;
        this.description = description;
        this.year = year;
        this.creditsRequired = creditsRequired;
    }

    public Specialization() {
    }

    public Long getSpecializationId() {
        return specializationId;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getYear() {
        return year;
    }

    public Integer getCreditsRequired() {
        return creditsRequired;
    }
}
