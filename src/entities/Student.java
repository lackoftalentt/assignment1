import java.util.ArrayList;
import java.util.HashMap;

public class Student{
    private int final studentId;
    private int final schoolId;
    private String name;
    private final Map<String, ArrayList<integer>> gradesBySubject;
    private final ArrayList<Teacher> allowedTeachers = new ArrayList<>();

    public Student(int studentId, int schoolId; String name){
        this.studentId = studentId;
        this.schoolId = schooldId;
        this.name = name;
        this.gradesBySubject = new HashMap<>();
    }

    public int getStudentId(){
        return studentId;
    }

    public int getSchoolId(){
        return schoolId;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    private void addGradeInternal(String subject, int grade) {
        gradesBySubject.computeIfAbsent(subject, k -> new ArrayList<>()).add(grade);
    }

    public boolean enrollTeacher(Teacher teacher){
        if(teacher == null) return false;

        if(allowedTeachers.contains(teacher)) return false;

        if(teacher.schoolId != this.getSchoolId()) return false;

        allowedTeachers.add(teacher);
        return true;
    }

    public boolean receiveGradeFromTeacher(Teacher teacher, int garde){
        if(teacher == null) return false;

        if(!allowedTeachers.contains(teacher)) {
            System.out.println("Ошибка: этот преподаватель не ведёт студента по предмету " + teacher.getSubject);
            return false;
        }

        addGradeInternal(teacher.getSubject(), grade);
        return true;
    }

};