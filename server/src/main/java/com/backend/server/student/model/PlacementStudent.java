package com.backend.server.student.model;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "placement_student")
public class PlacementStudent {
    public enum Acceptance {
        PENDING, ACCEPTED, REJECTED;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "cv_application")
    private String cvApplication;
    @Column(name = "about")
    private String about;
    @Enumerated(EnumType.ORDINAL)
    private Acceptance acceptance;
    @Column(name = "comments")
    private String comments;
    @Temporal(TemporalType.DATE)
    Date date;

    @ManyToOne()
    @JoinColumn(name = "placement_id", referencedColumnName = "id")
    private Placement placement;

    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Students students;

    public PlacementStudent(Long id, String cvApplication, String about, String comments,
            Date date, Placement placement, Students student) {
        this.id = id;
        this.cvApplication = cvApplication;
        this.about = about;
        this.comments = comments;
        this.date = date;
        this.placement = placement;
        this.students = student;
        this.acceptance = Acceptance.PENDING;
    }

    public PlacementStudent() {
    }

    public Long getId() {
        return id;
    }

    public String getCvApplication() {
        return cvApplication;
    }

    public String getAbout() {
        return about;
    }

    public String getComments() {
        return comments;
    }

    public Date getDate() {
        return date;
    }

    public Placement getPlacement() {
        return placement;
    }

    public Students getStudent() {
        return students;
    }

    public Acceptance getAcceptance() {
        return acceptance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCvApplication(String cvApplication) {
        this.cvApplication = cvApplication;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public void setStudent(Students student) {
        this.students = student;
    }

    public void setAcceptance() {
        this.acceptance = Acceptance.PENDING;
    }
}
