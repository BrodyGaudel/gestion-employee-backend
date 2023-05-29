package com.brodygaudel.gestionemployee.web;

import com.brodygaudel.gestionemployee.dtos.ExperienceDTO;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.ExperienceNotFoundException;
import com.brodygaudel.gestionemployee.services.ExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/experience")
public class ExperienceRestController {

    private final ExperienceService service;

    public ExperienceRestController(ExperienceService service) {
        this.service = service;
    }

    @PostMapping("/save")
    public ExperienceDTO save(@RequestBody ExperienceDTO experienceDTO) throws EmployeeNotFoundException{
        return service.save(experienceDTO);
    }

    @PutMapping("/update")
    public ExperienceDTO update(@RequestBody ExperienceDTO experienceDTO) throws ExperienceNotFoundException{
        return service.update(experienceDTO);
    }

    @GetMapping("/get/{id}")
    public ExperienceDTO findById(@PathVariable Long id) throws ExperienceNotFoundException{
        return service.findById(id);
    }

    @GetMapping("/list/{employeeId}")
    public List<ExperienceDTO> findByEmployeeId(@PathVariable String employeeId){
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
