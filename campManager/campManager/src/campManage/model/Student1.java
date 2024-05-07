package campManage.model;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;


public class Student1 {
   private String ID;
   private String name;
   private String[] stuCon ={"Red","Green","Yellow"};
   Subject subject=new Subject();
   Score1 score1=new Score1();
    //생성자
    public Student1() throws Exception {

    }

    Scanner sc = new Scanner(System.in);
    //json
    JSONObject studentInfo = new JSONObject();
    Reader reader = new FileReader("campManager/campManager/src/campManage/src/testjson.json");
    JSONParser parser = new JSONParser();
    //readerclose
    public void rdClose()throws Exception{
        reader.close();
    }


    // 전체 부분 조회 메서드
    public void allStudent() throws Exception {
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject jsonRes = new JSONObject();
        JSONObject[] objs= new JSONObject[]{jsonObject};
        for (JSONObject object : objs){
            Iterator it = object.keySet().iterator();
            while (it.hasNext()){
                String key = (String) it.next();
                jsonRes.put(key, object.get(key));
                System.out.println(key + ":" + object.get(key).toString().replaceAll("\\{","").replaceAll("\\}",""));
            }
        }


    }
    //아이디조회
    public void selectStudent() throws Exception {
        System.out.print("id 입력 : ");
        sc.nextLine();
        String id = sc.nextLine();
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject)obj;
        reader.close();
        System.out.println(jsonObject.get(id));

    }
    //점수 초기화

    //안내문
    public void info(){
        System.out.println("====필수과목====");
        System.out.println("Java, 객체지향, Spring, JPA, MySQL");
        System.out.println("최소 3개 입력 (그만 입력시 입력종료)");
    }
    public void info2(){
        System.out.println("=====선택과목=====");
        System.out.println("디자인 패턴, Spring Security, Redis, MongoDB");
        System.out.println("최소 2개 입력 (그만 입력시 입력종료)");
    }
    //생성
    public void makeStu() throws Exception {

        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject addStudent = new JSONObject();
        JSONObject jsonArray = new JSONObject();
        JSONObject jsonArray2 = new JSONObject();
        JSONObject addSub = new JSONObject();
        boolean flag = true;
        System.out.println("========수강생 생성=========");
        while (flag) {
            JSONObject stuObj = new JSONObject();
            System.out.print("ID 입력 : ");
            String id = sc.nextLine();
            System.out.print("이름 입력 : ");
            String name = sc.nextLine();
            System.out.print("필수 과목 입력 : ");
            info();
            Loopout:
            while (true){
                String must = sc.next();

                if(jsonArray.size()<5){
                    for(int i=0; i<subject.subject1.length; i++){
                        if(must.equals("그만")){
                            break Loopout;
                        }else if(must.equals(subject.subject1[i])){
                            for(int j=0; j<10; j++){
                                addSub.put(j+1, "0");
                                jsonArray.put(must,addSub);
                            }
                        }
                    }
                    if(jsonArray.equals(must)){
                        System.out.println("잘못된 입력입니다!");
                        break;
                    }
                }else{
                    break;
                }
            }
            info2();
            System.out.print("선택 과목 입력 : ");
            Loopout2:
            while (true){
                String may = sc.next();

                if(jsonArray2.size()<4){
                    for(int i=0; i<subject.subject2.length; i++){
                        if(may.equals("그만")){
                            break Loopout2;
                        }else if(may.equals(subject.subject2[i])){
                            for(int j=0; j<10; j++){
                                addSub.put(j+1, "0");
                                jsonArray2.put(may,addSub);
                            }
                        }
                    }
                    if(jsonArray2.equals(may)){
                        System.out.println("잘못된 입력입니다!");
                        break;
                    }
                }else{
                    break Loopout2;
                }
            }
            String select = sc.nextLine();
            System.out.print("현재 상태(Green,Yellow,Red) : ");
            String con = sc.nextLine();
            if(con.equals(stuCon[0])){}
            stuObj.put("상태", con);
            stuObj.put("선택 과목", jsonArray2);
            stuObj.put("필수 과목", jsonArray);
            stuObj.put("이름", name);
            addStudent.put(id,stuObj);

            jsonObject.put("student",addStudent);
            System.out.println(addStudent);
            File jsonFile = new File("campManager/campManager/src/campManage/src/testjson.json");
            FileWriter fileWriter = new FileWriter(jsonFile , false);


            System.out.println("더 추가 하시겠습니까? 1. 예 2. 아니요");
            switch (sc.nextInt()) {
                case 1 :
                    fileWriter.write(jsonObject.toJSONString());
                    flag = true;
                    break;
                case 2 :
                    fileWriter.write(jsonObject.toJSONString());
                    fileWriter.close();
                    flag = false;
                    break;
            }
        }
    }

    //삭제
    public void delStu() throws Exception{
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject)obj;
        System.out.println("================수강생 삭제===================");
        System.out.print("삭제할 수강생 ID 입력 : ");
        String id = sc.nextLine();
        try {
                jsonObject.remove(id);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("삭제 완료");
        try {
            File jsonFile = new File("campManager/campManager/src/campManage/src/testjson.json");
            FileWriter fileWriter = new FileWriter(jsonFile, false);
            fileWriter.write(jsonObject.toJSONString());
            fileWriter.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    //json 키값이 중복되면 뒤에 입력된 값으로 변경됨!
    //수정
    public void reStu() throws Exception{
        Object obj = parser.parse(reader);
        JSONObject jsonObject = (JSONObject)obj;
        JSONObject reset = new JSONObject();
        JSONObject reset2 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        System.out.println("================수강생 수정===================");
        System.out.println("수정할 수강생 ID 입력");
        String id = sc.nextLine();
        reset = (JSONObject) jsonObject.get(id);
        System.out.println(reset);
        reset.put("필수 과목",jsonArray);
        System.out.println(reset);


    }
    //점수 수정
    public void reScore() throws Exception{

    }
    //조회
    public void serStu() throws Exception{
        System.out.println("================수강생 조회===================");
        System.out.println("1. 전체 조회 2. ID 조회");
        switch (sc.nextInt()) {
            case 1 -> allStudent();
            case 2 -> selectStudent();

        }
    }
}