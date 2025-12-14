import entities.School;
import entities.Student;
import entities.Teacher;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        School school = new School(1);

        while (true) {
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
            System.out.println("0) Выход");
            System.out.print("Выбор: ");

            int step = sc.nextInt();
            sc.nextLine();

            switch (step) {
                case 1 -> {
                    System.out.print("studentId: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("name: ");
                    String name = sc.nextLine();

                    Student st = new Student(studentId, school.getSchoolId(), name);
                    school.addStudentToSchool(st);
                }
                case 2 -> {
                    System.out.print("teacherId: ");
                    int teacherId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("name: ");
                    String name = sc.nextLine();

                    System.out.print("subject: ");
                    String subject = sc.nextLine();

                    Teacher t = new Teacher(teacherId, school.getSchoolId(), name, subject);
                    school.addTeacherToSchool(t);
                }
                case 3 -> {
                    System.out.print("studentId: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("teacherId: ");
                    int teacherId = sc.nextInt();
                    sc.nextLine();

                    Student st = school.findStudentById(studentId);
                    Teacher t = school.findTeacherById(teacherId);

                    if (st == null) {
                        System.out.println("Студент не найден.");
                        break;
                    }
                    if (t == null) {
                        System.out.println("Преподаватель не найден.");
                        break;
                    }

                    school.assignStudentToTeacher(st, t);
                }
                case 4 -> {
                    System.out.print("teacherId: ");
                    int teacherId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("studentId: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("grade: ");
                    int grade = sc.nextInt();
                    sc.nextLine();

                    Teacher t = school.findTeacherById(teacherId);
                    Student st = school.findStudentById(studentId);

                    if (t == null || st == null) {
                        System.out.println("Ошибка: студент или преподаватель не найден.");
                        break;
                    }

                    t.assignGrade(st, grade);
                }
                case 5 -> System.out.println(school.getSchoolStudents());
                case 6 -> System.out.println(school.getSchoolTeachers());
                case 7 -> {
                    System.out.print("studentId #1: ");
                    int id1 = sc.nextInt();
                    sc.nextLine();

                    System.out.print("studentId #2: ");
                    int id2 = sc.nextInt();
                    sc.nextLine();

                    Student s1 = school.findStudentById(id1);
                    Student s2 = school.findStudentById(id2);

                    if (s1 == null || s2 == null) {
                        System.out.println("Ошибка: один из студентов не найден.");
                        break;
                    }

                    System.out.println("s1.equals(s2) = " + s1.equals(s2));
                }
                case 8 -> {
                    System.out.print("studentId: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    Student st = school.findStudentById(studentId);
                    if (st == null) {
                        System.out.println("Студент не найден.");
                        break;
                    }
                    st.printAllGrades();
                }
                case 9 -> {
                    System.out.print("studentId: ");
                    int studentId = sc.nextInt();
                    sc.nextLine();

                    System.out.print("subject: ");
                    String subject = sc.nextLine();

                    Student st = school.findStudentById(studentId);
                    if (st == null) {
                        System.out.println("Студент не найден.");
                        break;
                    }

                    System.out.println(subject + " -> " + st.getGradesBySubject(subject));
                }
                case 10 -> {
                    System.out.print("teacherId: ");
                    int teacherId = sc.nextInt();
                    sc.nextLine();

                    Teacher t = school.findTeacherById(teacherId);
                    if (t == null) {
                        System.out.println("Преподаватель не найден.");
                        break;
                    }

                    t.printStreamGrades();
                }
                case 0 -> {
                    System.out.println("Выход.");
                    return;
                }
                default -> System.out.println("Неверный выбор.");
            }
        }
    }
}
