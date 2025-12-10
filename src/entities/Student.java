package entities;

import java.util.Objects;

public class Student {
    private String name;
    private String group;
    private double gpa;

    public Student(String name, String group, double gpa){
        this.name = name;
        this.group = group;
        this.gpa = gpa;
    }

    public String getName(){
        return name;
    }

    public String getGroup(){
        return group;
    }

    public double getGpa(){
        return gpa;
    }

    public void setName(String name){
        if(name != null && !name.trim().isEmpty()){
            this.name = name;
        }
    }

    public void setGroup(String group){
        if(group != null && !group.trim().isEmpty()){
            this.group = group;
        }
    }

    public void setGpa(double gpa){
        if(gpa >= 0 && gpa <= 4){
            this.gpa = gpa;
        }
    }

    @Override
    public String toString(){
        return "Student name='" + name + "', group='" + group + "', gpa=" + gpa;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Student student = (Student) obj;

        return Double.compare(student.gpa, gpa) == 0 &&
                name.equals(student.name) &&
                group.equals(student.group);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + Double.hashCode(gpa);
        return result;
    }
}