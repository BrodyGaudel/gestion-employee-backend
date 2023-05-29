package com.brodygaudel.gestionemployee.repositories;

import com.brodygaudel.gestionemployee.entities.Experience;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExperienceRepository extends JpaRepository<Experience, Long> {

    @Query("select e from Experience e where e.employee.id =?1")
    List<Experience> findByEmployeeId(String id);
}
