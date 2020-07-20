package org.tutorial;

import org.tutorial.model.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class UpdateStudent {

  public static void main(String[] args) {
    EntityManagerFactory entityManagerFactory =
        Persistence.createEntityManagerFactory("student_pu");
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    EntityTransaction entityTransaction = entityManager.getTransaction();

    Student student = entityManager.find(Student.class, 1L);
    System.out.println("Before update " + student.toString());

    entityTransaction.begin();

    student.setFirstName("Alan");
    student.setLastName("Bold");

    entityTransaction.commit();

    System.out.println("After update " + student.toString());

    entityManager.close();
    entityManagerFactory.close();
  }
}
