import entities.Student;

public class Main {
    public static void main(String[] args) {
        Student stud1 = new Student("Beibarys", "SE-2542", 4.0);
        Student stud2 = new Student("sadsda", "SE-2001", 1.0);

        System.out.println(stud1);
        System.out.println(stud2);

        System.out.println(stud1.equals(stud2));
    }
}