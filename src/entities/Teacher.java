package entities;

import java.util.ArrayList;
import java.util.List;

public class Teacher extends SchoolPerson {

    private String subject;
    private final List<Student> students = new ArrayList<>();

    public Teacher(int teacherId, int schoolId, String name, String subject) {
        super(teacherId, schoolId, name);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean hasStudent(Student student) {
        return students.contains(student);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher other)) return false;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subject='" + subject + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }

    @Override
    public String getRole() {
        return "Teacher";
    }
}
