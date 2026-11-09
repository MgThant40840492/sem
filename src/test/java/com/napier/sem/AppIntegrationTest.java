package com.napier.sem;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppIntegrationTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
        app.connect("localhost:33061", 30000);

    }

    @Test
    void testGetEmployee()
    {
        Employee emp = app.getEmployee(255530);
        assertNotNull(emp);
        assertEquals(255530, emp.emp_no);
        assertEquals("Ronghao", emp.first_name);
        assertEquals("Garigliano", emp.last_name);
        assertEquals("Technique Leader", emp.title);
        assertNotNull(emp.dept);
        assertNotNull(emp.manager);
    }

    @Test
    void testGetEmployeeNotFound()
    {
        Employee emp = app.getEmployee(-1);

        assertNull(emp);
    }

    @Test
    void testGetDepartment()
    {
        Department dept = app.getDepartment("Development");

        assertNotNull(dept);
        assertEquals("d005", dept.dept_no);
        assertEquals("Development", dept.dept_name);
        assertNotNull(dept.manager);
    }

    @Test
    void testGetDepartmentNotFound()
    {
        Department dept = app.getDepartment("Invalid Department");

        assertNull(dept);
    }

    @Test
    void testGetSalariesByDepartment()
    {
        Department dept = app.getDepartment("Development");

        ArrayList<Employee> employees = app.getSalariesByDepartment(dept);

        assertNotNull(employees);
        assertFalse(employees.isEmpty());

        for (Employee emp : employees)
        {
            assertNotNull(emp);
            assertNotNull(emp.first_name);
            assertNotNull(emp.last_name);
            assertTrue(emp.salary > 0);
        }
    }

    @Test
    void testGetSalariesByDepartmentNull()
    {
        ArrayList<Employee> employees = app.getSalariesByDepartment(null);

        assertNull(employees);
    }

    @Test
    void testGetAllSalaries()
    {
        ArrayList<Employee> employees = app.getAllSalaries();

        assertNotNull(employees);
        assertFalse(employees.isEmpty());

        for (Employee emp : employees)
        {
            assertNotNull(emp);
            assertNotNull(emp.first_name);
            assertNotNull(emp.last_name);
            assertTrue(emp.salary > 0);
        }
    }

    @Test
    void testAddEmployee()
    {
        Employee emp = new Employee();
        emp.emp_no = 999999;
        emp.first_name = "Kevin";
        emp.last_name = "Chalmers";
        app.addEmployee(emp);
        emp = app.getEmployee(999999);
        assertEquals(emp.emp_no, 999999);
        assertEquals(emp.first_name, "Kevin");
        assertEquals(emp.last_name, "Chalmers");
    }
}
