package com.brodygaudel.gestionemployee.services;

import com.brodygaudel.gestionemployee.dtos.AbsenceDTO;
import com.brodygaudel.gestionemployee.exceptions.AbsenceNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;

import java.util.List;

public interface AbsenceService {
    AbsenceDTO save(AbsenceDTO absenceDTO) throws EmployeeNotFoundException;
    AbsenceDTO update(AbsenceDTO absenceDTO) throws AbsenceNotFoundException;
    AbsenceDTO findById(Long id) throws AbsenceNotFoundException;
    List<AbsenceDTO> findByEmployeeId(String employeeId);
    void deleteById(Long id);
    void deleteByEmployeeId(String employeeId);
}
