package app;

import java.util.Scanner;
import entities.School;
import features.menu.SchoolMenuHandler;
import features.controller.SchoolController;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        School school = new School(1);

        SchoolController controller = new SchoolController(school);
        SchoolMenuHandler menu = new SchoolMenuHandler(sc, controller);

        menu.run();
        sc.close();
    }
}
