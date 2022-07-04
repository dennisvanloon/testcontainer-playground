package com.dvl.testcontainerplayground.postgres.service;

import java.util.List;

import com.dvl.testcontainerplayground.postgres.entity.Department;
import com.dvl.testcontainerplayground.postgres.repositories.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department saveDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public List<Department> fetchDepartmentList() {
        return departmentRepository.findAll();
    }

    @Override
    public Department updateDepartment(Department department, Long departmentId) {
        departmentRepository.findById(departmentId).ifPresent(updatedDepartment -> {
            updatedDepartment.setDepartmentName(department.getDepartmentName());
            updatedDepartment.setDepartmentAddress(department.getDepartmentAddress());
            updatedDepartment.setDepartmentCode(department.getDepartmentCode());
            departmentRepository.save(updatedDepartment);
        });
        return departmentRepository.findById(departmentId).orElse(null);

    }

    @Override
    public void deleteDepartmentById(Long departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}