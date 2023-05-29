package com.brodygaudel.gestionemployee.entities;

import com.brodygaudel.gestionemployee.enums.ContractType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ContractType type;
    private String title;
    private String description;
    private BigDecimal salary;
    @OneToOne(mappedBy = "contract")
    private Employee employee;
}
