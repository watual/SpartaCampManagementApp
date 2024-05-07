package campManage.studentManagement;

import campManage.src.DBConfig;
import netscape.javascript.JSObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.w3c.dom.ls.LSOutput;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

public class StudentRegistration_BWH {
    //인스턴스 : 클래스에 생성된 변수, 함수 들
    private final Scanner sc;
    BufferedWriter bw;
    String path = DBConfig.PATH_json_BWH;

    StudentRegistration_BWH() throws IOException, ParseException {
        this.sc = new Scanner(System.in);
    }

    public void inquiry() throws IOException, ParseException {
        end:
        while (true) {
            System.out.println("==================== 학생관리 ====================");
            System.out.println("0. 뒤로 돌아가기");
            System.out.println("1. 전체 학생정보 조회");
            System.out.println("2. 학생정보 검색");
            System.out.print("번호 입력: ");

            switch (sc.nextLine()) {
                case "0" -> {
                    break end;
                }
                case "1" -> inquiryAll();
                case "2" -> inquirySearch();
                default -> {
                    System.out.println("입력이 올바르지 않습니다. 번호를 입력해주세요.");
                }
            }
        }
    }

    public void inquiryJson(JSONObject jsonObject) {
        //JSON 값 출력
        System.out.println(
                STR."이름: \{jsonObject.get("이름")}\t/ " +
                        STR."필수과목: \{((JSONObject)jsonObject.get("필수과목")).keySet()}\t/ " +
                        STR."선택과목: \{((JSONObject)jsonObject.get("선택과목")).keySet()}\t/ " +
                        STR."상태: \{jsonObject.get("상태")}"
        );
    }

    public void inquiryJsonS(JSONObject jsonObject) {
        //다중 Json 낱개 출력
        jsonObject.values().forEach(value -> {
            inquiryJson((JSONObject) value);
        });
    }

    public void inquiryAll() {
        System.out.println("==================== 전체조회 결과 ====================");
        inquiryJsonS(DBConfig.students);
    }

    public void inquirySearch() {
        System.out.println("아직 미구현된 기능입니다.");
    }

    public void makeSubjectJson(String subject){
        loop:
        while (true){
            System.out.println(STR."========== \{subject} 선택 ==========");
            JSONObject subjectElement = (JSONObject) DBConfig.config.get(subject);
            subjectElement.keySet().stream().sorted().forEach(e -> {
                System.out.println(STR."\{e}. \{subjectElement.get(e)}");
            });

            String str = sc.nextLine();
            switch (str) {
                case "0" -> {break loop;}
            }
            if ("exit".equals(str.toLowerCase())) break;
//            student.put("이름", str);

            }
        }


    public void registrate() throws IOException {

//        System.out.println(config.get("학생고유번호"));
        long studentIndexMax = (long) DBConfig.config.get("학생고유번호생성");
        JSONObject newStudents = new JSONObject();
        String str;
        while (true) {
            System.out.println("==================== 학생정보 등록 ====================");
            System.out.println(STR."========== 등록 [종료: 'exit' 입력] ==========");

            JSONObject student = new JSONObject();
            JSONObject subjectMandatory = new JSONObject();
            JSONObject subjectChoice = new JSONObject();
            student.put("고유번호", studentIndexMax);

            System.out.print("학생이름: ");
            str = sc.nextLine();
            if ("exit".equals(str.toLowerCase())) break;
            student.put("이름", str);

            makeSubjectJson("필수과목");

            System.out.print("선택과목: ");
            str = sc.nextLine();
            if ("exit".equals(str.toLowerCase())) break;
            student.put("선택과목", str);

            System.out.print("상태: ");
            str = sc.nextLine();
            if ("exit".equals(str.toLowerCase())) break;
            student.put("상태", str);

            //students JSON에 제작한 student를 추가
            DBConfig.students.put(studentIndexMax, student);
            newStudents.put(studentIndexMax, student);
            studentIndexMax++;
            inquiryJsonS(student);
        }
        inquiryJsonS(newStudents);

        DBConfig.config.put("학생고유번호생성", studentIndexMax);
        DBConfig.updateDatabase();  //수정
        System.out.println("새로운 데이터 등록완료");
    }


    public void modify() {

    }

    public void delete() {

    }
}
