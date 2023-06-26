package com.practicecode.onetoonerelation.dao;

import com.practicecode.onetoonerelation.entity.Course;
import com.practicecode.onetoonerelation.entity.Instructor;
import com.practicecode.onetoonerelation.entity.InstructorDetail;
import com.practicecode.onetoonerelation.entity.Student;

import java.util.List;

public interface AppDAO {

    // Instructors
    void save(Instructor theInstructor);

    List<Instructor> findAll();

    Instructor findInstructorById(int theid);

    void delete(int theId);

    // Instructor details
    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetail(int theId);

    List<InstructorDetail> findAllDetail();

    List<Course> findCourseByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update (Instructor tenpInstructor);

    Course findCourseById(int theId);

    void updateCourse (Course tempCourse);

    void deleteInstructor (int theId);

    void deleteCourse (int theId);

    void save (Course theCourse);

    Course findCourseAndReviewsByCourseId (int theId);

    Course findCourseAndStudentByCourseId (int theId);

    Student findStudentAndCourseByStudentId (int theId);

    void update (Student theStudent);

    void deleteStudent (int theId);
}