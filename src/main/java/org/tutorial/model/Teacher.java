package org.tutorial.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher extends Person{

    @ManyToOne
    private School school;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(
            name = "teachers_students",
            joinColumns =  { @JoinColumn(name = "teacher_id") },
            inverseJoinColumns = { @JoinColumn(name = "student_id") },
            uniqueConstraints = {
                    @UniqueConstraint(
                            columnNames = { "teacher_id", "student_id" }
                    )
            }

    )
    private Set<Student> students = new HashSet<>();

    public Teacher(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Teacher() {
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public void addStudent(Student student) {
        boolean added = students.add(student);
        if(added) {
            student.getTeachers().add(this);
        }
    }

    public void removeStudent(Student student) {
        boolean removed = students.remove(student);
        if(removed) {
            student.getTeachers().remove(this);
        }
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
