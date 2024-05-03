package campManage.scoreManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ScoreManagement_KMG {
    public static void run() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\민규\\IdeaProjects\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\Database.txt"));
        while (scanner.hasNext()) {
            String str = scanner.next();
            System.out.println(str);
        }
    }
}

