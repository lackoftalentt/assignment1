package features.menu;

import features.controller.SchoolController;

import java.util.Scanner;

public class SchoolMenuHandler {

    private final Scanner scanner;
    private final SchoolController controller;

    public SchoolMenuHandler(Scanner scanner, SchoolController controller) {
        this.scanner = scanner;
        this.controller = controller;
    }

    public void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("Выход из программы.");
                break;
            }

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addTeacher();
                case 3 -> assignStudentToTeacher();
                case 4 -> assignGrade();

                case 5 -> showStudents();
                case 6 -> showTeacherStreamStudents();
                case 7 -> showTeachers();

                case 8 -> showAllStudentGrades();
                case 9 -> showStudentGradesBySubject();
                case 10 -> showTeacherStreamGrades();

                case 11 -> filterHighGrades();
                case 12 -> filterLowGrades();
                case 13 -> sortBySubjectAverage();
                case 14 -> sortBySubjectGradesCount();

                default -> System.out.println("Неверный выбор.");
            }
        }
    }

    // ================== ADD ==================

    private void addStudent() {
        System.out.print("ID студента: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Имя студента: ");
        String name = scanner.nextLine();
        controller.addStudent(id, name);
    }

    private void addTeacher() {
        System.out.print("ID преподавателя: ");
        int id = scanner.nextInt(); scanner.nextLine();
        System.out.print("Имя преподавателя: ");
        String name = scanner.nextLine();
        System.out.print("Предмет: ");
        String subject = scanner.nextLine();
        controller.addTeacher(id, name, subject);
    }

    // ================== ASSIGN ==================

    private void assignStudentToTeacher() {
        showStudents();
        System.out.print("ID студента: ");
        int studentId = scanner.nextInt();

        showTeachers();
        System.out.print("ID преподавателя: ");
        int teacherId = scanner.nextInt();
        scanner.nextLine();

        controller.assignStudentToTeacher(studentId, teacherId);
    }

    private void assignGrade() {
        showTeachers();
        System.out.print("ID преподавателя: ");
        int teacherId = scanner.nextInt();

        showTeacherStudentsInternal(teacherId);
        System.out.print("ID студента: ");
        int studentId = scanner.nextInt();

        System.out.print("Оценка: ");
        int grade = scanner.nextInt();
        scanner.nextLine();

        controller.assignGrade(teacherId, studentId, grade);
    }

    // ================== SHOW ==================

    private void showStudents() {
        System.out.println("\n=== Студенты ===");
        controller.getAllStudents().forEach(s ->
                System.out.printf("ID: %d | Имя: %s%n", s.getId(), s.getName())
        );
    }

    private void showTeachers() {
        System.out.println("\n=== Преподаватели ===");
        controller.getAllTeachers().forEach(t ->
                System.out.printf("ID: %d | Имя: %s | Предмет: %s%n",
                        t.getId(), t.getName(), t.getSubject())
        );
    }

    private void showTeacherStreamStudents() {
        showTeachers();
        System.out.print("ID преподавателя: ");
        int teacherId = scanner.nextInt(); scanner.nextLine();
        showTeacherStudentsInternal(teacherId);
    }

    private void showTeacherStudentsInternal(int teacherId) {
        var students = controller.getTeacherStreamStudents(teacherId);

        if (students.isEmpty()) {
            System.out.println("У преподавателя нет студентов или он не найден.");
            return;
        }

        System.out.println("\n=== Студенты потока ===");
        students.forEach(s ->
                System.out.printf("ID: %d | Имя: %s%n", s.getId(), s.getName())
        );
    }

    private void showAllStudentGrades() {
        showStudents();
        System.out.print("ID студента: ");
        int studentId = scanner.nextInt(); scanner.nextLine();
        controller.showAllStudentGrades(studentId);
    }

    private void showStudentGradesBySubject() {
        showStudents();
        System.out.print("ID студента: ");
        int studentId = scanner.nextInt(); scanner.nextLine();
        System.out.print("Предмет: ");
        String subject = scanner.nextLine();
        controller.showStudentGradesBySubject(studentId, subject);
    }

    private void showTeacherStreamGrades() {
        showTeachers();
        System.out.print("ID преподавателя: ");
        int teacherId = scanner.nextInt(); scanner.nextLine();
        controller.showTeacherStreamGrades(teacherId);
    }

    // ================== FILTER / SORT ==================

    private void filterHighGrades() {
        System.out.print("Предмет: ");
        String subject = scanner.nextLine();
        System.out.print("Минимальная оценка: ");
        int min = scanner.nextInt(); scanner.nextLine();
        controller.filterHighGrades(subject, min);
    }

    private void filterLowGrades() {
        System.out.print("Предмет: ");
        String subject = scanner.nextLine();
        System.out.print("Максимальная оценка: ");
        int max = scanner.nextInt(); scanner.nextLine();
        controller.filterLowGrades(subject, max);
    }

    private void sortBySubjectAverage() {
        System.out.print("Предмет: ");
        String subject = scanner.nextLine();
        controller.sortBySubjectAverage(subject);
    }

    private void sortBySubjectGradesCount() {
        System.out.print("Предмет: ");
        String subject = scanner.nextLine();
        controller.sortBySubjectGradesCount(subject);
    }

    // ================== MENU ==================

    private void printMenu() {
        System.out.println("""
            ===== School Management System =====
            1) Добавить студента
            2) Добавить преподавателя
            3) Назначить студента преподавателю
            4) Поставить оценку

            5) Показать студентов
            6) Показать студентов в потоке преподавателя
            7) Показать преподавателей

            8) Показать все оценки студента
            9) Показать оценки студента по предмету
            10) Показать оценки студентов по потоку преподавателя

            11) Фильтр: студенты с высокой оценкой
            12) Фильтр: студенты с низкой оценкой
            13) Сортировка: по средней оценке
            14) Сортировка: по количеству оценок

            0) Выход
            ====================================
        """);
    }
}
