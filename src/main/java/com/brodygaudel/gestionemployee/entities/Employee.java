package com.brodygaudel.gestionemployee.entities;

import com.brodygaudel.gestionemployee.enums.Sex;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Employee {
    @Id
    private String id;
    private String firstname;
    private String name;
    private String nationality;
    private String placeOfBirth;
    private Date dateOfBirth;
    @Enumerated(EnumType.STRING)
    private Sex sex;
    @Column(unique = true)
    private String cin;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String phone;
    private String address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contract_id", referencedColumnName = "id")
    private Contract contract;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Formation> formations;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Experience> experiences;

    @OneToMany(mappedBy = "employee", fetch = FetchType.LAZY)
    private List<Absence> absences;
}
