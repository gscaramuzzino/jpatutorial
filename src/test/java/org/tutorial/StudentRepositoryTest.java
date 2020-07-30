package org.tutorial;

import org.junit.*;
import org.tutorial.model.Student;

import static org.junit.Assert.*;


public class StudentRepositoryTest {

  private static StudentRepository repository;

  @BeforeClass
  public static void beforeClass() throws Exception {
    repository = new StudentRepository("student_pu_test");
  }

  @AfterClass
  public static void afterClass() throws Exception {
    repository.close();
  }

  @Before
  public void setUp() throws Exception {}

  @After
  public void tearDown() throws Exception {}

  @Test
  public void add() {
    Student student = new Student();
    student.setLastName("Red");
    student.setFirstName("Alan");

    repository.add(student);

    assertNotNull(student.getId());
    assertTrue(student.getId() != null);
  }

  @Test
  public void find() {
    Student student = new Student();
    student.setLastName("Red");
    student.setFirstName("Alan");

    repository.add(student);

    student = repository.find(student.getId());

    assertNotNull(student);
    assertNotNull(student.getId());
    assertEquals("Red", student.getLastName());
  }

  @Test
  public void update() {
    Student student = new Student();
    student.setLastName("Red");
    student.setFirstName("Alan");

    student = repository.add(student);

    student.setLastName("Green");
    student = repository.update(student);

    assertNotNull(student);
    assertEquals("Green", student.getLastName());
    assertEquals("Alan", student.getFirstName());

  }

  @Test
  public void delete() {
    Student student = new Student();
    student.setLastName("Red");
    student.setFirstName("Alan");

    student = repository.add(student);

    repository.delete(student);

    student = repository.find(student.getId());

    assertNull(student);

  }


}