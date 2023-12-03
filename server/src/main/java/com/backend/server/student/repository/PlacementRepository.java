package com.backend.server.student.repository;

import com.backend.server.student.model.Placement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlacementRepository extends JpaRepository<Placement, Long> {

}
