package com.backend.server.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "domains")
public class Domain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "domain_id")
    private Long domainId;
    @Column(name = "program")
    private String program;
    @Column(name = "batch")
    private String batch;
    @Column(name = "capacity")
    private Integer capacity;
    @Column(name = "qualification")
    private String qualification;
    @JsonIgnore
    @OneToOne(mappedBy = "domain")
    private Students student;

    @JsonIgnore
    @OneToMany(mappedBy = "domain")
    private Set<PlacementFilter> placementFilter = new HashSet<>();

    public Domain(Long domainId, String program, String batch, Integer capacity, String qualification,
            Students student) {
        this.domainId = domainId;
        this.program = program;
        this.batch = batch;
        this.capacity = capacity;
        this.qualification = qualification;
        this.student = student;
    }

    public Domain() {
    }

    public Long getDomainId() {
        return domainId;
    }

    public String getProgram() {
        return program;
    }

    public String getBatch() {
        return batch;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public String getQualification() {
        return qualification;
    }

    public Students student() {
        return student;
    }
}
