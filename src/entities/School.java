package entities;

import java.util.ArrayList;

public class School {
    private final int schoolId;
    private final ArrayList<Student> students;
    private final ArrayList<Teacher> teachers;

    public School(int schoolId) {
        this.schoolId = schoolId;
        this.students = new ArrayList<>();
        this.teachers = new ArrayList<>();
    }

    public int getSchoolId() {
        return schoolId;
    }

    public ArrayList<Student> getSchoolStudents() {
        return new ArrayList<>(students);
    }

    public ArrayList<Teacher> getSchoolTeachers() {
        return new ArrayList<>(teachers);
    }

    public boolean addStudentToSchool(Student student) {
        if (student == null || student.getSchoolId() != schoolId) return false;

        for (Student s : students) {
            if (s.getId() == student.getId()) return false;
        }

        students.add(student);
        return true;
    }

    public boolean addTeacherToSchool(Teacher teacher) {
        if (teacher == null || teacher.getSchoolId() != schoolId) return false;

        for (Teacher t : teachers) {
            if (t.getId() == teacher.getId()) return false;
        }

        teachers.add(teacher);
        return true;
    }

    public Student findStudentById(int id) {
        for (Student s : students) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    public Teacher findTeacherById(int id) {
        for (Teacher t : teachers) {
            if (t.getId() == id) return t;
        }
        return null;
    }

    public boolean assignStudentToTeacher(Student student, Teacher teacher) {
        if (student == null || teacher == null) return false;
        if (student.getSchoolId() != schoolId || teacher.getSchoolId() != schoolId) return false;

        return teacher.enrollStudent(student) && student.enrollTeacher(teacher);
    }

    public ArrayList<Student> getStudentsWithHighGrades(String subject, int minGrade) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            for (int grade : s.getGradesBySubject(subject)) {
                if (grade >= minGrade) {
                    result.add(s);
                    break;
                }
            }
        }
        return result;
    }

    public ArrayList<Student> getStudentsWithLowGrades(String subject, int maxGrade) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            for (int grade : s.getGradesBySubject(subject)) {
                if (grade <= maxGrade) {
                    result.add(s);
                    break;
                }
            }
        }
        return result;
    }


    public ArrayList<Student> getStudentsSortedBySubjectAverage(String subject) {
        ArrayList<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Double.compare(b.getAverageBySubject(subject), a.getAverageBySubject(subject)));
        return sorted;
    }

    public ArrayList<Student> getStudentsSortedBySubjectGradesCount(String subject) {
        ArrayList<Student> sorted = new ArrayList<>(students);
        sorted.sort((a, b) -> Integer.compare(b.getGradesBySubject(subject).size(), a.getGradesBySubject(subject).size()));
        return sorted;
    }
}
