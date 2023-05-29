package com.brodygaudel.gestionemployee.services.implementation;

import com.brodygaudel.gestionemployee.dtos.AbsenceDTO;
import com.brodygaudel.gestionemployee.entities.Absence;
import com.brodygaudel.gestionemployee.entities.Employee;
import com.brodygaudel.gestionemployee.exceptions.AbsenceNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.repositories.AbsenceRepository;
import com.brodygaudel.gestionemployee.repositories.EmployeeRepository;
import com.brodygaudel.gestionemployee.services.AbsenceService;
import com.brodygaudel.gestionemployee.util.mapping.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AbsenceServiceImpl implements AbsenceService {

    private final AbsenceRepository absenceRepository;
    private final EmployeeRepository employeeRepository;
    private final Mappers mappers;

    public AbsenceServiceImpl(AbsenceRepository absenceRepository, EmployeeRepository employeeRepository, Mappers mappers) {
        this.absenceRepository = absenceRepository;
        this.employeeRepository = employeeRepository;
        this.mappers = mappers;
    }

    @Override
    public AbsenceDTO save(AbsenceDTO absenceDTO) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(absenceDTO.employeeId())
                .orElseThrow( () -> new EmployeeNotFoundException("employee not found"));
        Absence absence = mappers.fromAbsenceDTO(absenceDTO);
        absence.setEmployee(employee);
        Absence absenceSaved = absenceRepository.save(absence);
        log.info("absence saved");
        return mappers.fromAbsence(absenceSaved);
    }

    @Override
    public AbsenceDTO update(AbsenceDTO absenceDTO) throws AbsenceNotFoundException {
        Absence absence = absenceRepository.findById(absenceDTO.id())
                .orElseThrow( () -> new AbsenceNotFoundException("absence not found"));
        absence.setStart(absenceDTO.start());
        absence.setEnd(absenceDTO.end());
        absence.setObservation(absenceDTO.observation());
        Absence absenceUpdated = absenceRepository.save(absence);
        log.info("absence updated");
        return mappers.fromAbsence(absenceUpdated);
    }

    @Override
    public AbsenceDTO findById(Long id) throws AbsenceNotFoundException {
        Absence absence = absenceRepository.findById(id)
                .orElseThrow( () -> new AbsenceNotFoundException("absence not found"));
        log.info("absence found");
        return mappers.fromAbsence(absence);
    }

    @Override
    public List<AbsenceDTO> findByEmployeeId(String employeeId) {
        List<Absence> absences = absenceRepository.findByEmployeeId(employeeId);
        log.info("absence(s) found with employee id :"+employeeId);
        return mappers.fromListOfAbsences(absences);
    }

    @Override
    public void deleteById(Long id) {
        absenceRepository.deleteById(id);
        log.info("absence deleted");
    }

    @Override
    public void deleteByEmployeeId(String employeeId) {
        List<Absence> absences = absenceRepository.findByEmployeeId(employeeId);
        absenceRepository.deleteAll(absences);
        log.info("absence deleted with employeeId :"+employeeId);
    }
}
