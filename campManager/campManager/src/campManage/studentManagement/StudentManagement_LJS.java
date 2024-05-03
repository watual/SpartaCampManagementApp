package campManage.studentManagement;

import java.io.File;
import java.sql.SQLException;
import java.util.*;

public class StudentManagement_LJS {
    // 필드
    static HashMap<String, ArrayList<String>> a = new HashMap<>();

    // 메서드
    // 데이터 읽기
    public static void getDb() throws Exception {
        Scanner sc = new Scanner(new File("campManager/campManager/src/campManage/src/Database.txt"));
        for (;;) {
            if (sc.hasNextLine()) {
                ArrayList<String> list = new ArrayList<>();
                list.add(Arrays.toString(sc.nextLine().split(";")));
                a.put(sc.nextLine().split(",")[0], list);

            } else {
                break;
            }
        }

    }

    // 파일 읽기
    public void readFile() {
        Scanner sc = new Scanner(System.in);
    }

    // 수강생 생성
    public void makeStu() {
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
    
    // 값 출력
    public static void run() throws Exception {

    }
}