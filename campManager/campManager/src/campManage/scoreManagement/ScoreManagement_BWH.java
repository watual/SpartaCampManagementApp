package campManage.scoreManagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScoreManagement_BWH {

    public static void run() throws FileNotFoundException {
        String path = "campManager/campManager/src/campManage/src/Database.txt";
        Scanner sc = new Scanner(new File(path));
        while(sc.hasNext()){
            String str = sc.nextLine();
            System.out.println(str);


        }
    }
}
