package services;

import entities.Student;
import entities.Teacher;

public class TeacherService {

    public boolean enrollStudent(Teacher teacher, Student student) {
        if (teacher == null || student == null) return false;
        if (teacher.getSchoolId() != student.getSchoolId()) return false;
        if (teacher.hasStudent(student)) return false;

        teacher.addStudent(student);
        student.addAllowedTeacher(teacher);
        return true;
    }
}
