package com.backend.server.student.aux;

import com.backend.server.student.model.PlacementFilter;
import com.backend.server.student.model.PlacementStudent;
import com.backend.server.student.model.Students;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/aux")
public class AuxController {
    @Autowired
    private AuxService auxService;

    @GetMapping(path = "/get/{studentId}")
    public ResponseEntity<?> getStudentById(@PathVariable("studentId") Long id) {
        List<Students> stud = auxService.getStudentById(id);
        return new ResponseEntity<>(stud, HttpStatus.OK);
    }

    @GetMapping(path = "/get/{studentId}/offers")
    public ResponseEntity<?> getOffers(@PathVariable("studentId") Long id) {
        List<PlacementFilter> offers = auxService.getOffers(id);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }

    @PostMapping(path = "/add/application")
    public ResponseEntity<?> addApplication(@RequestBody AuxApplicationDTO applicationDTO) {
        if (auxService.addApplication(applicationDTO) != null)
            return new ResponseEntity<>(HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
    }

    @GetMapping(path = "/get/{studentId}/grades")
    public Double getGrades(@PathVariable("studentId") Long id) {
        return auxService.getStudentCurrentGrades(id);
    }

    @GetMapping(path = "/get/{studentId}/placements")
    public ResponseEntity<?> getPlacementStudentsById(@PathVariable("studentId") Long id) {
        List<PlacementStudent> placements = auxService.getPlacementStudentsById(id);
        return new ResponseEntity<>(placements, HttpStatus.OK);
    }

}
