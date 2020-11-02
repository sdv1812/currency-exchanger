package com.gigrt.currencyexchanger.rest;

import com.gigrt.currencyexchanger.model.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EmployeeRestControllerTest {
    private static final Integer EMPLOYEE_ID = 1;
    private static final Integer EMPLOYEE_ID_N0T_EXIST = 0;

    @LocalServerPort
    private int port;

    @Autowired
    private EmployeeRestController employeeRestController;

    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    public void setUp() {

    }

    @Test
    public void contextLoads() throws Exception {
        assertThat(employeeRestController).isNotNull();
    }

    @Test
    public void getEmployee_ShouldReturnEmployee() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/" + "employees/" + EMPLOYEE_ID,
                Employee.class)).hasFieldOrProperty("id").isNotNull();
    }

    @Test
    public void getEmployee_ShouldNotReturnEmployee() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/api/v1/" + "employees/" + EMPLOYEE_ID_N0T_EXIST,
                Employee.class)).hasFieldOrProperty("id").isNotNull();
    }

    @Test
    void getAllEmployee() {
    }

    @Test
    void getEmployee() {
    }

    @Test
    void addEmployee() {
    }

    @Test
    void updateEmployee() {
    }

    @Test
    void deleteEmployee() {
    }
}