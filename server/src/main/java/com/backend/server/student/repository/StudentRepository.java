package com.backend.server.student.repository;

import com.backend.server.student.model.Students;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Students, Long> {

    Optional<Students> findStudentByEmail(String email);

    List<Students> findByStudentId(Long studentId);
}
