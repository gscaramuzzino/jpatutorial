package org.tutorial;

import org.tutorial.model.Student;

import java.util.List;

public class App {
  public static void main(String[] args) {

    Student student = new Student();
    student.setFirstName("Alan");
    student.setLastName("Red");

    StudentRepository repository = new StudentRepository();

    repository.add(student);

    System.out.println("Added student " + student.toString());

    repository.findFirstNames().forEach(System.out::println);

    repository.findLastNames().forEach(System.out::println);

    student = repository.find(student.getId());

    System.out.println("Found student " + student.toString());

    student = repository.findById(student.getId());

    System.out.println("Found student (JPQL) " + student.toString());

    student.setLastName("Green");

    repository.update(student);

    System.out.println("Updated student " + student.toString());

    student = repository.updateFirstNameById("Fred", student.getId());

    System.out.println("Updated first name (JPQL)" + student.toString());

    student = repository.updateLastNameById("Yellow", student.getId());

    System.out.println("Updated last name (JPQL)" + student.toString());

    List<Student> students = repository.findByFirstNameStartWith("Fr");

    students.forEach(System.out::println);

    students = repository.findByLastNameEndWith("ow");

    students.forEach(System.out::println);

    System.out.println("Number of student(s): "+  repository.count());

    students = repository.findSortingByFirstName();

    students.forEach(System.out::println);

    students = repository.findSortingById();

    students.forEach(System.out::println);

    repository.deleteById(student.getId());

    repository.delete(student);

    System.out.println("Deleted student " + student.toString());

    repository.close();

  }
}
