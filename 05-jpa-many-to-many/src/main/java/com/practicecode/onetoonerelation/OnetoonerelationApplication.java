package com.practicecode.onetoonerelation;

import com.practicecode.onetoonerelation.dao.AppDAO;
import com.practicecode.onetoonerelation.entity.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OnetoonerelationApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnetoonerelationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			//createCourseAndStudent(appDAO);
			//findCourseAndStudentByCourseId(appDAO);
			//findStudentAndCourseByStudentId(appDAO);
			//addMoreCoursesForStudent(appDAO);
			//deleteSingleCourse(appDAO);
			//deleteStudent(appDAO);
		};
	}

	private void deleteStudent(AppDAO appDAO) {
		appDAO.deleteStudent(1);
		System.out.println("Student Deleted.");
	}

	private void deleteSingleCourse(AppDAO appDAO) {
		appDAO.deleteCourse(10);
		System.out.println("Course Deleted.");
	}

	private void addMoreCoursesForStudent(AppDAO appDAO) {
		Student tempStudent = appDAO.findStudentAndCourseByStudentId(1);

		// creating more courses
		Course tempCourse1 = new Course("Machine Learning");
		Course tempCourse2 = new Course("Artificial Intelligence");

		// adding courses to student
		tempStudent.addCourse(tempCourse1);
		tempStudent.addCourse(tempCourse2);

		System.out.println("Saving Student: " + tempStudent);
		System.out.println("Associated Courses: " + tempStudent.getCourses());

		appDAO.update(tempStudent);

		System.out.println("Updated.");
	}

	private void findStudentAndCourseByStudentId(AppDAO appDAO) {
		Student temp = appDAO.findStudentAndCourseByStudentId(1);
		System.out.println("Student: " + temp);
		System.out.println("Courses of student: " + temp.getCourses());
	}

	private void findCourseAndStudentByCourseId(AppDAO appDAO) {
		Course temp = appDAO.findCourseAndStudentByCourseId(10);
		System.out.println("Course: " + temp);
		System.out.println("Students in the course: " + temp.getStudents());
	}

	private void createCourseAndStudent(AppDAO appDAO) {
		// create course
		Course newCourse = new Course("Object Oriented Programming");
		Course newCourse1 = new Course("Data Structures and Algorithms");

		// create student
		Student newStudent = new Student("Ali", "Rehman", "ali@gmail.com");
		Student newStudent1 = new Student("Kashif", "Khan", "kashif@gmail,com");
		Student newStudent2 = new Student("Umer", "Khan", "umer@gmail.com");

		// add students to course
		newCourse.addStudent(newStudent);
		newCourse.addStudent(newStudent1);
		newCourse.addStudent(newStudent2);

		newCourse1.addStudent(newStudent);
		newCourse1.addStudent(newStudent1);

		// add student to course
		System.out.println("Course 01: " + newCourse);
		System.out.println("Students in the course: " + newCourse.getStudents());

		System.out.println("Course 02: " + newCourse1);
		System.out.println("Students in the course: " + newCourse1.getStudents());

		// save the course
		appDAO.save(newCourse);
		appDAO.save(newCourse1);

		System.out.println("Done..");
	}

	private void deleteCourseAndReviews(AppDAO appDAO) {
		appDAO.deleteCourse(10);
		System.out.println("Course and reviews deleted successfully.");
	}

	private void findCourseAndReviewsByCourseId(AppDAO appDAO) {
		Course temp = appDAO.findCourseAndReviewsByCourseId(10);
		System.out.println("Course for id: 10: " + temp);
		System.out.println("Reviews for this course are: " + temp.getReviews());
	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// create a course
		Course newCourse = new Course("The course is about data structures and algorithms");

		// add some reviews to that course
		newCourse.addReview(new Review("This course is best for programmers"));
		newCourse.addReview(new Review("Great course"));
		newCourse.addReview(new Review("Data Structures explained very well"));
		newCourse.addReview(new Review("Recommended"));

		// save the course
		appDAO.save(newCourse);
		System.out.println("Course saved to record.");
	}

	private void deleteCourse(AppDAO appDAO) {
		appDAO.deleteCourse(10);
		System.out.println("Course with id: 10 deleted.");
	}

	private void deleteInstructor(AppDAO appDAO) {
		appDAO.deleteInstructor(1);
		System.out.println("Instructor deleted.");
	}

	private void updateCourse(AppDAO appDAO) {
		Course temp  = appDAO.findCourseById(10);
		System.out.println("Updating course with id: 1");
		temp.setTitle("This is updated course");

		appDAO.updateCourse(temp);

		System.out.println("Course Updated..");
	}

	private void updateInstructor(AppDAO appDAO) {
		Instructor temp = appDAO.findInstructorById(1);
		System.out.println("Updating instructor with id 1");
		temp.setLastName("Tester");

		appDAO.update(temp);
		System.out.println("Done..");
	}

	private void findInstructorByIdJoinFetch(AppDAO appDAO) {
		Instructor temp = appDAO.findInstructorByIdJoinFetch(1);
		System.out.println("Instructor: " + temp);
		System.out.println("Courses: " + temp.getCourses());

		System.out.println("Done...");
	}

	private void findCourseByInstructor(AppDAO appDAO) {
		// finding instructor by id
		Instructor temp = appDAO.findInstructorById(1);
		List<Course> courses = appDAO.findCourseByInstructorId(1);

		// associating objects
		temp.setCourses(courses);
		System.out.println("Course in this Id are: " + temp.getCourses());
	}

	private void findAllDetails(AppDAO appDAO) {
		List<InstructorDetail> detail = appDAO.findAllDetail();
		for (InstructorDetail data: detail){
			System.out.println(data);
		}
	}

	private void deleteInstructorDetailData(AppDAO appDAO) {
		appDAO.deleteInstructorDetail(3);
		System.out.println("Instructor detail deleted.");
	}

	private void findInstructorDetail(AppDAO appDAO) {
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(3);
		System.out.println("Instructor Detail: " + tempInstructorDetail);
		System.out.println("Instructor: " + tempInstructorDetail.getInstructor());
	}

	private void deleteData(AppDAO appDAO) {
		appDAO.delete(4);
		System.out.println("Record deleted..");
	}

	private void findAllData(AppDAO appDAO) {
		List<Instructor> instructors = appDAO.findAll();
		for (Instructor i : instructors) {
			System.out.println(i);
		}
	}

	private void findInstructor(AppDAO appDAO) {
		Instructor tempInstructor = appDAO.findInstructorById(4);
		System.out.println("Instructor: " + tempInstructor);
		System.out.println("Instructor Detail: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor tempInstructor = new Instructor("John", "Lee",  "john@gmail.com");

		// create instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "vlogging");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Savcing the instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Data saved in database..");
	}

}
