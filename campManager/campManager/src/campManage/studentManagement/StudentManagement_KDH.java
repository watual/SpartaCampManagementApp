package campManage.studentManagement;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class StudentManagement_KDH {
    public static void run() throws IOException {
        System.out.println("도훈");
    }

    public static void test() throws IOException {
        Scanner sc = new Scanner(new File("campManager/campManager/src/campManage/src/Database.txt"));
        while (sc.hasNext()) {
            String str = sc.next();
            System.out.println(str);
        }

    }
}
