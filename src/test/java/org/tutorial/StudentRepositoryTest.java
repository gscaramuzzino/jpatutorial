package org.tutorial;

import org.junit.*;
import org.tutorial.model.Student;
import org.tutorial.repository.StudentRepository;

import static org.junit.Assert.*;


public class StudentRepositoryTest {

  private static StudentRepository repository;

  @BeforeClass
  public static void beforeClass() {
    repository = new StudentRepository("student_pu_test");
  }

  @AfterClass
  public static void afterClass() {
    repository.close();
  }

  @Before
  public void setUp() {}

  @After
  public void tearDown() {}

  @Test
  public void add() {
    Student student = new Student("Red", "Alan");
    student.setLastName("Red");
    student.setFirstName("Alan");

    repository.add(student);

    assertNotNull(student.getId());
    assertNotNull(student.getId());
  }

  @Test
  public void find() {
    Student student = new Student("Red", "Alan");

    repository.add(student);

    student = repository.find(student.getId());

    assertNotNull(student);
    assertNotNull(student.getId());
    assertEquals("Alan", student.getLastName());
  }

  @Test
  public void update() {
    Student student = new Student("Red", "Alan");

    student = repository.add(student);

    student.setLastName("Green");
    student = repository.update(student);

    assertNotNull(student);
    assertEquals("Green", student.getLastName());
    assertEquals("Red", student.getFirstName());

  }

  @Test
  public void delete() {
    Student student = new Student("Red", "Alan");

    student = repository.add(student);

    repository.delete(student);

    student = repository.find(student.getId());

    assertNull(student);

  }


}