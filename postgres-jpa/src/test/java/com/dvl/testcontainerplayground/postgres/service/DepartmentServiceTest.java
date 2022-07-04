package com.dvl.testcontainerplayground.postgres.service;

import com.dvl.testcontainerplayground.postgres.entity.Department;
import com.dvl.testcontainerplayground.postgres.repositories.DepartmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DepartmentServiceTest {

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private DepartmentRepository departmentRepository;

    private Department itDepartment;

    public DepartmentServiceTest() {
        itDepartment = Department.builder()
                .departmentAddress("Dorpstraat 12")
                .departmentCode("12-C")
                .departmentName("IT")
                .build();
    }

    @BeforeEach
    public void beforeEach() {
        departmentRepository.deleteAll();
        itDepartment = departmentRepository.save(itDepartment);

        assertEquals(1, departmentRepository.findAll().size());
        assertTrue(departmentRepository.findById(itDepartment.getDepartmentId()).isPresent());
    }

    @Test
    public void saveDepartment() {
        Department salesDepartment = Department.builder()
                .departmentAddress("Dorpstraat 12")
                .departmentCode("14-A")
                .departmentName("Sales")
                .build();
        departmentService.saveDepartment(salesDepartment);
        assertEquals(2, departmentService.fetchDepartmentList().size());
    }


    @Test
    public void fetchDepartmentList() {
        assertEquals(1, departmentService.fetchDepartmentList().size());
        assertEquals(itDepartment.getDepartmentId(), departmentService.fetchDepartmentList().get(0).getDepartmentId());
    }

    @Test
    public void updateDepartment() {
        itDepartment.setDepartmentCode("12-A");
        departmentService.updateDepartment(itDepartment, itDepartment.getDepartmentId());
        assertEquals(1, departmentService.fetchDepartmentList().size());
        assertEquals("12-A", departmentService.fetchDepartmentList().get(0).getDepartmentCode());
    }

    @Test
    public void deleteDepartmentById() {
        departmentService.deleteDepartmentById(itDepartment.getDepartmentId());
        assertEquals(0, departmentService.fetchDepartmentList().size());
    }

}
