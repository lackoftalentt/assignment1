import java.util.Scanner;
import entities.School;
import features.SchoolMenuHandler;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        School school = new School(1);

        SchoolMenuHandler menuHandler = new SchoolMenuHandler(sc, school);
        menuHandler.run();

        sc.close();
    }
}
