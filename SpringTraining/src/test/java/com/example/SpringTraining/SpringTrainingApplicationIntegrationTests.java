package com.example.SpringTraining;

import com.example.SpringTraining.controller.DepartmentController;
import com.example.SpringTraining.repositories.DepartmentRepository;
import com.example.SpringTraining.service.DepartmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class SpringTrainingApplicationIntegrationTests {

	@Autowired(required = false)
	private DepartmentController departmentController;

	@Autowired(required = false)
	private DepartmentService departmentService;

	@Autowired(required = false)
	private DepartmentRepository departmentRepository;

	@Test
	void contextLoads() {
		// Verify that the application context loads successfully
	}

	@Test
	void beansAreCreated() {
		// Verify that all required beans are created
		assertThat(departmentController).isNotNull();
		assertThat(departmentService).isNotNull();
		assertThat(departmentRepository).isNotNull();
	}

	@Test
	void departmentRepositoryHasData() {
		// Verify that sample data was loaded
		long count = departmentRepository.count();
		assertThat(count).isGreaterThan(0);
	}
}

