package com.backend.server.student.model;

import jakarta.persistence.*;

@Entity
@Table(name = "placement_filter")
public class PlacementFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne()
    @JoinColumn(name = "placement_id", referencedColumnName = "id", unique = false)
    private Placement placement;

    @ManyToOne()
    @JoinColumn(name = "specialization", referencedColumnName = "specialization_id", unique = false)
    private Specialization specialization;

    @ManyToOne()
    @JoinColumn(name = "domain", referencedColumnName = "domain_id", unique = false)
    private Domain domain;

    public PlacementFilter(Long id, Placement placement, Specialization specialization, Domain domain) {
        this.id = id;
        this.placement = placement;
        this.specialization = specialization;
        this.domain = domain;
    }

    public PlacementFilter() {
    }

    public Long getId() {
        return id;
    }

    public Placement getPlacement() {
        return placement;
    }

    public Specialization getSpecialization() {
        return specialization;
    }

    public Domain getDomain() {
        return domain;
    }
}
