package com.backend.server.student.repository;

import com.backend.server.student.model.PlacementStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlacementStudentRepository extends JpaRepository<PlacementStudent, Long> {
    @Query(value = "select * from placement_student where student_id=?1", nativeQuery = true)
    List<PlacementStudent> getPlacementStudentsById(Long id);
}
