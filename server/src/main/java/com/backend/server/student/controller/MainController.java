package com.backend.server.student.controller;

import com.backend.server.student.aux.AuxAuthenticationObjectDTO;
import com.backend.server.student.model.Students;
import com.backend.server.student.repository.StudentRepository;
import com.backend.server.student.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/home")
public class MainController {
    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private StudentRepository studentRepository;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuxAuthenticationObjectDTO credential) {
        if (authenticationService.authenticate(credential)) {
            Map<String, Object> map = new LinkedHashMap<String, Object>();
            Optional<Students> studentList = studentRepository.findStudentByEmail(credential.getEmail());

            if (!studentList.isEmpty()) {
                map.put("status", 1);
                map.put("data", studentList);
            } else {
                map.clear();
                map.put("status", 0);
                map.put("message", "Data is not found");
            }
            return new ResponseEntity<>(map, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
