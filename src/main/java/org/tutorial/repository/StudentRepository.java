package org.tutorial.repository;

import org.tutorial.model.School;
import org.tutorial.model.Student;
import org.tutorial.model.Teacher;
import org.tutorial.model.Tutor;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.util.Arrays;
import java.util.List;

public class StudentRepository {

  private EntityManager entityManager;
  private EntityManagerFactory emf;

  public StudentRepository() {
    this.emf = Persistence.createEntityManagerFactory("student_pu");
    this.entityManager = this.emf.createEntityManager();
  }

  public StudentRepository(String pu) {
    this.emf = Persistence.createEntityManagerFactory(pu);
    this.entityManager = this.emf.createEntityManager();
  }

  public Student add(Student student) {
    entityManager.getTransaction().begin();
    entityManager.persist(student);
    entityManager.getTransaction().commit();
    return student;
  }

  public Tutor addTutor(Long id, Tutor tutor) {
    entityManager.getTransaction().begin();
    Student student = find(id);
    student.setTutor(tutor);
    entityManager.getTransaction().commit();
    return tutor;
  }

  public Student find(Long id) {
    return entityManager.find(Student.class, id);
  }

  public Student findById(Long id) {
    Query query = entityManager.createNamedQuery("find student by id");
    query.setParameter("id", id);
    return (Student) query.getSingleResult();
  }

  public Student updateFirstNameById(String firstName, Long id) {
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("Update Student set firstName = '"+ firstName + "' where id = " + id );
    query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManager.clear();
    return findById(id);
  }

  public Student updateLastNameById(String lastName, Long id) {
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("Update Student set lastName = '"+ lastName + "' where id = " + id );
    query.executeUpdate();
    entityManager.getTransaction().commit();
    entityManager.clear();
    return findById(id);
  }

  public void deleteById(Long id) {
    entityManager.getTransaction().begin();
    Query query = entityManager.createQuery("Delete from Student where id =" + id);
    query.executeUpdate();
    entityManager.getTransaction().commit();
  }

  public List<Student> findByFirstNameStartWith(String keyword) {
    Query query = entityManager.createQuery("Select s from Student s where s.firstName like '" + keyword + "%'");
    return query.getResultList();
  }

  public List<Student> findByLastNameEndWith(String keyword) {
    Query query = entityManager.createQuery("Select s from Student s where s.lastName like '%" + keyword + "'");
    return query.getResultList();
  }

  public List<Student> findSortingByFirstName() {
    Query query = entityManager.createQuery("Select s from Student s order by s.firstName desc");
    return query.getResultList();
  }

  public List<Student> findSortingById() {
    Query query = entityManager.createQuery("Select s from Student s order by s.id desc");
    return query.getResultList();
  }

  public Long count() {
    Query query = entityManager.createQuery("Select count(s) from Student s");
    return (Long) query.getSingleResult();
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

  public List<String> findFirstNames() {
    Query query = entityManager.createQuery("Select s.firstName from Student s");
    return query.getResultList();
  }

  public List<String> findLastNames() {
    Query query = entityManager.createQuery("Select s.lastName from Student s");
    return query.getResultList();
  }

  public void addTeacher(Long id, Teacher teacher) {
    entityManager.getTransaction().begin();
    Student student = find(id);
    if(student != null) {
     student.getTeachers().add(teacher);
    }
    entityManager.getTransaction().commit();
  }

  public void removeTeacher(Long id, Teacher teacher) {
    Student student = find(id);
    if(student != null) {
      student.getTeachers().remove(teacher);
    }
  }

  public List<Student> getStudentWithCriteriaBuilder() {
    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);

    Root<Student> studentRoot = criteriaQuery.from(Student.class);

    criteriaQuery.select(studentRoot.get("firstName"));
    criteriaQuery.distinct(true);
    criteriaQuery.orderBy(criteriaBuilder.desc(studentRoot.get("firstName")));

    CriteriaQuery<Student> select = criteriaQuery.select(studentRoot);
    TypedQuery<Student> query = entityManager.createQuery(select);

    return query.getResultList();
  }

  public List<Student> getStudentsWithWHEREFirstName() {

    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
    CriteriaQuery<Student> query = criteriaBuilder.createQuery(Student.class);

    Root<Student> from = query.from(Student.class);

    List firstNameList = Arrays.asList(new String[]{"SFirstName_1","SFirstName_2"});

    Expression<String> exp = from.get("firstName");
    Predicate in = exp.in(firstNameList);
    query.where(in);
    query.groupBy(from.get("lastName"));

    CriteriaQuery<Student> select = query.select(from);
    TypedQuery<Student> query1 = entityManager.createQuery(select);

    return query1.getResultList();
  }


  public void close() {
    this.entityManager.close();
    this.emf.close();
  }
}
