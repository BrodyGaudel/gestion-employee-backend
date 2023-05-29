package com.brodygaudel.gestionemployee.services;

import com.brodygaudel.gestionemployee.dtos.FormationDTO;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.FormationNotFoundException;

import java.util.List;

public interface FormationService {
    FormationDTO save(FormationDTO formationDTO) throws EmployeeNotFoundException;
    FormationDTO update(FormationDTO formationDTO) throws FormationNotFoundException;
    FormationDTO findById(Long id) throws FormationNotFoundException;
    List<FormationDTO> findByEmployeeId(String employeeId);
    void deleteById(Long id);
    void deleteAllByEmployeeId(String employeeId);
}
