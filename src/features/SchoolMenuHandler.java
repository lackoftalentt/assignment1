package features;

import entities.School;
import entities.Student;
import entities.Teacher;

import java.util.ArrayList;
import java.util.Scanner;

public class SchoolMenuHandler {
    private final Scanner scanner;
    private final School school;

    public SchoolMenuHandler(Scanner scanner, School school) {
        this.scanner = scanner;
        this.school = school;
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Выход.");
                break;
            }

            handleChoice(choice);
        }
    }

    private void displayMenu() {
        System.out.println("\n=== School Management System ===");
        System.out.println("1) Добавить студента");
        System.out.println("2) Добавить преподавателя");
        System.out.println("3) Назначить студента преподавателю (поток)");
        System.out.println("4) Поставить оценку");
        System.out.println("5) Показать студентов школы");
        System.out.println("6) Показать преподавателей школы");
        System.out.println("7) Сравнить двух студентов по ID (equals)");
        System.out.println("8) Показать оценки студента (все предметы)");
        System.out.println("9) Показать оценки студента по предмету");
        System.out.println("10) Показать оценки потока учителя (по его предмету)");
        System.out.println("11) Фильтр: студенты с высокой оценкой по предмету");
        System.out.println("12) Фильтр: студенты с низкой оценкой по предмету");
        System.out.println("13) Сортировка: студенты по среднему баллу по предмету");
        System.out.println("14) Сортировка: студенты по количеству оценок по предмету");
        System.out.println("0) Выход");
        System.out.print("Выбор: ");
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1 -> addStudent();
            case 2 -> addTeacher();
            case 3 -> assignStudentToTeacher();
            case 4 -> assignGrade();
            case 5 -> showAllStudents();
            case 6 -> showAllTeachers();
            case 7 -> compareStudents();
            case 8 -> showAllStudentGrades();
            case 9 -> showStudentGradesBySubject();
            case 10 -> showTeacherStreamGrades();

            default -> System.out.println("Неверный выбор.");
        }
    }

    private void addStudent() {
        System.out.print("studentId: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("name: ");
        String name = scanner.nextLine();

        Student st = new Student(studentId, school.getSchoolId(), name);
        school.addStudentToSchool(st);
        System.out.println("Студент добавлен успешно.");
    }

    private void addTeacher() {
        System.out.print("teacherId: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("subject: ");
        String subject = scanner.nextLine();

        Teacher t = new Teacher(teacherId, school.getSchoolId(), name, subject);
        school.addTeacherToSchool(t);
        System.out.println("Преподаватель добавлен успешно.");
    }

    private void assignStudentToTeacher() {
        ArrayList<Student> students = school.getSchoolStudents();
        System.out.println(students);
        System.out.print("studentId: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Teacher> teachers = school.getSchoolTeachers();
        System.out.println(teachers);
        System.out.print("teacherId: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        Student st = school.findStudentById(studentId);
        Teacher t = school.findTeacherById(teacherId);

        if (st == null) {
            System.out.println("Студент не найден.");
            return;
        }
        if (t == null) {
            System.out.println("Преподаватель не найден.");
            return;
        }

        school.assignStudentToTeacher(st, t);
        System.out.println("Студент назначен преподавателю успешно.");
    }

    private void assignGrade() {
        ArrayList<Teacher> teachers = school.getSchoolTeachers();
        System.out.println(teachers);
        System.out.print("teacherId: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Student> students = school.getSchoolStudents();
        System.out.println(students);
        System.out.print("studentId: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("grade: ");
        int grade = scanner.nextInt();
        scanner.nextLine();

        Teacher t = school.findTeacherById(teacherId);
        Student st = school.findStudentById(studentId);

        if (t == null || st == null) {
            System.out.println("Ошибка: студент или преподаватель не найден.");
            return;
        }

        t.assignGrade(st, grade);
        System.out.println("Оценка поставлена успешно.");
    }

    private void showAllStudents() {
        System.out.println(school.getSchoolStudents());
    }

    private void showAllTeachers() {
        System.out.println(school.getSchoolTeachers());
    }

    private void compareStudents() {
        ArrayList<Student> students = school.getSchoolStudents();
        System.out.println(students);

        System.out.print("studentId #1: ");
        int id1 = scanner.nextInt();
        scanner.nextLine();

        System.out.print("studentId #2: ");
        int id2 = scanner.nextInt();
        scanner.nextLine();

        Student s1 = school.findStudentById(id1);
        Student s2 = school.findStudentById(id2);

        if (s1 == null || s2 == null) {
            System.out.println("Ошибка: один из студентов не найден.");
            return;
        }

        System.out.println("s1.equals(s2) = " + s1.equals(s2));
    }

    private void showAllStudentGrades() {
        ArrayList<Student> students = school.getSchoolStudents();
        System.out.println(students);
        System.out.print("studentId: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        Student st = school.findStudentById(studentId);
        if (st == null) {
            System.out.println("Студент не найден.");
            return;
        }
        st.printAllGrades();
    }

    private void showStudentGradesBySubject() {
        ArrayList<Student> students = school.getSchoolStudents();
        System.out.println(students);
        System.out.print("studentId: ");
        int studentId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("subject: ");
        String subject = scanner.nextLine();

        Student st = school.findStudentById(studentId);
        if (st == null) {
            System.out.println("Студент не найден.");
            return;
        }

        System.out.println(subject + " -> " + st.getGradesBySubject(subject));
    }

    private void showTeacherStreamGrades() {
        ArrayList<Teacher> teachers = school.getSchoolTeachers();
        System.out.println(teachers);
        System.out.print("teacherId: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        Teacher t = school.findTeacherById(teacherId);
        if (t == null) {
            System.out.println("Преподаватель не найден.");
            return;
        }

        t.printStreamGrades();
    }

    private void filterLowGrades() {
        System.out.print("Введите предмет: ");
        String subject = scanner.nextLine();
        System.out.print("Максимальная оценка: ");
        int maxGrade = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Student> result = school.getStudentsWithLowGrades(subject, maxGrade);
        System.out.println("Студенты с оценкой <= " + maxGrade + " по " + subject + ":");
        System.out.println(result);
    }

    private void sortBySubjectAverage() {
        System.out.print("Введите предмет: ");
        String subject = scanner.nextLine();

        ArrayList<Student> result = school.getStudentsSortedBySubjectAverage(subject);
        System.out.println("Студенты, отсортированные по средней оценке по " + subject + ":");
        System.out.println(result);
    }

    private void sortBySubjectGradesCount() {
        System.out.print("Введите предмет: ");
        String subject = scanner.nextLine();

        ArrayList<Student> result = school.getStudentsSortedBySubjectGradesCount(subject);
        System.out.println("Студенты, отсортированные по количеству оценок по " + subject + ":");
        System.out.println(result);
    }
}
