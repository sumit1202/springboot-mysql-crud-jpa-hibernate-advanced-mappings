package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.dao.AppDAO;
import com.example.demo.dao.Course;
import com.example.demo.entity.Instructor;
import com.example.demo.entity.InstructorDetail;
import java.util.List;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	@Autowired
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {

			System.out.println("=> Command Line Runner started.");

			// one-to-one mapping
			// createInstructor(appDAO);
			// createMultipleInstructor(appDAO);
			// findInstructorById(appDAO);
			// deleteInstructor(appDAO);
			// findInstructorDetailById(appDAO);
			// deleteInstructorDetailById(appDAO);

			// many-to-one/one-to-many mapping
			// createInstructorWithCourses(appDAO);
			// findInstructorWithCourses(appDAO);
			// findCoursesForInstructor(appDAO);
			// findInstructorWithCoursesJoinFetch(appDAO);
			// updateInstructor(appDAO);
			// updateCourse(appDAO);
			// deleteInstructor(appDAO);
			deleteCourseById(appDAO);

			System.out.println("=> Done.");
		};
	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 11;
		System.out.println("deleting course with id: " + id);
		appDAO.deleteCourseById(id);
		System.out.println("=> successfully deleted course with id " + id);
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;
		System.out.println("=> Finding course id: " + id);
		Course course = appDAO.findCourseById(id);
		System.out.println("=> updating course with id: " + id);
		course.setTitle("Computer Science");
		appDAO.update(course);
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("=> Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("=> updating instructor with id: " + id);
		instructor.setFirstName("John");
		instructor.setLastName("Wick");
		instructor.setEmail("wick.john@email.com");
		appDAO.update(instructor);
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		System.out.println("=> Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("=> instructor: " + instructor);
		System.out.println("=> instructor's courses: " + instructor.getCourses());

	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("=> Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("=> instructor: " + instructor);

		// workaround for lazy-fetch type loading to avoid exception
		System.out.println("=> Finding courses for instructor id: " + id);
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		instructor.setCourses(courses);
		System.out.println("=> instructor's courses: " + instructor.getCourses());
	}

	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("=> Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		System.out.println("=> instructor: " + instructor);

		// below throw exception due to lazy-fetch type loading
		// change to eager fetch type loading will load every course, hence will resolve
		// exception
		System.out.println("=> instructor's courses: " + instructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		Instructor instructor = new Instructor("Susan", "Fox", "fox.susan@email.com");
		InstructorDetail instructorDetail = new InstructorDetail("www.yttt.com", "filming");
		instructor.setInstructorDetail(instructorDetail);

		// create some courses amd add to instructor
		Course course1 = new Course("Physics");
		Course course2 = new Course("Maths");
		instructor.add(course1);
		instructor.add(course2);
		System.out.println("=> saving instructor to DB: " + instructor);
		System.out.println("=> saving instructor's courses to DB: " + instructor.getCourses());

		appDAO.save(instructor);
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int id = 4;
		appDAO.deleteInstructorDetailById(id);
		System.out.println("=> successfully deleted instructor detail with id " + id);
	}

	private void findInstructorDetailById(AppDAO appDAO) {
		int id = 3;
		System.out.println("finding instructor detail with id: " + id);
		InstructorDetail instructorDetail = appDAO.findInstructorDetailById(id);
		System.out.println("=> instructor detail: " + instructorDetail);
		System.out.println("=> instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("deleting instructor with id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("=> Successfully deleted instructor with id " + id);
	}

	private void findInstructorById(AppDAO appDAO) {
		int id = 2;
		System.out.println("finding instructor with id: " + id);
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
		System.out.println("=> saving instructor1 to DB: " + instructor1);
		System.out.println("=> saving instructor2 to DB: " + instructor2);
		appDAO.save(instructor1);
		appDAO.save(instructor2);
	}

	private void createInstructor(AppDAO appDAO) {
		Instructor instructor = new Instructor("Lucy", "Wayne", "wayne.lucy@email.com");
		InstructorDetail instructorDetail = new InstructorDetail("www.ytt.com", "touring");
		instructor.setInstructorDetail(instructorDetail);
		System.out.println("=> saving instructor to DB: " + instructor);
		appDAO.save(instructor);
	}

}
