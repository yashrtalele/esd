package com.backend.server.student.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "placement")
public class Placement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "profile")
    private String profile;
    @Column(name = "description")
    private String description;
    @Column(name = "intake")
    private Integer intake;
    @Column(name = "minimum_grade")
    private float minimumGrade;
    @ManyToOne()
    @JoinColumn(name = "organization", referencedColumnName = "id")
    private Organizations organizations;

    @JsonIgnore
    @OneToMany(mappedBy = "placement")
    private Set<Students> students = new HashSet<>();

    @OneToMany(mappedBy = "placement")
    private Set<PlacementStudent> placementStudents = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "placement")
    private PlacementFilter placementFilter;

    public Placement(Long id, Organizations organizations, String profile, String description, Integer intake,
            float minimumGrade) {
        this.id = id;
        this.organizations = organizations;
        this.profile = profile;
        this.description = description;
        this.intake = intake;
        this.minimumGrade = minimumGrade;
    }

    public Placement() {
    }

    public Long getId() {
        return id;
    }

    public Organizations getOrganization() {
        return organizations;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getIntake() {
        return intake;
    }

    public void setIntake(Integer intake) {
        this.intake = intake;
    }

    public float getMinimumGrade() {
        return minimumGrade;
    }

    public void setMinimumGrade(Float minimumGrade) {
        this.minimumGrade = minimumGrade;
    }
}
