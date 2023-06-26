package com.practicecode.onetoonerelation;

import com.practicecode.onetoonerelation.dao.AppDAO;
import com.practicecode.onetoonerelation.entity.Course;
import com.practicecode.onetoonerelation.entity.Instructor;
import com.practicecode.onetoonerelation.entity.InstructorDetail;
import com.practicecode.onetoonerelation.entity.Review;
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
			//createCourseAndReviews(appDAO);
			//findCourseAndReviewsByCourseId(appDAO);
			deleteCourseAndReviews(appDAO);
		};
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
