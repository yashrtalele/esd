package com.backend.server.student.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "grades")
public class Grades {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "grade_id")
    private Long gradeId;
    @Column(name = "letter_grade", unique = true)
    private String letterGrade;
    @Column(name = "grade_points", nullable = false)
    private float gradePoints;
    @Column(name = "comment", unique = false)
    private String comment;

    @OneToMany(mappedBy = "grades")
    private Set<StudentCourses> studentCourses = new HashSet<>();

    public Grades(Long gradeId, String letterGrade, float gradePoints, String comment) {
        this.gradeId = gradeId;
        this.letterGrade = letterGrade;
        this.gradePoints = gradePoints;
        this.comment = comment;
    }

    public Grades() {
    }

    public Long getGradeId() {
        return gradeId;
    }

    public String getLetterGrade() {
        return letterGrade;
    }

    public float getGradePoints() {
        return gradePoints;
    }

    public String getComment() {
        return comment;
    }
}
