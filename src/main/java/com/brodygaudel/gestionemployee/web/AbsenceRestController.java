package com.brodygaudel.gestionemployee.web;

import com.brodygaudel.gestionemployee.dtos.AbsenceDTO;
import com.brodygaudel.gestionemployee.exceptions.AbsenceNotFoundException;
import com.brodygaudel.gestionemployee.exceptions.EmployeeNotFoundException;
import com.brodygaudel.gestionemployee.services.AbsenceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/absence")
public class AbsenceRestController {

    private final AbsenceService service;

    public AbsenceRestController(AbsenceService service) {
        this.service = service;
    }

    @GetMapping("/save")
    public AbsenceDTO save(@RequestBody AbsenceDTO absenceDTO) throws EmployeeNotFoundException{
        return service.save(absenceDTO);
    }

    @PostMapping("/update")
    public AbsenceDTO update(@RequestBody AbsenceDTO absenceDTO) throws AbsenceNotFoundException{
        return service.update(absenceDTO);
    }

    @GetMapping("/get/{id}")
    public AbsenceDTO findById(@PathVariable Long id) throws AbsenceNotFoundException{
        return service.findById(id);
    }

    @GetMapping("/list/{employeeId}")
    public List<AbsenceDTO> findByEmployeeId(@PathVariable String employeeId){
        return service.findByEmployeeId(employeeId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable Long id){
        service.deleteById(id);
    }

    @DeleteMapping("/clear/{employeeId}")
    public void deleteByEmployeeId(@PathVariable String employeeId){
        service.deleteByEmployeeId(employeeId);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exceptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
