package com.brodygaudel.gestionemployee.dtos;

import java.util.Date;

public record AbsenceDTO(Long id, String observation, Date start, Date end, String employeeId) {
}
