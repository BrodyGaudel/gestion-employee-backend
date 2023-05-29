package com.brodygaudel.gestionemployee.repositories;

import com.brodygaudel.gestionemployee.entities.Formation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation, Long> {

    @Query("select f from Formation f where f.employee.id =?1")
    List<Formation> findByEmployeeId(String id);
}
