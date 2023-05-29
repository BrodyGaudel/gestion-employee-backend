package com.brodygaudel.gestionemployee.util.mapping;

import com.brodygaudel.gestionemployee.dtos.*;
import com.brodygaudel.gestionemployee.entities.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MappersImpl implements Mappers {
    @Override
    public Formation fromFormationDTO(FormationDTO formationDTO) {
        try{
            return Formation.builder()
                    .id(formationDTO.id())
                    .description(formationDTO.description())
                    .title(formationDTO.title())
                    .start(formationDTO.start())
                    .end(formationDTO.end())
                    .level(formationDTO.level())
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public FormationDTO fromFormation(Formation formation) {
        try{
            return new FormationDTO(
                    formation.getId(),
                    formation.getLevel(),
                    formation.getTitle(),
                    formation.getDescription(),
                    formation.getStart(),
                    formation.getEnd(),
                    formation.getEmployee().getId()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<FormationDTO> fromListOfFormations(List<Formation> formations) {
        try{
            return formations.stream().map(this::fromFormation).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public Experience fromExperienceDTO(ExperienceDTO experienceDTO) {
        try{
            return Experience.builder()
                    .title(experienceDTO.title())
                    .description(experienceDTO.description())
                    .enterprise(experienceDTO.enterprise())
                    .start(experienceDTO.start())
                    .end(experienceDTO.end())
                    .id(experienceDTO.id())
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public ExperienceDTO fromExperience(Experience experience) {
        try{
            return new ExperienceDTO(
                    experience.getId(),
                    experience.getTitle(),
                    experience.getDescription(),
                    experience.getEnterprise(),
                    experience.getStart(),
                    experience.getEnd(),
                    experience.getEmployee().getId()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<ExperienceDTO> fromListOfExperiences(List<Experience> experiences) {
        try{
            return experiences.stream().map(this::fromExperience).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public Absence fromAbsenceDTO(AbsenceDTO absenceDTO) {
        try{
            return Absence.builder()
                    .start(absenceDTO.start())
                    .end(absenceDTO.end())
                    .observation(absenceDTO.observation())
                    .id(absenceDTO.id())
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public AbsenceDTO fromAbsence(Absence absence) {
        try{
            return new AbsenceDTO(
                    absence.getId(), absence.getObservation(), absence.getStart(),
                    absence.getEnd(), absence.getEmployee().getId()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<AbsenceDTO> fromListOfAbsences(List<Absence> absences) {
        try{
            return absences.stream().map(this::fromAbsence).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }

    @Override
    public ContractDTO fromContract(Contract contract) {
        try{
            return new ContractDTO(
                    contract.getId(), contract.getType(), contract.getTitle(),
                    contract.getDescription(), contract.getSalary(),contract.getEmployee().getId()
            );
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Contract fromContractDTO(ContractDTO contractDTO) {
        try{
            return Contract.builder()
                    .type(contractDTO.type())
                    .description(contractDTO.title())
                    .title(contractDTO.title())
                    .salary(contractDTO.salary())
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public Employee fromEmployeeDTO(EmployeeDTO employeeDTO) {
        try{
            Contract contract = fromContractDTO(employeeDTO.getContractDTO());
            return Employee.builder()
                    .cin(employeeDTO.getCin())
                    .email(employeeDTO.getEmail())
                    .address(employeeDTO.getAddress())
                    .firstname(employeeDTO.getFirstname())
                    .name(employeeDTO.getName())
                    .dateOfBirth(employeeDTO.getDateOfBirth())
                    .placeOfBirth(employeeDTO.getPlaceOfBirth())
                    .nationality(employeeDTO.getNationality())
                    .sex(employeeDTO.getSex())
                    .phone(employeeDTO.getPhone())
                    .id(employeeDTO.getId())
                    .contract(contract)
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public EmployeeDTO fromEmployee(Employee employee) {
        try{
            ContractDTO contractDTO = fromContract(employee.getContract());
            return EmployeeDTO.builder()
                    .id(employee.getId())
                    .address(employee.getAddress())
                    .email(employee.getEmail())
                    .name(employee.getName())
                    .firstname(employee.getFirstname())
                    .phone(employee.getPhone())
                    .cin(employee.getCin())
                    .sex(employee.getSex())
                    .nationality(employee.getNationality())
                    .placeOfBirth(employee.getPlaceOfBirth())
                    .dateOfBirth(employee.getDateOfBirth())
                    .contractDTO(contractDTO)
                    .build();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public List<EmployeeDTO> fromListOfEmployees(List<Employee> employees) {
        try{
            return employees.stream().map(this::fromEmployee).toList();
        }catch (Exception e){
            return Collections.emptyList();
        }
    }
}
