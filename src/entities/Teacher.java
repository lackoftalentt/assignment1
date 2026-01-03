package entities;

import java.util.ArrayList;

public class Teacher extends SchoolPerson {
    private String subject;
    private final ArrayList<Student> studentsInStream;

    public Teacher(int teacherId, int schoolId, String name, String subject) {
        super(teacherId, schoolId, name);
        this.subject = subject;
        this.studentsInStream = new ArrayList<>();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public boolean enrollStudent(Student student) {
        if (student == null) return false;
        if (student.getSchoolId() != schoolId) return false;
        if (studentsInStream.contains(student)) return false;

        studentsInStream.add(student);
        return true;
    }

    public ArrayList<Student> getStudentsInStream() {
        return new ArrayList<>(studentsInStream);
    }

    public boolean assignGrade(Student student, int grade) {
        if (student == null) return false;

        if (!studentsInStream.contains(student)) {
            System.out.println("Ошибка: студент не учится у этого преподавателя.");
            return false;
        }

        return student.receiveGradeFromTeacher(this, grade);
    }

    public void printStreamGrades() {
        System.out.println("Оценки потока по предмету: " + subject);

        for (Student s : studentsInStream) {
            System.out.println(
                    s.getId() + " " + s.getName() +
                            " -> " + s.getGradesBySubject(subject)
            );
        }
    }

    @Override
    public String getRole() {
        return "Teacher";
    }

    @Override
    public String toString() {
        return "Teacher{id=" + id +
                ", schoolId=" + schoolId +
                ", name='" + name + '\'' +
                ", subject='" + subject + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
