package services;

import entities.Student;
import entities.Teacher;

import java.util.List;
import java.util.Map;

public class GradeService {

    public boolean addGrade(Student student, Teacher teacher, int grade) {
        if (student == null || teacher == null) return false;
        if (!student.isTeacherAllowed(teacher)) return false;

        student.addGrade(teacher.getSubject(), grade);
        return true;
    }

    public double getAverageBySubject(Student student, String subject) {
        List<Integer> grades = student.getGradesBySubject(subject);
        if (grades.isEmpty()) return 0.0;

        return grades.stream()
                .mapToInt(Integer::intValue)
                .average()
                .orElse(0.0);
    }

    public List<Integer> getGradesBySubject(Student student, String subject) {
        if (student == null || subject == null) return List.of();
        return student.getGradesBySubject(subject);
    }

    public Map<String, List<Integer>> getAllGrades(Student student) {
        if (student == null) return Map.of();
        return student.getAllGrades();
    }

    public List<Integer> getStudentGradesForTeacher(
            Student student,
            Teacher teacher
    ) {
        if (student == null || teacher == null) return List.of();
        return student.getGradesBySubject(teacher.getSubject());
    }
}
