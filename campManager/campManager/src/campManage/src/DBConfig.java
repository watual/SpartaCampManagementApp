package campManage.src;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class DBConfig {
    public static final String PATH = "campManager/campManager/src/campManage/src/Database.json";
    public static final String PATH_BU = "campManager/campManager/src/campManage/src/DatabaseBackup.txt";

    public static JSONObject dataJson;  //전체 데이터
    public static JSONObject config;    //설정 데이터
    public static JSONObject students;  //학생들 데이터 ( 과목, 상태, 점수 )

    public static void initDBConfig() throws IOException, ParseException {
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
        //등록 -> DB.txt 파일에 데이터 추가
        String path = DBConfig.PATH;

        //JSON DB 파일 호출
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));

        //JSON 데이터 String 한 줄로 변환
        StringBuilder sb = new StringBuilder();
        String strDB;
        while ((strDB = br.readLine()) != null) {
            sb.append(strDB.trim());
        }
        br.close();
        //JSON 객체 할당
        // String빌더 -> String -> Object -> JSON
        try {
            dataJson = (JSONObject) (new JSONParser().parse(sb.toString()));
            config = (JSONObject) dataJson.get("config");
            students = (JSONObject) dataJson.get("students");
        } catch (Exception e){
            System.out.println("데이터가 없습니다");
            //JSON 데이터가 없을때 초기생성
            config = (JSONObject) ((new JSONParser().parse("{\"학생고유번호\":0}")));
//            students = (JSONObject) ((new JSONParser().parse("{}")));
        }
    }
    public static void updateDatabase() {
        try {
            //dataJson에 config, students 병합
            dataJson.put("config", config);
            dataJson.put("students", students);
            //json 파일 갱신
            //파일에 데이터 작성하는 방법 : BufferedWriter, PrintWriter, FileWriter
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH, false));
            bw.write(DBConfig.dataJson.toJSONString());
            bw.close();
            initDBConfig();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void updateDataJson() {
        try {
            //json 파일 갱신
            //파일에 데이터 작성하는 방법 : BufferedWriter, PrintWriter, FileWriter
            BufferedWriter bw = new BufferedWriter(new FileWriter(PATH, false));
            bw.write(DBConfig.dataJson.toJSONString());
            bw.close();
            initDBConfig();
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
//    public static void modifyStudent(String studentId, JSONObject studentJson) throws IOException {
//        students.put(studentId, studentJson);
//        updateDatabase();
//    }
//    public static void modifySubjects(String studentId, String sortOfSubjects, JSONObject subjects){
//        if(sortOfSubjects.equals("필수과목") || sortOfSubjects.equals("선택과목")){
//
//            students.put(studentId, subjects);
//        }
//    }
//    public static void modifySubject(String studentId, String subjectName, String modifyName,JSONObject subjectJson){
//        JSONObject student = (JSONObject) students.get(studentId);
//
//        if(((JSONObject) student.get("필수과목")).containsKey(subjectName)){
//            JSONObject subjectSort = (JSONObject) student.get("필수과목");
//            JSONObject subject = (JSONObject) subjectSort.get(subjectName);
//
//            subjectSort.remove(subjectName);
//
//            student.put("필수과목",subjectSort);
//            students.put(studentId,student);
//
//        } else if (((JSONObject) student.get("선택과목")).containsKey(subjectName)) {
//            JSONObject subjectSort = (JSONObject) student.get("선택과목");
//            JSONObject subject = (JSONObject) subjectSort.get(subjectName);
//        }
//    }
}
