package services;

import entities.Student;
import entities.Teacher;

public class StudentService {

    public boolean enrollTeacher(Student student, Teacher teacher) {
        if (student == null || teacher == null) return false;
        if (teacher.getSchoolId() != student.getSchoolId()) return false;
        if (student.isTeacherAllowed(teacher)) return false;

        student.addAllowedTeacher(teacher);
        return true;
    }

    public boolean isTeacherAllowed(Student student, Teacher teacher) {
        if (student == null || teacher == null) return false;
        return student.isTeacherAllowed(teacher);
    }
}
