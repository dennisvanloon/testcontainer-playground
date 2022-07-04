package com.dvl.testcontainerplayground.postgres.repositories;

import com.dvl.testcontainerplayground.postgres.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
