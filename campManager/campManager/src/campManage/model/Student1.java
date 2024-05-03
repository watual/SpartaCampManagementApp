package campManage.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;


public class Student1 {
   private String ID;
   private String name;
   private String stuCon;
   Subject subject;
    //생성자
    public Student1() throws Exception {

    }

    Scanner sc = new Scanner(System.in);
    //json
    JSONObject studentInfo = new JSONObject();
    Reader reader = new FileReader("campManager/campManager/src/campManage/src/testjson.json");

    // 전체 부분 조회 메서드
    public void allStudent(){

    }
    public void selectStudent() {

    }

    //생성
    public void makeStu() throws Exception {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject)obj;
        reader.close();
        JSONObject addStudent = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        boolean flag = true;
        System.out.println("========수강생 생성=========");
        while (flag) {
            JSONObject stuObj = new JSONObject();
            System.out.print("ID 입력 : ");
            String id = sc.nextLine();
            System.out.print("이름 입력 : ");
            String name = sc.nextLine();
            System.out.print("필수 과목 입력 : ");
            while (true){
                String must = sc.next();
                if(jsonArray.size()<5){
                    if(jsonArray.equals(must)){
                        System.out.println("잘못된 입력입니다!");
                    }else{
                    jsonArray.add(must);}
                }else{
                    break;
                }
            }
            System.out.print("선택 과목 입력 : ");
            String select = sc.nextLine();
            System.out.print("현재 상태 : ");
            String con = sc.nextLine();
            stuObj.put("이름", name);
            stuObj.put("필수 과목", jsonArray);
            stuObj.put("선택 과목", select);
            stuObj.put("상태", con);
            addStudent.put(id,stuObj);
            JSONObject jsonRes = new JSONObject();
//            jsonObject.merge("학생들",addStudent, );
            JSONObject[] objs = new JSONObject[]{jsonObject,addStudent};
            for (JSONObject object : objs){
                Iterator it = object.keySet().iterator();
                while (it.hasNext()){
                    String key = (String) it.next();
                    jsonRes.put(key, object.get(key));
                }
            }
            System.out.println(jsonRes);
            File jsonFile = new File("campManager/campManager/src/campManage/src/testjson.json");
            FileWriter fileWriter = new FileWriter(jsonFile , false);


            System.out.println("더 추가 하시겠습니까? 1. 예 2. 아니요");
            switch (sc.nextInt()) {
                case 1 :
                    fileWriter.write(jsonRes.toJSONString());
                    flag = true;
                    break;
                case 2 :
                    fileWriter.write(jsonRes.toJSONString());
                    fileWriter.close();
                    flag = false;
                    break;
            }
        }
    }
        //출력 테스트
//    public void getlist()throws Exception{
      //  JSONParser parser = new JSONParser();

    // Object obj = parser.parse(reader);
//        JSONObject jsonObject = (JSONObject) obj;
//        System.out.println(jsonObject);
//    }
    //삭제
    public void delStu(){
        //조회
        System.out.println("삭제할 수강생 찾는 값");
        //삭제
    }
    //수정
    public void reStu(){
        System.out.println("수정할 수강생 찾는 값");
        //수정
    }
    //조회
    public void serStu(){
        System.out.println("1. 전체 조회 2. 검색 조회");
        switch (sc.nextInt()){
            case 1 -> allStudent();
            case 2 -> selectStudent();
            default -> System.out.println("잘못된 값 입력");

        }
    }
}

