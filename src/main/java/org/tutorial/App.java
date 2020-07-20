package org.tutorial;

import org.tutorial.model.Student;

public class App {
  public static void main(String[] args) {

    Student student = new Student();
    student.setFirstName("Alan");
    student.setLastName("Red");

    StudentRepository repository = new StudentRepository();

    repository.add(student);

    System.out.println("Added student " + student.toString());

    student = repository.find(student.getId());

    System.out.println("Found student " + student.toString());

    student.setLastName("Green");

    repository.update(student);

    System.out.println("Updated student " + student.toString());

    repository.delete(student);

    System.out.println("Deleted student " + student.toString());

  }
}
