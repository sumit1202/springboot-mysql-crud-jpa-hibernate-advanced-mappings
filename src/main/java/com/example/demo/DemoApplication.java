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
			// createMultipleInstructor(appDAO);
			// findInstructorById(appDAO);
			// deleteInstructorById(appDAO);
			// findInstructorDetailById(appDAO);
			deleteInstructorDetailById(appDAO);
			System.out.println("=> Done.");
		};
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int id = 4;
		appDAO.deleteInstructorDetailById(id);
		System.out.println("=> Successfully deleted instructor detail with id " + id);
	}

	private void findInstructorDetailById(AppDAO appDAO) {
		int id = 3;
		System.out.println("Finding instructor detail with id: " + id);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("=> instructor detail: " + instructorDetail);
		System.out.println("=> instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructorById(AppDAO appDAO) {
		int id = 1;
		appDAO.deleteInstructorById(id);
		System.out.println("=> Successfully deleted instructor with id " + id);
	}

	private void findInstructorById(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding instructor with id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("=> instructor: " + instructor);
		System.out.println("=> instructor detail: " + instructor.getInstructorDetail());
	}

	private void createMultipleInstructor(AppDAO appDAO) {
		Instructor instructor1 = new Instructor("Lucy", "Wayne", "wayne.lucy@email.com");
		InstructorDetail instructorDetail1 = new InstructorDetail("www.ytt.com", "touring");
		Instructor instructor2 = new Instructor("John", "Wick", "wick.john@email.com");
		InstructorDetail instructorDetail2 = new InstructorDetail("www.yt.com", "vlogging");
		instructor1.setInstructorDetail(instructorDetail1);
		instructor2.setInstructorDetail(instructorDetail2);
		System.out.println("=> Saving instructor1 to DB: " + instructor1);
		System.out.println("=> Saving instructor2 to DB: " + instructor2);
		appDAO.save(instructor1);
		appDAO.save(instructor2);
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Lucy", "Wayne", "wayne.lucy@email.com");
		InstructorDetail instructorDetail = new InstructorDetail("www.ytt.com", "touring");
		instructor.setInstructorDetail(instructorDetail);
		System.out.println("=> Saving instructor to DB: " + instructor);
		appDAO.save(instructor);
	}

}
