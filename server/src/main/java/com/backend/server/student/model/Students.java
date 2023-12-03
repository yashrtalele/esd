package com.backend.server.student.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
public class Students {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email", unique = true)
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "photographPath")
    private String photographPath;
    @Column(name = "cgpa")
    private Double cgpa;
    @Column(name = "total_credits")
    private Integer totalCredits;
    @Column(name = "graduation_year")
    private Integer graduationYear;

    @OneToOne()
    @JoinColumn(name = "domain", referencedColumnName = "domain_id")
    private Domain domain;

    @OneToOne()
    @JoinColumn(name = "specialization", referencedColumnName = "specialization_id")
    private Specialization specialization;

    @ManyToOne()
    @JoinColumn(name = "placement_id", referencedColumnName = "id")
    private Placement placement;

    @OneToMany(mappedBy = "students")
    private Set<StudentCourses> studentCourses = new HashSet<>();

    @OneToMany(mappedBy = "students")
    private Set<PlacementStudent> placementStudents = new HashSet<>();

    public Students(Long studentId, String firstName, String lastName, String email, String password,
            String photographPath, Double cgpa, Integer totalCredits, Integer graduationYear, Domain domain,
            Placement placement) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.photographPath = photographPath;
        this.cgpa = cgpa;
        this.totalCredits = totalCredits;
        this.graduationYear = graduationYear;
        this.domain = domain;
        this.placement = placement;
    }

    public Students() {
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhotographPath() {
        return photographPath;
    }

    public void setPhotographPath(String photographPath) {
        this.photographPath = photographPath;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }

    public Integer getTotalCredits() {
        return totalCredits;
    }

    public void setTotalCredits(Integer totalCredits) {
        this.totalCredits = totalCredits;
    }

    public Integer getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(Integer graduationYear) {
        this.graduationYear = graduationYear;
    }

    public Domain getDomain() {
        return domain;
    }

    public void setDomain(Domain domain) {
        this.domain = domain;
    }

    public Placement getPlacement() {
        return placement;
    }

    public void setPlacement(Placement placement) {
        this.placement = placement;
    }

    public Specialization getSpecialization() {
        return specialization;
    }
}
