package org.tutorial;

import org.tutorial.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CreateStudent {
  public static void main(String[] args) {
      EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("student_pu");
      EntityManager entityManager = entityManagerFactory.createEntityManager();
      EntityTransaction entityTransaction = entityManager.getTransaction();

      entityTransaction.begin();

      Student student = new Student();
      student.setFirstName("John");
      student.setLastName("Bell");

      Student student1 = new Student();
      student1.setFirstName("Alan");
      student1.setLastName("Conrad");

      entityManager.persist(student);

      entityTransaction.commit();

      entityManager.close();
      entityManagerFactory.close();
  }
}
