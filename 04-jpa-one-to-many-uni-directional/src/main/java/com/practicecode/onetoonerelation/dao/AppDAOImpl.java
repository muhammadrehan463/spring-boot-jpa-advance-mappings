package com.practicecode.onetoonerelation.dao;

import com.practicecode.onetoonerelation.entity.Course;
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

    @Override
    public List<Course> findCourseByInstructorId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("from Course where instructorId.id = :data", Course.class);
        query.setParameter("data", theId);

        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        TypedQuery<Instructor> query = entityManager.createQuery("SELECT i FROM Instructor i JOIN FETCH i.courses JOIN FETCH i.instructorDetail WHERE i.id = :data", Instructor.class);
        query.setParameter("data", theId);
        Instructor temp = query.getSingleResult();
        return temp;
    }

    @Override
    @Transactional
    public void update(Instructor tenpInstructor) {
        entityManager.merge(tenpInstructor);
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void updateCourse(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    @Transactional
    public void deleteInstructor(int theId) {
        Instructor temp = entityManager.find(Instructor.class, theId);
        List<Course> courses = temp.getCourses();

        for(Course tempCourses:  courses){
            tempCourses.setInstructorId(null);
        }
        entityManager.remove(temp);
    }

    @Override
    @Transactional
    public void deleteCourse(int theId) {
        Course temp = entityManager.find(Course.class, theId);
        entityManager.remove(temp);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
        TypedQuery<Course> query = entityManager.createQuery("select c from Course c join fetch c.reviews where c.id =: data", Course.class);
        query.setParameter("data", theId);

        Course course = query.getSingleResult();

        return course;
    }
}
