package com.brodygaudel.gestionemployee.util.mapping;

import com.brodygaudel.gestionemployee.dtos.*;
import com.brodygaudel.gestionemployee.entities.*;

import java.util.List;

public interface Mappers {
    Formation fromFormationDTO(FormationDTO formationDTO);
    FormationDTO fromFormation(Formation formation);
    List<FormationDTO> fromListOfFormations(List<Formation> formations);

    Experience fromExperienceDTO(ExperienceDTO experienceDTO);
    ExperienceDTO fromExperience(Experience experience);
    List<ExperienceDTO> fromListOfExperiences(List<Experience> experiences);

    Absence fromAbsenceDTO(AbsenceDTO absenceDTO);
    AbsenceDTO fromAbsence(Absence absence);
    List<AbsenceDTO> fromListOfAbsences(List<Absence> absences);

    ContractDTO fromContract(Contract contract);
    Contract fromContractDTO(ContractDTO contractDTO);

    Employee fromEmployeeDTO(EmployeeDTO employeeDTO);
    EmployeeDTO fromEmployee(Employee employee);
    List<EmployeeDTO> fromListOfEmployees(List<Employee> employees);
}
