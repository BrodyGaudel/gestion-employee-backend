package com.brodygaudel.gestionemployee.web;

import com.brodygaudel.gestionemployee.dtos.FormationDTO;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.FormationNotFoundException;
import com.brodygaudel.gestionemployee.services.FormationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formation")
public class FormationRestController {

    private final FormationService service;

    public FormationRestController(FormationService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public FormationDTO save(@RequestBody FormationDTO formationDTO) throws EmployeeNotFoundException{
        return service.save(formationDTO);
    }

    @PutMapping("/update")
    public FormationDTO update(@RequestBody FormationDTO formationDTO) throws FormationNotFoundException{
        return service.update(formationDTO);
    }

    @GetMapping("/get/{id}")
    public FormationDTO findById(@PathVariable Long id) throws FormationNotFoundException{
        return service.findById(id);
    }

    @GetMapping("/list/{employeeId}")
    public List<FormationDTO> findByEmployeeId(@PathVariable String employeeId){
        return service.findByEmployeeId(employeeId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @DeleteMapping("/clear/{employeeId}")
    public void deleteAllByEmployeeId(@PathVariable String employeeId){
        service.deleteAllByEmployeeId(employeeId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
