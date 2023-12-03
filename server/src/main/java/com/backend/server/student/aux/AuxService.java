package com.backend.server.student.aux;

import com.backend.server.student.model.*;
import com.backend.server.student.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuxService {
    @Autowired
    private PlacementRepository placementRepository;

    @Autowired
    private StudentCoursesRepository studentCoursesRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private PlacementFilterRepository placementFilterRepository;

    @Autowired
    private PlacementStudentRepository placementStudentRepository;

    public List<Students> getStudentById(Long id) {
        List<Students> student = studentRepository.findByStudentId(id);
        return student;
    }

    public double getStudentGrades(Long id) {
        return studentCoursesRepository.getStudentGrades(id);
    }

    public Integer getStudentSubjectsCount(Long id) {
        return studentCoursesRepository.getStudentSubjectsCount(id);
    }

    public List<PlacementStudent> getPlacementStudentsById(Long id) {
        List<PlacementStudent> placements = placementStudentRepository.getPlacementStudentsById(id);
        return placements;
    }

    public Double getStudentCurrentGrades(Long id) {
        Double subSumGrades = getStudentGrades(id);
        Integer subCount = getStudentSubjectsCount(id);
        Double totalGrades = subSumGrades / subCount;
        return totalGrades;
    }

    public List<PlacementFilter> getOffers(Long id) {
        List<Students> student = getStudentById(id);
        double subSumGrades = getStudentGrades(id);
        Integer subCount = getStudentSubjectsCount(id);
        float totalGrades = (float) subSumGrades / subCount;
        Specialization specialization = student.get(0).getSpecialization();
        Long sId = specialization.getSpecializationId();
        Domain domain = student.get(0).getDomain();
        Long dId = domain.getDomainId();

        List<PlacementFilter> eligibleOffers = placementFilterRepository.findBySIdAndDId(sId, dId, totalGrades);
        return eligibleOffers;
    }

    public PlacementStudent addApplication(AuxApplicationDTO applicationDTO) {
        PlacementStudent ps = new PlacementStudent();
        ps.setId(applicationDTO.getId());
        ps.setCvApplication(applicationDTO.getCvApplication());
        ps.setAbout(applicationDTO.getAbout());
        ps.setComments(applicationDTO.getComments());
        ps.setDate(applicationDTO.getDate());
        Optional<Placement> placement = placementRepository.findById(applicationDTO.getPlacement());
        if (placement.isPresent()) {
            ps.setPlacement(placement.get());
        }
        Optional<Students> student = studentRepository.findById(applicationDTO.getStudent());
        if (student.isPresent()) {
            ps.setStudent(student.get());
        }
        ps.setAcceptance();
        return placementStudentRepository.save(ps);
    }

}
