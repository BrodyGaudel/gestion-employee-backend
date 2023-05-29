package com.brodygaudel.gestionemployee.services.implementation;

import com.brodygaudel.gestionemployee.dtos.EmployeeDTO;
import com.brodygaudel.gestionemployee.entities.Contract;
import com.brodygaudel.gestionemployee.entities.Employee;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.SavedEmployeeRejectedException;
import com.brodygaudel.gestionemployee.repositories.EmployeeRepository;
import com.brodygaudel.gestionemployee.services.EmployeeService;
import com.brodygaudel.gestionemployee.util.idgenerator.IdGenerator;
import com.brodygaudel.gestionemployee.util.mapping.Mappers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final Mappers mappers;
    private final IdGenerator idGenerator;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, Mappers mappers, IdGenerator idGenerator) {
        this.employeeRepository = employeeRepository;
        this.mappers = mappers;
        this.idGenerator = idGenerator;
    }

    @Override
    public EmployeeDTO save(EmployeeDTO employeeDTO) throws SavedEmployeeRejectedException {
        Boolean cin = employeeRepository.checkIfCinExists(employeeDTO.getCin());
        if(Boolean.TRUE.equals(cin)){
            throw new SavedEmployeeRejectedException("cin already used by another employee!");
        }
        Boolean email = employeeRepository.checkIfEmailExists(employeeDTO.getEmail());
        if(Boolean.TRUE.equals(email)){
            throw new SavedEmployeeRejectedException("email already used by another employee!");
        }
        Boolean phone = employeeRepository.checkIfPhoneExists(employeeDTO.getPhone());
        if(Boolean.TRUE.equals(phone)){
            throw new SavedEmployeeRejectedException("phone already used by another employee!");
        }
        Employee employee = mappers.fromEmployeeDTO(employeeDTO);
        Contract contract = employee.getContract();
        employee.setId(idGenerator.generateId(employee.getSex()));
        contract.setEmployee(employee);
        employee.setContract(contract);
        Employee employeeSaved = employeeRepository.save(employee);
        log.info("employee saved");
        return mappers.fromEmployee(employeeSaved);
    }

    @Override
    public EmployeeDTO update(EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeDTO.getId())
                .orElseThrow( () -> new EmployeeNotFoundException("the employee you are trying to update does not exist"));
        employee.setFirstname(employeeDTO.getFirstname());
        employee.setName(employeeDTO.getName());
        employee.setAddress(employeeDTO.getAddress());
        employee.setPlaceOfBirth(employeeDTO.getPlaceOfBirth());
        employee.setDateOfBirth(employeeDTO.getDateOfBirth());
        employee.setNationality(employeeDTO.getNationality());
        employee.setCin(employeeDTO.getCin());
        employee.setPhone(employeeDTO.getPhone());
        employee.setEmail(employeeDTO.getEmail());
        Contract contract = employee.getContract();
        contract.setType(employeeDTO.getContractDTO().type());
        contract.setTitle(employeeDTO.getContractDTO().title());
        contract.setSalary(employeeDTO.getContractDTO().salary());
        contract.setDescription(employeeDTO.getContractDTO().description());
        contract.setEmployee(employee);
        employee.setContract(contract);
        Employee employeeUpdated = employeeRepository.save(employee);
        log.info("employee updated");
        return mappers.fromEmployee(employeeUpdated);
    }

    @Override
    public EmployeeDTO findById(String id) {
        Employee employee = employeeRepository.findById(id).orElse(null);
        log.info("employee found");
        return mappers.fromEmployee(employee);
    }

    @Override
    public List<EmployeeDTO> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        log.info("employees found");
        return mappers.fromListOfEmployees(employees);
    }

    @Override
    public List<EmployeeDTO> search(String keyword) {
        List<Employee> employees = employeeRepository.search(keyword);
        log.info("employees found by keyword");
        return mappers.fromListOfEmployees(employees);
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
        log.info("employee deleted");
    }

    @Override
    public void deleteAll() {
        employeeRepository.deleteAll();
        log.info("all employees deleted");
    }
}
