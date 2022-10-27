package com.oracle.repository;

import com.oracle.model.Employee;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repo.
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
