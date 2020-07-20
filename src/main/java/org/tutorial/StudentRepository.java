package org.tutorial;

import org.tutorial.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class StudentRepository {

  private EntityManager entityManager;
  private EntityManagerFactory emf;

  public StudentRepository() {
    this.emf = Persistence.createEntityManagerFactory("student_pu");
    this.entityManager = this.emf.createEntityManager();
  }

  public Student add(Student student) {
    entityManager.getTransaction().begin();
    entityManager.persist(student);
    entityManager.getTransaction().commit();
    return student;
  }

  public Student find(Long id) {
    return entityManager.find(Student.class, id);
  }

  public Student update(Student student) {
    Student studentToUpdate = find(student.getId());
    entityManager.getTransaction().begin();
    studentToUpdate.setFirstName(student.getFirstName());
    studentToUpdate.setLastName(student.getLastName());
    entityManager.getTransaction().commit();
    return studentToUpdate;
  }

  public void delete(Student student) {
    entityManager.getTransaction().begin();
    entityManager.remove(student);
    entityManager.getTransaction().commit();
  }

  public void close() {
    this.entityManager.close();
    this.emf.close();
  }
}
