package com.backend.server.student.model;

import jakarta.persistence.*;

@Entity
@Table(name = "student_courses")
public class StudentCourses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "course_id")
    private Long courseId;
    @ManyToOne()
    @JoinColumn(name = "student_id", referencedColumnName = "student_id")
    private Students students;

    @ManyToOne()
    @JoinColumn(name = "grade_id", referencedColumnName = "grade_id")
    private Grades grades;

    public StudentCourses(Long id, Long courseId, Students students, Grades grades) {
        this.id = id;
        this.courseId = courseId;
        this.students = students;
        this.grades = grades;
    }

    public StudentCourses() {
    }

    public Long getId() {
        return id;
    }

    public Long getCourseId() {
        return courseId;
    }

    public Students getStudents() {
        return students;
    }

    public Grades getGrades() {
        return grades;
    }

}
