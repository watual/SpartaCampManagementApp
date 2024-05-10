package campManage.studentManagement;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonTest {

    public static JSONObject createJsonData() throws IOException, ParseException {


        // 모든 데이터를 담을 JSONObject 생성
        Reader reader = new FileReader("campManager/campManager/src/campManage/src/Database_김도훈.json");
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject) obj;
        JSONObject core = (JSONObject) jsonObject.get("students");

        Object CopyObject(){};
        Object CopyObject(CopyObject jsonObject){

        }


        int zeroNum = 0;
        String zeroNumstr = "" + zeroNum;
        Object keyNum = core.get(zeroNumstr);
        while (keyNum != null) {
            zeroNum = zeroNum + 1;
            zeroNumstr = "" + zeroNum;
            keyNum = core.get(zeroNumstr);
        }
        System.out.println("Objetct 의 키 번호 부여 : " + zeroNumstr); // 학생 데이터번호 zero
        System.out.println(obj2);
        obj2.put("students", zeroNumstr); // "2"
        System.out.println(obj2);
        System.out.println(core);


        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력해주세요.");
        String name = sc.nextLine();
        System.out.println("학번을 입력해주세요.");
        int studentId = sc.nextInt();
        System.out.println("필수 과목을 입력해주세요.");
        String SUBJECT_TYPE_MANDATORY = sc.nextLine();
        System.out.println("선택 과목을 입력해주세요.");
        String SUBJECT_TYPE_CHOISE = sc.nextLine();
        System.out.println("상태를 입력해주세요 Green/Red/Yellow");
        String status = sc.next();
        String averageScore = "평균등급 : ";

        //students
        jsonObject.put("students", zeroNumstr); // "2"
        jsonObject.put("config", "컨피그 테스트 문구");
        jsonObject.put(zeroNumstr, "테스트이름");
        jsonObject.put(zeroNumstr, "테스트상태");
        System.out.println(jsonObject);
// {"2":"상태","students":"2","config":"컨피그 테스트 문구"}
        JSONObject studentInfo = new JSONObject();

        studentInfo.put("이름", name);


        JSONObject subjectINfo = new JSONObject();
        subjectINfo.put(SUBJECT_TYPE_MANDATORY, averageScore + 'D');
        studentInfo.put("필수과목", subjectINfo);

        JSONObject choiceSubject = new JSONObject();
        choiceSubject.put(SUBJECT_TYPE_CHOISE, averageScore + 'D');
        studentInfo.put("선택과목", subjectINfo);

        JSONObject configObject = new JSONObject();
        configObject.put("학생고유번호", studentId);
        subjectINfo.put("config", configObject);

        JSONObject students = new JSONObject();
        students.put("students", studentInfo);


        BufferedReader bufferedReader = new BufferedReader(new FileReader("campManager/campManager/src/campManage/src/Database_김도훈.json"));
        File file = new File("campManager/campManager/src/campManage/src/Database_김도훈.json");


        //텍스트파일에 입력
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, false));
        writer.newLine();
        writer.write(String.valueOf(jsonObject));
        writer.close(); //flush() 내장


//        JSONObject studentsCount = new JSONObject();
//        int countNum = 0; // json 파일의 번호를 읽어서 0 대신 대입
//        studentsCount.put(countNum, students);
//        countNum = countNum + 1;

//        // 과목별 점수 조회
//        JSONArray itemList = new JSONArray();
//        JSONObject item1 = new JSONObject();
//        item1.put("item_no", 21);
//        item1.put("item_name", "L/Blue");
//        itemList.add(item1);
//        System.out.println(item1);

        return studentInfo;


    }
}
