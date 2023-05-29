package com.brodygaudel.gestionemployee.dtos;

import java.util.Date;

public record ExperienceDTO(
        Long id,
        String title,
        String description,
        String enterprise,
        Date start,
        Date end,
        String employeeId) { }
