package com.brodygaudel.gestionemployee.services;

import com.brodygaudel.gestionemployee.dtos.EmployeeDTO;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.SavedEmployeeRejectedException;

import java.util.List;

public interface EmployeeService {
    EmployeeDTO save(EmployeeDTO employeeDTO) throws SavedEmployeeRejectedException;
    EmployeeDTO update(EmployeeDTO employeeDTO) throws EmployeeNotFoundException;
    EmployeeDTO findById(String id);
    List<EmployeeDTO> findAll();
    List<EmployeeDTO> search(String keyword);
    void deleteById(String id);
    void deleteAll();
}
