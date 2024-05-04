package campManage.studentManagement;

import campManage.src.DBConfig;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Scanner;

public class StudentRegistration_BWH {
    //등록 -> DB.txt 파일에 데이터 추가
    private String path = DBConfig.PATH_json_BWH;

    StudentRegistration_BWH() {
    }

    public void inquiry() throws Exception {
        /*
        데이터 읽기
        1. BufferedReader
            - 대량의 데이터를 읽을 때 사용
            - 속도가 빠름
            - 가장 무난하고 추천되는 방식
            - 버퍼의 기본 사이즈는 8KB이다
        2. Scanner
            - 지정된 서식(int,char..)을 읽을 때 사용
            - 정수, 실수, 문자열과 같은 다양한 데이터 유형을 파싱할 수 있기 때문에 구조화된 형식으로 데이터를 읽는 데 유용
            - 줄바꿈, 공백 등 구분된 내용을 쉽게 읽음
        3. FileReader
            - 한번에 하나의 문자를 읽기 때문에 작은 파일이나 데이터를 문자 단위로 처리해야 할 때 유용
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        StringBuilder sb = new StringBuilder();
        String str;
        while ((str = br.readLine()) != null) {
            sb.append(str.trim());
        }
        //String -> Object로 parse -> JSONObject로 형변
        JSONObject jsonObject = (JSONObject) (new JSONParser().parse(sb.toString()));

        JSONObject config = (JSONObject) jsonObject.get("config");
        JSONObject students = (JSONObject) jsonObject.get("students");
        JSONObject student = (JSONObject) students.get("student0");

        System.out.println(config.get("현재고유번호"));
        System.out.println(student.get("이름"));

        System.out.println("========================================");
    }

    JSONObject data;

    public void registrate() throws IOException {
//        Reader reader = new FileReader("");
        //파일에 데이터 작성하는 방법 : BufferedWriter, PrintWriter, FileWriter
        BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
        Scanner sc = new Scanner(System.in);

        //JSON 파일 제작
        JSONObject data = new JSONObject();
        JSONObject config = new JSONObject();
        JSONObject students = new JSONObject();

        //a, b, c 클래스
        //a.x1 = 23;
        String studentIndex;
        String studentName;
        String studentSubjectMandatory;
        String studentSubjectChoice;
        String studentIndexMax = "";
        bw.newLine();
        for (int i = 0; i < 2; i++) {
            JSONObject student = new JSONObject();
            System.out.println("========================================");
            System.out.println("# # # 학생 정보 등록 # # #");
            student.put("고유번호", 0);
            System.out.println("학생이름: ");
            studentName = sc.nextLine();
            student.put("이름", studentName);
            System.out.println("필수과목: ");
            studentSubjectMandatory = sc.nextLine();
            student.put("필수과목", studentSubjectMandatory);
            System.out.println("선택과목: ");
            studentSubjectChoice = sc.nextLine();
            student.put("선택과목", studentSubjectChoice);

            students.put("student" + i, student);
            studentIndexMax = Integer.toString(i);
        }
        config.put("현재고유번호", studentIndexMax);
        data.put("config", config);
        data.put("students", students);

        bw.write(data.toJSONString());
        bw.close();
        System.out.println("새로운 데이터 등록완료");
    }

    public void modify() {

    }

    public void delete() {
    }
}
