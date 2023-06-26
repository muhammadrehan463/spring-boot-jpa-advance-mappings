package com.practicecode.onetoonerelation.dao;

import com.practicecode.onetoonerelation.entity.Instructor;
import com.practicecode.onetoonerelation.entity.InstructorDetail;

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


}
