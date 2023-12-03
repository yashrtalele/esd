package com.backend.server.student.repository;

import com.backend.server.student.model.PlacementFilter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlacementFilterRepository extends JpaRepository<PlacementFilter, Long> {
    @Query(value = "select pf.placement_id, p.id, pf.specialization, pf.domain from placement p inner join placement_filter pf on p.id=pf.placement_id where pf.specialization=?1 and pf.domain=?2 and p.minimum_grade <= ?3", nativeQuery = true)
    // @Query(value = "select ps.student_id, pf.placement_id, p.id,
    // pf.specialization, pf.domain from placement_student ps inner join placement p
    // on ps.placement_id=p.id inner join placement_filter pf on
    // p.id=pf.placement_id where pf.specialization=?1 and pf.domain=?2 and
    // ps.student_id=?3 and p.minimum_grade <= ?4", nativeQuery = true)
    List<PlacementFilter> findBySIdAndDId(Long sId, Long dId, float totalGrades);
}
