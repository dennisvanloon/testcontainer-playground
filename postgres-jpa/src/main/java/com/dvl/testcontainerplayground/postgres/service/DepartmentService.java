package com.dvl.testcontainerplayground.postgres.service;

import com.dvl.testcontainerplayground.postgres.entity.Department;

import java.util.List;

public interface DepartmentService {

    Department saveDepartment(Department department);

    List<Department> fetchDepartmentList();

    Department updateDepartment(Department department, Long departmentId);

    void deleteDepartmentById(Long departmentId);
}
