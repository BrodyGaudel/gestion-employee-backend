package com.brodygaudel.gestionemployee.dtos;

import com.brodygaudel.gestionemployee.enums.ContractType;

import java.math.BigDecimal;

public record ContractDTO(Long id, ContractType type, String title,
                          String description, BigDecimal salary, String employeeId) {
}
