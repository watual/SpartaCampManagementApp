package studentManagement;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagement_LJS  {
    //필드
    ArrayList<String> a = new ArrayList<>();
    //메서드
    //값 출력
    public void getStudent() throws Exception {
        Scanner sc = new Scanner(new File("campManager/campManager/src/campManage/src/Database.txt"));

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            a.add(line);
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }
}

public class StudentManagement_LJS {
    public static void run(){

    }

}
