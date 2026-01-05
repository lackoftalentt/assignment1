package features.controller;

import entities.*;
import services.*;

import java.util.List;

public class SchoolController {

    private final School school;
    private final SchoolService schoolService;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    public SchoolController(School school) {
        this.school = school;
        this.schoolService = new SchoolService();
        this.teacherService = new TeacherService();
        this.gradeService = new GradeService();
    }

    public void addStudent(int id, String name) {
        Student s = new Student(id, school.getSchoolId(), name);
        SchoolService.AddResult result = schoolService.addStudent(school, s);

        switch (result) {
            case SUCCESS ->
                    System.out.println("Студент успешно добавлен.");
            case DUPLICATE_ID ->
                    System.out.println("Ошибка: студент с таким ID уже существует.");
            case WRONG_SCHOOL ->
                    System.out.println("Ошибка: неверная школа.");
            case NULL_ENTITY ->
                    System.out.println("Ошибка: некорректные данные.");
        }
    }


    public void addTeacher(int id, String name, String subject) {
        Teacher t = new Teacher(id, school.getSchoolId(), name, subject);
        SchoolService.AddResult result = schoolService.addTeacher(school, t);

        switch (result) {
            case SUCCESS ->
                    System.out.println("Студент успешно добавлен.");
            case DUPLICATE_ID ->
                    System.out.println("Ошибка: студент с таким ID уже существует.");
            case WRONG_SCHOOL ->
                    System.out.println("Ошибка: неверная школа.");
            case NULL_ENTITY ->
                    System.out.println("Ошибка: некорректные данные.");
        }
    }

    public void assignStudentToTeacher(int studentId, int teacherId) {
        Student s = schoolService.findStudentById(school, studentId);
        Teacher t = schoolService.findTeacherById(school, teacherId);

        if (s == null || t == null) {
            System.out.println("Ошибка: студент или преподаватель не найден");
            return;
        }
        teacherService.enrollStudent(t, s);
    }

    public void assignGrade(int teacherId, int studentId, int grade) {
        Student s = schoolService.findStudentById(school, studentId);
        Teacher t = schoolService.findTeacherById(school, teacherId);

        if (s == null || t == null) {
            System.out.println("Ошибка: студент или преподаватель не найден");
            return;
        }
        gradeService.addGrade(s, t, grade);
    }

    public void printStudents() {
        school.getStudents().forEach(System.out::println);
    }

    public void printTeachers() {
        school.getTeachers().forEach(System.out::println);
    }

    public void printTeacherStreamStudents(int teacherId) {
        Teacher t = schoolService.findTeacherById(school, teacherId);
        if (t == null) {
            System.out.println("Преподаватель не найден");
            return;
        }

        System.out.println("Оценки потока по предмету: " + t.getSubject());

        for (Student s : t.getStudents()) {
            System.out.println(
                    s.getId() + " " + s.getName()
            );
        }
    }

    public void showStudentGradesBySubject(int studentId, String subject) {
        Student s = schoolService.findStudentById(school, studentId);
        if (s == null) {
            System.out.println("Студент не найден");
            return;
        }

        System.out.println(
                "Оценки " + s.getName() + " по " + subject + ": " +
                        gradeService.getGradesBySubject(s, subject)
        );
    }

    public void showAllStudentGrades(int studentId) {
        Student s = schoolService.findStudentById(school, studentId);
        if (s == null) {
            System.out.println("Студент не найден");
            return;
        }

        var grades = gradeService.getAllGrades(s);
        if (grades.isEmpty()) {
            System.out.println("Оценок нет");
            return;
        }

        grades.forEach((subject, list) ->
                System.out.println(s.getName() + " -> " + subject + " -> " + list)
        );
    }

    public void showTeacherStreamGrades(int teacherId) {
        Teacher t = schoolService.findTeacherById(school, teacherId);
        if (t == null) {
            System.out.println("Преподаватель не найден");
            return;
        }

        System.out.println("Оценки потока по предмету: " + t.getSubject());

        for (Student s : t.getStudents()) {
            System.out.println(
                    s.getId() + " " + s.getName() +
                            " -> " +
                            gradeService.getStudentGradesForTeacher(s, t)
            );
        }
    }

    public List<Student> getAllStudents() {
        return school.getStudents();
    }

    public List<Teacher> getAllTeachers() {
        return school.getTeachers();
    }

    public List<Student> getTeacherStreamStudents(int teacherId) {
        Teacher t = schoolService.findTeacherById(school, teacherId);
        if (t == null) return List.of();
        return t.getStudents();
    }

    public void filterHighGrades(String subject, int minGrade) {
        var result = schoolService.filterStudentsWithGradeAbove(
                school, subject, minGrade
        );

        System.out.println("Студенты с оценкой >= " + minGrade + " по " + subject);
        result.forEach(s ->
                System.out.printf("ID: %d | Имя: %s%n", s.getId(), s.getName())
        );
    }

    public void filterLowGrades(String subject, int maxGrade) {
        var result = schoolService.filterStudentsWithGradeBelow(
                school, subject, maxGrade
        );

        System.out.println("Студенты с оценкой <= " + maxGrade + " по " + subject);
        result.forEach(s ->
                System.out.printf("ID: %d | Имя: %s%n", s.getId(), s.getName())
        );
    }

    public void sortBySubjectAverage(String subject) {
        var result = schoolService.sortByAverageGrade(
                school, subject, gradeService
        );

        System.out.println("Сортировка по средней оценке по " + subject);
        result.forEach(System.out::println);
    }

    public void sortBySubjectGradesCount(String subject) {
        var result = schoolService.sortByGradesCount(
                school, subject
        );

        System.out.println("Сортировка по количеству оценок по " + subject);
        result.forEach(System.out::println);
    }
}
