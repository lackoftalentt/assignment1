package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student extends SchoolPerson {
    private final Map<String, ArrayList<Integer>> gradesBySubject;
    private final ArrayList<Teacher> allowedTeachers;

    public Student(int studentId, int schoolId, String name) {
        super(studentId, schoolId, name);
        this.gradesBySubject = new HashMap<>();
        this.allowedTeachers = new ArrayList<>();
    }

    public boolean enrollTeacher(Teacher teacher) {
        if (teacher == null) return false;
        if (teacher.getSchoolId() != schoolId) return false;
        if (allowedTeachers.contains(teacher)) return false;

        allowedTeachers.add(teacher);
        return true;
    }

    private void addGradeInternal(String subject, int grade) {
        gradesBySubject
                .computeIfAbsent(subject, k -> new ArrayList<>())
                .add(grade);
    }

    public boolean receiveGradeFromTeacher(Teacher teacher, int grade) {
        if (teacher == null) return false;

        if (!allowedTeachers.contains(teacher)) {
            System.out.println(
                    "Ошибка: преподаватель не ведёт студента " +
                            name + " по предмету " + teacher.getSubject()
            );
            return false;
        }

        addGradeInternal(teacher.getSubject(), grade);
        return true;
    }

    public ArrayList<Integer> getGradesBySubject(String subject) {
        ArrayList<Integer> grades = gradesBySubject.get(subject);
        return grades == null ? new ArrayList<>() : new ArrayList<>(grades);
    }

    public void printAllGrades() {
        if (gradesBySubject.isEmpty()) {
            System.out.println("Оценок нет: " + name);
            return;
        }

        for (var entry : gradesBySubject.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

    public double getAverageBySubject(String subject) {
        ArrayList<Integer> grades = gradesBySubject.get(subject);
        if (grades == null || grades.isEmpty()) return 0.0;

        double sum = 0;
        for (int g : grades) sum += g;
        return sum / grades.size();
    }

    @Override
    public String getRole() {
        return "Student";
    }

    @Override
    public String toString() {
        return "Student{id=" + id +
                ", name='" + name + '\'' +
                ", schoolId=" + schoolId + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student other)) return false;
        return id == other.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }
}
