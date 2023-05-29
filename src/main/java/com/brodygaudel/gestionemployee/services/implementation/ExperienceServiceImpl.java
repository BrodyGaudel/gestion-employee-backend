package com.brodygaudel.gestionemployee.services.implementation;

import com.brodygaudel.gestionemployee.dtos.ExperienceDTO;
import com.brodygaudel.gestionemployee.entities.Employee;
import com.brodygaudel.gestionemployee.entities.Experience;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.ExperienceNotFoundException;
import com.brodygaudel.gestionemployee.repositories.EmployeeRepository;
import com.brodygaudel.gestionemployee.repositories.ExperienceRepository;
import com.brodygaudel.gestionemployee.services.ExperienceService;
import com.brodygaudel.gestionemployee.util.mapping.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;
    private final Mappers mappers;
    private final EmployeeRepository employeeRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository, Mappers mappers, EmployeeRepository employeeRepository) {
        this.experienceRepository = experienceRepository;
        this.mappers = mappers;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ExperienceDTO save(ExperienceDTO experienceDTO) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(experienceDTO.employeeId())
                .orElseThrow( () -> new EmployeeNotFoundException("employee not found"));
        Experience experience = mappers.fromExperienceDTO(experienceDTO);
        experience.setEmployee(employee);
        Experience experienceSaved = experienceRepository.save(experience);
        log.info("experience saved");
        return mappers.fromExperience(experienceSaved);
    }

    @Override
    public ExperienceDTO update(ExperienceDTO experienceDTO) throws ExperienceNotFoundException {
        Experience experience = experienceRepository.findById(experienceDTO.id())
                .orElseThrow( () -> new ExperienceNotFoundException("experience not found"));
        experience.setEnd(experienceDTO.end());
        experience.setStart(experienceDTO.start());
        experience.setTitle(experienceDTO.description());
        experience.setDescription(experienceDTO.description());
        experience.setEnterprise(experienceDTO.enterprise());
        Experience experienceUpdated = experienceRepository.save(experience);
        log.info("experience updated");
        return mappers.fromExperience(experienceUpdated);
    }

    @Override
    public ExperienceDTO findById(Long id) throws ExperienceNotFoundException {
        Experience experience = experienceRepository.findById(id)
                .orElseThrow( () -> new ExperienceNotFoundException("experience not found"));
        log.info("experience found");
        return mappers.fromExperience(experience);
    }

    @Override
    public List<ExperienceDTO> findByEmployeeId(String employeeId) {
        List<Experience> experiences = experienceRepository.findByEmployeeId(employeeId);
        log.info("experiences found by employee id");
        return mappers.fromListOfExperiences(experiences);
    }

    @Override
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
        log.info("experience deleted");
    }

    @Override
    public void deleteAllByEmployeeId(String employeeId) {
        List<Experience> experiences = experienceRepository.findByEmployeeId(employeeId);
        experienceRepository.deleteAll(experiences);
        log.info("experience deleted");
    }
}
