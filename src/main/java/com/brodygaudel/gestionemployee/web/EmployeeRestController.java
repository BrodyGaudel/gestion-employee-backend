package com.brodygaudel.gestionemployee.web;

import com.brodygaudel.gestionemployee.dtos.EmployeeDTO;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.SavedEmployeeRejectedException;
import com.brodygaudel.gestionemployee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    private final EmployeeService service;

    public EmployeeRestController(EmployeeService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public EmployeeDTO save(@RequestBody EmployeeDTO employeeDTO) throws SavedEmployeeRejectedException {
        return service.save(employeeDTO);
    }

    @PutMapping("/update")
    public EmployeeDTO update(@RequestBody EmployeeDTO employeeDTO) throws EmployeeNotFoundException {
        return service.update(employeeDTO);
    }

    @GetMapping("/get/{id}")
    public EmployeeDTO findById(@PathVariable String id){
        return service.findById(id);
    }

    @GetMapping("/list")
    public List<EmployeeDTO> findAll(){
        return service.findAll();
    }

    @GetMapping("/search")
    public List<EmployeeDTO> search(@RequestParam(name = "keyword", defaultValue = "") String keyword){
        return service.search("%"+keyword+"%");
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id){
        service.deleteById(id);
    }

    @DeleteMapping("/clear")
    public void deleteAll(){
        service.deleteAll();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
