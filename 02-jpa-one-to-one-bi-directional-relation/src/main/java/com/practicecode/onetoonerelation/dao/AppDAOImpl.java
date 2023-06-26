package com.practicecode.onetoonerelation.dao;

import com.practicecode.onetoonerelation.entity.Instructor;
import com.practicecode.onetoonerelation.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{
    // define fields for entity manager
    private EntityManager entityManager;

    // injecting entity manager using constructor injection

    @Autowired
    public AppDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public List<Instructor> findAll() {
        TypedQuery<Instructor> theQuery = entityManager.createQuery("FROM Instructor", Instructor.class);
        List<Instructor> instructors = theQuery.getResultList();

        return instructors;
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void delete(int theId) {
        Instructor tempInstructor = entityManager.find(Instructor.class, theId);
        entityManager.remove(tempInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetail(int theId) {
        InstructorDetail tempInstructorDetail = entityManager.find(InstructorDetail.class, theId);
        entityManager.remove(tempInstructorDetail);
    }

    @Override
    public List<InstructorDetail> findAllDetail() {
        TypedQuery<InstructorDetail> theQuery = entityManager.createQuery("FROM InstructorDetail", InstructorDetail.class);
        List<InstructorDetail> details = theQuery.getResultList();

        return details;
    }
}
