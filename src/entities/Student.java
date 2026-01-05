package entities;

import java.util.*;

public class Student extends SchoolPerson {

    private final Map<String, List<Integer>> gradesBySubject = new HashMap<>();
    private final List<Teacher> allowedTeachers = new ArrayList<>();

    public Student(int studentId, int schoolId, String name) {
        super(studentId, schoolId, name);
    }

    public void addAllowedTeacher(Teacher teacher) {
        allowedTeachers.add(teacher);
    }

    public boolean isTeacherAllowed(Teacher teacher) {
        return allowedTeachers.contains(teacher);
    }

    public void addGrade(String subject, int grade) {
        gradesBySubject
                .computeIfAbsent(subject, k -> new ArrayList<>())
                .add(grade);
    }

    public List<Integer> getGradesBySubject(String subject) {
        return new ArrayList<>(gradesBySubject.getOrDefault(subject, List.of()));
    }

    public Map<String, List<Integer>> getAllGrades() {
        return Collections.unmodifiableMap(gradesBySubject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student other)) return false;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", schoolId=" + schoolId +
                '}';
    }

    @Override
    public String getRole() {
        return "Student";
    }
}
