package com.practicecode.onetoonerelation.dao;

import com.practicecode.onetoonerelation.entity.Instructor;

import java.util.List;


public interface AppDAO {
    void save(Instructor theInstructor);
    List<Instructor> findAll();
    Instructor findInstructorById(int theid);

    void delete(int theId);

}
