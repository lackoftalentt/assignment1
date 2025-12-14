package entities;

import java.util.ArrayList;

public class Teacher {
    private String name;
    private String subject;

    private final int teacherId;
    private final int schoolId;

    private final ArrayList<Student> studentsInStream;

    public Teacher(int teacherId, int schoolId, String name, String subject) {
        this.teacherId = teacherId;
        this.schoolId = schoolId;
        this.name = name;
        this.subject = subject;
        this.studentsInStream = new ArrayList<>();
    }

    public int getTeacherId() { return teacherId; }
    public int getSchoolId() { return schoolId; }
    public String getName() { return name; }
    public String getSubject() { return subject; }

    public void setName(String name) { this.name = name; }
    public void setSubject(String subject) { this.subject = subject; }

    public boolean enrollStudent(Student student) {
        if (student == null) return false;
        if (student.getSchoolId() != this.schoolId) return false;

        if (studentsInStream.contains(student)) return false;

        studentsInStream.add(student);
        return true;
    }

    public boolean removeStudent(Student student) {
        if (student == null) return false;
        return studentsInStream.remove(student);
    }

    public boolean assignGrade(Student student, int grade) {
        if (student == null) return false;

        if (!studentsInStream.contains(student)) {
            System.out.println("Ошибка: студент не учится у этого преподавателя.");
            return false;
        }

        return student.receiveGradeFromTeacher(this, grade);
    }

    public ArrayList<Student> getStudentsInStream() {
        return new ArrayList<>(studentsInStream);
    }

    public void printStreamGrades() {
        System.out.println("Оценки потока по предмету: " + subject);

        for (Student s : studentsInStream) {
            ArrayList<Integer> grades = s.getGradesBySubject(this.subject);
            System.out.println(s.getStudentId() + " " + s.getName() + " -> " + grades);
        }
    }


    @Override
    public String toString() {
        return "Teacher{id=" + teacherId + ", schoolId=" + schoolId +
                ", name='" + name + "', subject='" + subject + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher other)) return false;
        return this.teacherId == other.teacherId;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(teacherId);
    }
}
