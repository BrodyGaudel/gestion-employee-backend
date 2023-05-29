package com.brodygaudel.gestionemployee.dtos;

import com.brodygaudel.gestionemployee.enums.Sex;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmployeeDTO {
    private String id;
    private String firstname;
    private String name;
    private String nationality;
    private String placeOfBirth;
    private Date dateOfBirth;
    private Sex sex;
    private String cin;
    private String email;
    private String phone;
    private String address;
    private ContractDTO contractDTO;
}
