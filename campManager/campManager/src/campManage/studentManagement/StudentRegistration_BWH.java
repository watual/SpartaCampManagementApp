package campManage.studentManagement;

import campManage.src.DBConfig;
import netscape.javascript.JSObject;
import org.json.simple.JSONObject;

import java.io.*;
import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class StudentRegistration_BWH {
    //등록 -> DB.txt 파일에 데이터 추가
    private String path = DBConfig.PATH_BWH;

    StudentRegistration_BWH() {
    }

    public void inquiry() throws IOException {
        /*
        데이터 읽기
        1. BufferedReader
            - 대량의 데이터를 읽을 때 사용
            - 속도가 빠름
            - 가장 무난하고 추천되는 방식
        2. Scanner
            - 지정된 서식(int,char..)을 읽을 때 사용
            - 정수, 실수, 문자열과 같은 다양한 데이터 유형을 파싱할 수 있기 때문에 구조화된 형식으로 데이터를 읽는 데 유용
            - 줄바꿈, 공백 등 구분된 내용을 쉽게 읽음
        3. FileReader
            - 한번에 하나의 문자를 읽기 때문에 작은 파일이나 데이터를 문자 단위로 처리해야 할 때 유용
         */
        BufferedReader br = new BufferedReader(new FileReader(path));
        String str = br.readLine();
        System.out.println("========================================");
        while (str != null) {
            System.out.println(str);
            str = br.readLine();
        }
    }

    public void registrate() throws IOException {
        //파일에 데이터 작성하는 방법 : BufferedWriter, PrintWriter, FileWriter
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        Scanner sc = new Scanner(System.in);

        JSONObject job = new JSONObject();
        

        String INDEX_STUDENT = "INDEX_STUDENT";
        int studentIndexNum=0;

        String studentIndex;
        String studentName;
        String studentSubject;

        bw.newLine();
        System.out.println("========================================");
        System.out.println("# # # 학생 정보 등록 # # #");
        studentIndex = INDEX_STUDENT + (++studentIndexNum);
        System.out.println("학생이름: ");
        studentName = sc.nextLine();
        System.out.println("학생: ");
        bw.write("asdf");
        bw.close();
        System.out.println("새로운 데이터 등록완료");
    }

    public void modify() {

    }

    public void delete() {
    }
}
