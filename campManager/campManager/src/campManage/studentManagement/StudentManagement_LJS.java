package campManage.studentManagement;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class StudentManagement_LJS {
    //필드
    static HashMap<String, ArrayList<String>> a = new HashMap<>();

    //메서드
    //데이터 읽기
    public static void getDb() throws Exception {
        Scanner sc = new Scanner(new File("campManager/campManager/src/campManage/src/Database.txt"));
        while (sc.hasNextLine()) {
            for (int i = 0; i < sc.nextLine().length(); i++) {
                ArrayList<String> list = new ArrayList<>();
                for (int j = 0; j < 4; j++) {
                    list.add(i, sc.next().split(";")[j]);
                }
                    a.put(sc.next().split(";")[0], list);
                }
            }


        }
    //파일 읽기
    public void readFile(){
        Scanner sc = new Scanner(System.in);
    }
    //수강생 생성
    public void makeStu(){
        ArrayList<String> a = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("ID 입력 : ");
        String id = sc.nextLine();
        System.out.print("이름 입력 : ");
        String name = sc.nextLine();
        System.out.print("필수 과목 입력 : ");
        String must = sc.nextLine();

        System.out.println("선택 과목 입력 : ");



    }


    //값 출력
    public static void run() throws Exception {
        getDb();
    }
}
