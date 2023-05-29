package com.brodygaudel.gestionemployee.repositories;

import com.brodygaudel.gestionemployee.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ContractRepository extends JpaRepository<Contract, Long> {

    @Query("select c from Contract c where c.employee.id=?1")
    Contract findByEmployeeId(String id);
}
