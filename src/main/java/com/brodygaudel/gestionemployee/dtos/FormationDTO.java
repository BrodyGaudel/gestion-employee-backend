package com.brodygaudel.gestionemployee.dtos;

import com.brodygaudel.gestionemployee.enums.Level;

import java.util.Date;

public record FormationDTO(
        Long id,
        Level level,
        String title,
        String description,
        Date start,
        Date end,
        String employeeId) { }
