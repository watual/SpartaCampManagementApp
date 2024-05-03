package campManage.studentManagement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class test2 {
    public static void main(String[] args) throws Exception{
        HashMap<String, ArrayList<String>> l1 = new HashMap<>();
        Path path = Paths.get("campManager/campManager/src/campManage/src/Database.txt");
        //파일 라인수 세기
        int result=0;
        try {
            long e = Files.lines(path).count();
            System.out.println(e);
            result = (int)e;
        }catch (Exception e){
            e.printStackTrace();
        }finally {

        }
        while(true) {
            for (int i = 0; i < 4; i++) {
                Scanner sc = new Scanner(new File("campManager/campManager/src/campManage/src/Database.txt"));
                ArrayList<String> list = new ArrayList<>();
                for(int j = 0; j < result; j++) {
                    list.add(sc.nextLine().split(";")[i]);
                }
                l1.put(String.valueOf(i), list);
            }
            break;
        }

    }
}
