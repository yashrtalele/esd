package com.backend.server.student.service;

import com.backend.server.student.aux.AuxAuthenticationObjectDTO;
import com.backend.server.student.model.Students;
import com.backend.server.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    @Autowired
    private StudentRepository studentRepository;

    private boolean authenticateStudent(String email, String password) {
        Optional<Students> studentByEmail = studentRepository.findStudentByEmail(email);
        if (studentByEmail.isPresent()) {
            Students student = studentByEmail.get();
            return password.equals(student.getPassword());

        } else {
            return false;
        }
    }

    public boolean authenticate(AuxAuthenticationObjectDTO authenticationObjectDTO) {
        return authenticateStudent(authenticationObjectDTO.getEmail(),
                authenticationObjectDTO.getPassword());
    }
}
