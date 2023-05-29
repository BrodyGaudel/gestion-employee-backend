package com.brodygaudel.gestionemployee.repositories;

import com.brodygaudel.gestionemployee.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query("select e from Employee e where e.name like :keyword or e.firstname like :keyword")
    List<Employee> search(@Param("keyword") String keyword);

    @Query("select e from Employee e where e.cin =?1")
    Employee findByCin(String cin);

    @Query("select e from Employee e where e.email =?1")
    Employee findByEmail(String email);

    @Query("select case when count(e)>0 then true else false END from Employee e where e.cin=?1")
    Boolean checkIfCinExists(String cin);

    @Query("select case when count(e)>0 then true else false END from Employee e where e.email=?1")
    Boolean checkIfEmailExists(String email);

    @Query("select case when count(e)>0 then true else false END from Employee e where e.phone=?1")
    Boolean checkIfPhoneExists(String phone);


}
