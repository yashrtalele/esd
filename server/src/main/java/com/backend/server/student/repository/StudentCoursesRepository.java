package com.backend.server.student.repository;

import com.backend.server.student.model.StudentCourses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StudentCoursesRepository extends JpaRepository<StudentCourses, Long> {
    @Query(value = "select sum(g.grade_points) as count from student_courses sc inner join grades g on sc.grade_id=g.grade_id where sc.student_id=?1 group by student_id", nativeQuery = true)
    double getStudentGrades(Long id);

    @Query(value = "select count(*) as count from student_courses sc where sc.student_id=?1 group by student_id", nativeQuery = true)
    Integer getStudentSubjectsCount(Long id);
}
