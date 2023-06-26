package com.practicecode.onetoonerelation;

import com.practicecode.onetoonerelation.dao.AppDAO;
import com.practicecode.onetoonerelation.entity.Instructor;
import com.practicecode.onetoonerelation.entity.InstructorDetail;
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
			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//findAllData(appDAO);
			deleteData(appDAO);
		};
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
		Instructor tempInstructor = new Instructor("Brian", "Eve",  "brain@gmail.com");

		// create instructor detail
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "reading");

		tempInstructor.setInstructorDetail(tempInstructorDetail);

		System.out.println("Savcing the instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Data saved in database..");
	}

}
