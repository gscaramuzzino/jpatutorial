package org.tutorial;

import org.tutorial.model.School;
import org.tutorial.model.Student;
import org.tutorial.model.Teacher;
import org.tutorial.model.Tutor;
import org.tutorial.repository.SchoolRepository;
import org.tutorial.repository.StudentRepository;
import org.tutorial.repository.TeacherRepository;
import org.tutorial.repository.TutorRepository;

import java.util.List;


public class App {
  public static void main(String[] args) {

    Student student = new Student("Alan", "Red");

    //REPOSITORIES

    StudentRepository studentRepository = new StudentRepository();

    SchoolRepository schoolRepository = new SchoolRepository();

    TutorRepository tutorRepository = new TutorRepository();

    TeacherRepository teacherRepository = new TeacherRepository();

    //ADD STUDENT

    studentRepository.add(student);

    System.out.println("Added student " + student.toString());

    //ADD TUTOR

    Tutor tutor = new Tutor("FirstName_tutor_1", "LastName_tutor_2");

    tutorRepository.add(tutor);

    System.out.println("Added tutor " + tutor.toString());

    studentRepository.addTutor(student.getId(), tutor);

    System.out.println("Student with tutor " + student.toString());

    System.out.println("Found student with school " + student.toString());

    //ADD SCHOOL

    School school = new School("School_1","City_1");

    schoolRepository.add(school);

    System.out.println("Added school " + school.toString());

    school = schoolRepository.find(school.getId());

    school.getStudents().forEach(System.out::println);

    school = schoolRepository.find(school.getId());

    school.getStudents().forEach(System.out::println);

    //ADD TEACHER

    Teacher teacher = new Teacher("firstname_1","lastname_1");

    teacher.addStudent(new Student("SFirstName_1", "SLastname_1"));
    teacher.addStudent(new Student("SFirstName_2", "SLastname_2"));

    teacherRepository.add(teacher);

    //Persistence Operations and JPQL

    studentRepository.findFirstNames().forEach(System.out::println);

    studentRepository.findLastNames().forEach(System.out::println);

    student = studentRepository.find(student.getId());

    System.out.println("Found student " + student.toString());

    student = studentRepository.findById(student.getId());

    System.out.println("Found student (JPQL) " + student.toString());

    student.setLastName("Green");

    studentRepository.update(student);

    System.out.println("Updated student " + student.toString());

    student = studentRepository.updateFirstNameById("Fred", student.getId());

    System.out.println("Updated first name (JPQL)" + student.toString());

    student = studentRepository.updateLastNameById("Yellow", student.getId());

    System.out.println("Updated last name (JPQL)" + student.toString());

    List<Student> students = studentRepository.findByFirstNameStartWith("Fr");

    students.forEach(System.out::println);

    students = studentRepository.findByLastNameEndWith("ow");

    students.forEach(System.out::println);

    System.out.println("Number of student(s): "+  studentRepository.count());

    students = studentRepository.findSortingByFirstName();

    students.forEach(System.out::println);

    students = studentRepository.findSortingById();

    students.forEach(System.out::println);

    //repository.delete(student);

    //System.out.println("Deleted student " + student.toString());


    //CRITERIA BUILDER

    List<Student> studentList = studentRepository.getStudentWithCriteriaBuilder();

    System.out.println("Print Students (Criteria Builder): ");
    studentList.forEach(System.out::println);

    List<Student> studentListWhere = studentRepository.getStudentsWithWHEREFirstName();

    System.out.println("Print Students (Criteria Builder with WHERE and GROUP BY): ");
    studentListWhere.forEach(System.out::println);

    studentRepository.close();

  }
}
