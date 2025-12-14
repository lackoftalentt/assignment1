package entities;

import java.util.ArrayList;

public class School {
    private final int schoolId;
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    public School(int schoolId){
        this.schoolId = schoolId;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public int getSchoolId(){
        return schoolId;
    }

    public ArrayList<Teacher> getSchoolTeachers() {
        return new ArrayList<>(this.teachers);
    }

    public ArrayList<Student> getSchoolStudents(){
        return new ArrayList<>(this.students);
    }

    public boolean addStudentToSchool (Student student) {
        if (student == null) {
            System.out.println("Ошибка: студент не может быть null");
            return false;
        }

        if (student.getSchoolId() != this.schoolId) {
            System.out.println("Ошибка: студент принадлежит другой школе (ID: "
                    + student.getSchoolId() + ")");
            return false;
        }

        for (Student s : this.students) {
            if (s.getStudentId() == student.getStudentId()) {
                System.out.println("Ошибка: студент с ID "
                        + student.getStudentId() + " уже существует");
                return false;
            }
        }

        this.students.add(student);
        System.out.println("Студент " + student.getName() + " добавлен в школу под номером " + this.schoolId);
        return true;
    }

    public boolean addTeacherToSchool (Teacher teacher){
        if(teacher == null){
            System.out.println("Ошибка: преподаватель не может быть null");
            return false;
        }

        if(teacher.getSchoolId() != this.schoolId){
            System.out.println("Ошибка: преподаватель работает в школе под номером " + teacher.getSchoolId());
            return false;
        }

        for (Teacher t : this.teachers){
            if(t.getTeacherId() == teacher.getTeacherId()){
                System.out.println("Ошибка: преподаватель с ID" + teacher.getTeacherId() + " уже существует");
                return false;
            }
        }

        this.teachers.add(teacher);
        System.out.println("Преподаватель " + teacher.getName() + " добавлен в школу под номером " + this.schoolId);
        return true;
    }

    public boolean removeStudent(Student student){
        this.students.remove(student);
        return true;
    }

    public boolean removeTeacher(Teacher teacher){
        this.teachers.remove(teacher);
        return true;
    }

    public Student findStudentById(int studentId){
        for (Student s : this.students) {
            if (s.getStudentId() == studentId) {
                return s;
            }
        }
        return null;
    }

    public Teacher findTeacherById(int teacherId){
        for(Teacher t : this.teachers){
            if(t.getTeacherId() == teacherId){
                return t;
            }
        }
        return null;
    }

    public boolean assignStudentToTeacher(Student student, Teacher teacher) {
        if (student == null || teacher == null) {
            System.out.println("Ошибка: student/teacher не может быть null");
            return false;
        }

        if (student.getSchoolId() != this.schoolId) {
            System.out.println("Ошибка: студент из другой школы");
            return false;
        }
        if (teacher.getSchoolId() != this.schoolId) {
            System.out.println("Ошибка: преподаватель из другой школы");
            return false;
        }

        boolean okTeacher = teacher.enrollStudent(student);
        boolean okStudent = student.enrollTeacher(teacher);

        if (!okTeacher || !okStudent) {
            System.out.println("Ошибка: не удалось назначить студента преподавателю (возможно, уже назначен).");
            return false;
        }

        System.out.println("Назначено: " + student.getName() + " -> " + teacher.getName()
                + " (" + teacher.getSubject() + ")");
        return true;
    }

}
