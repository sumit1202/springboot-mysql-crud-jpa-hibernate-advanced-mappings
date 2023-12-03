package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.AppDAO;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			// createInstructor(appDAO);
			// findInstructorById(appDAO);
			// deleteInstructorById(appDAO);

		};
	}

	private void deleteInstructorById(AppDAO appDAO) {
		int id = 2;
		appDAO.deleteInstructorById(id);
		System.out.println("=> Successfully deleted instructior with id " + id);
	}

	private void findInstructorById(AppDAO appDAO) {
		Instructor instructor = appDAO.findInstructorById(2);
		System.out.println("=> instructor: " + instructor);
		System.out.println("=> instructor detail: " + instructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Lucy", "Wayne", "wayne.lucy@email.com");
		InstructorDetail instructorDetail = new InstructorDetail("www.ytt.com", "touring");
		instructor.setInstructorDetail(instructorDetail);
		System.out.println("=> Saving instructor to DB: " + instructor);
		appDAO.save(instructor);
		System.out.println("=> Done.");

	}

}
