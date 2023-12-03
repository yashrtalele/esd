package com.backend.server.student.aux;

import com.backend.server.student.model.PlacementStudent.Acceptance;

import java.sql.Date;

public class AuxApplicationDTO {
    private Long id;
    private String cvApplication;
    private String about;
    private String comments;
    private Acceptance acceptance;
    private Date date;
    private Long placement;
    private Long student;

    public AuxApplicationDTO() {
    }

    public AuxApplicationDTO(Long id, String cvApplication, String about, String comments,
            Date date, Long placement, Long student) {
        this.id = id;
        this.cvApplication = cvApplication;
        this.about = about;
        this.comments = comments;
        this.date = date;
        this.placement = placement;
        this.student = student;
        this.acceptance = Acceptance.PENDING;
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

    public Long getPlacement() {
        // System.out.println("🚀" + placement);
        return placement;
    }

    public Long getStudent() {
        return student;
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

    public void setPlacement(Long placement) {
        this.placement = placement;
    }

    public void setStudent(Long student) {
        this.student = student;
    }

    public void setAcceptance() {
        this.acceptance = Acceptance.PENDING;
    }
}
