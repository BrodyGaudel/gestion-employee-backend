package com.brodygaudel.gestionemployee.services.implementation;

import com.brodygaudel.gestionemployee.dtos.FormationDTO;
import com.brodygaudel.gestionemployee.entities.Employee;
import com.brodygaudel.gestionemployee.entities.Formation;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.FormationNotFoundException;
import com.brodygaudel.gestionemployee.repositories.EmployeeRepository;
import com.brodygaudel.gestionemployee.repositories.FormationRepository;
import com.brodygaudel.gestionemployee.services.FormationService;
import com.brodygaudel.gestionemployee.util.mapping.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class FormationServiceImpl implements FormationService {

    private final FormationRepository formationRepository;
    private final EmployeeRepository employeeRepository;
    private final Mappers mappers;

    public FormationServiceImpl(FormationRepository formationRepository, EmployeeRepository employeeRepository, Mappers mappers) {
        this.formationRepository = formationRepository;
        this.employeeRepository = employeeRepository;
        this.mappers = mappers;
    }

    @Override
    public FormationDTO save(FormationDTO formationDTO) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(formationDTO.employeeId())
                .orElseThrow( () -> new EmployeeNotFoundException("employee not found"));
        Formation formation = mappers.fromFormationDTO(formationDTO);
        formation.setEmployee(employee);
        Formation formationSaved = formationRepository.save(formation);
        log.info("formation saved");
        return mappers.fromFormation(formationSaved);
    }

    @Override
    public FormationDTO update(FormationDTO formationDTO) throws FormationNotFoundException {
        Formation formation = formationRepository.findById(formationDTO.id())
                .orElseThrow( () -> new FormationNotFoundException("formation not found"));
        formation.setEnd(formationDTO.end());
        formation.setStart(formationDTO.start());
        formation.setTitle(formation.getTitle());
        formation.setDescription(formation.getDescription());
        formation.setLevel(formation.getLevel());
        Formation formationSaved = formationRepository.save(formation);
        log.info("formation updated");
        return mappers.fromFormation(formationSaved);
    }

    @Override
    public FormationDTO findById(Long id) throws FormationNotFoundException {
        Formation formation = formationRepository.findById(id)
                .orElseThrow( () -> new FormationNotFoundException("formation not found"));
        log.info("formation found with id :"+id);
        return mappers.fromFormation(formation);
    }


    @Override
    public List<FormationDTO> findByEmployeeId(String employeeId) {
        List<Formation> formations = formationRepository.findByEmployeeId(employeeId);
        log.info("formation found with employee id :"+employeeId);
        return mappers.fromListOfFormations(formations);
    }

    @Override
    public void deleteById(Long id) {
        formationRepository.deleteById(id);
        log.info("formation deleted");
    }

    @Override
    public void deleteAllByEmployeeId(String employeeId) {
        List<Formation> formations = formationRepository.findByEmployeeId(employeeId);
        formationRepository.deleteAll(formations);
        log.info("all formation with deleted with employee id :"+employeeId);
    }
}
