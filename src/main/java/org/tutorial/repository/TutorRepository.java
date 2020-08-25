package org.tutorial.repository;

import org.tutorial.model.Tutor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TutorRepository {

    private EntityManager entityManager;
    private EntityManagerFactory emf;

    public TutorRepository() {
        this.emf = Persistence.createEntityManagerFactory("student_pu");
        this.entityManager = this.emf.createEntityManager();
    }

    public TutorRepository(String pu) {
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
    }

    public Tutor add(Tutor tutor) {
        entityManager.getTransaction().begin();
        entityManager.persist(tutor);
        entityManager.getTransaction().commit();
        return tutor;
    }

    public Tutor find(Long id) {
        return entityManager.find(Tutor.class, id);
    }

    public Tutor update(Tutor tutor) {
        Tutor tutorToUpdate = find(tutor.getId());
        entityManager.getTransaction().begin();
        tutorToUpdate.setLastName(tutor.getLastName());
        tutorToUpdate.setFirstName(tutor.getFirstName());
        entityManager.getTransaction().commit();
        return tutorToUpdate;
    }

    public void delete(Tutor tutor) {
        entityManager.getTransaction().begin();
        entityManager.remove(tutor);
        entityManager.getTransaction().commit();
    }

    public void close() {
        this.entityManager.close();
        this.emf.close();
    }
}
