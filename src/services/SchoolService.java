package services;

import entities.Student;
import entities.School;
import entities.Teacher;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SchoolService {

    public enum AddResult {
        SUCCESS,
        DUPLICATE_ID,
        WRONG_SCHOOL,
        NULL_ENTITY
    }

    public AddResult addStudent(School school, Student student) {
        if (student == null) return AddResult.NULL_ENTITY;
        if (student.getSchoolId() != school.getSchoolId())
            return AddResult.WRONG_SCHOOL;

        boolean exists = school.getStudents().stream()
                .anyMatch(s -> s.getId() == student.getId());

        if (exists) return AddResult.DUPLICATE_ID;

        school.addStudent(student);
        return AddResult.SUCCESS;
    }


    public AddResult addTeacher(School school, Teacher teacher) {
        if (teacher == null) return AddResult.NULL_ENTITY;
        if (teacher.getSchoolId() != school.getSchoolId())
            return AddResult.WRONG_SCHOOL;

        boolean exists = school.getTeachers().stream()
                .anyMatch(t -> t.getId() == teacher.getId());

        if (exists) return AddResult.DUPLICATE_ID;

        school.addTeacher(teacher);
        return AddResult.SUCCESS;
    }

    public Student findStudentById(School school, int id) {
        return school.getStudents().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public Teacher findTeacherById(School school, int id) {
        return school.getTeachers().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Student> filterStudentsWithGradeAbove(
            School school, String subject, int minGrade
    ) {
        List<Student> result = new ArrayList<>();

        for (Student s : school.getStudents()) {
            for (int g : s.getGradesBySubject(subject)) {
                if (g >= minGrade) {
                    result.add(s);
                    break;
                }
            }
        }
        return result;
    }

    public List<Student> filterStudentsWithGradeBelow(
            School school, String subject, int maxGrade
    ) {
        List<Student> result = new ArrayList<>();

        for (Student s : school.getStudents()) {
            for (int g : s.getGradesBySubject(subject)) {
                if (g <= maxGrade) {
                    result.add(s);
                    break;
                }
            }
        }
        return result;
    }

    public List<Student> sortByAverageGrade(
            School school,
            String subject,
            GradeService gradeService
    ) {
        return school.getStudents().stream()
                .sorted(Comparator.comparingDouble(
                        s -> -gradeService.getAverageBySubject(s, subject)
                ))
                .toList();
    }

    public List<Student> sortByGradesCount(
            School school,
            String subject
    ) {
        return school.getStudents().stream()
                .sorted((a, b) ->
                        Integer.compare(
                                b.getGradesBySubject(subject).size(),
                                a.getGradesBySubject(subject).size()
                        )
                )
                .toList();
    }
}
