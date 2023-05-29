package com.brodygaudel.gestionemployee.services;

import com.brodygaudel.gestionemployee.dtos.ExperienceDTO;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.ExperienceNotFoundException;

import java.util.List;

public interface ExperienceService {
    ExperienceDTO save(ExperienceDTO experienceDTO) throws EmployeeNotFoundException;
    ExperienceDTO update(ExperienceDTO experienceDTO) throws ExperienceNotFoundException;
    ExperienceDTO findById(Long id) throws ExperienceNotFoundException;
    List<ExperienceDTO> findByEmployeeId(String employeeId);
    void deleteById(Long id);
    void deleteAllByEmployeeId(String employeeId);
}
