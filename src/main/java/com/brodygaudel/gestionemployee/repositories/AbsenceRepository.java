package com.brodygaudel.gestionemployee.repositories;

import com.brodygaudel.gestionemployee.entities.Absence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AbsenceRepository extends JpaRepository<Absence, Long> {

    @Query("select a from Absence a where a.employee.id =?1")
    List<Absence> findByEmployeeId(String id);
}
