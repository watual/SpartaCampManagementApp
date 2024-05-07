package campManage.studentManagement;

import campManage.model.Score1;
import campManage.model.Subject;
import campManage.src.DBConfig;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Iterator;
import java.util.Scanner;


public class StudentManager {
   private String ID;
   private String name;
   private String[] stuCon ={"Red","Green","Yellow"};
   Subject subject=new Subject();
   Score1 score1=new Score1();

   Scanner sc;

   JSONObject jsonObject;
   JSONObject config;
   JSONObject student;
   JSONObject emptySubject;

   JSONObject studentInfo;

   JSONParser parser;

    //생성자
    public StudentManager() throws Exception {
        sc = new Scanner(System.in);
        Reader reader = new FileReader("campManager/campManager/src/campManage/src/testjson.json");
        parser = new JSONParser();
        jsonObject = (JSONObject) parser.parse(reader);
        reader.close();

        config = (JSONObject) jsonObject.get("config");
        student = (JSONObject) jsonObject.get("student");
        JSONObject studentInfo = new JSONObject();
        parser = new JSONParser();
        emptySubject = (JSONObject) DBConfig.config.get("empty");
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
    // 전체 부분 조회 메서드
    public void allStudent() throws Exception {
        inquiryAll();
//        JSONObject jsonRes = new JSONObject();
//        JSONObject[] objs= new JSONObject[]{jsonObject};
//        for (JSONObject object : objs){
//            Iterator it = object.keySet().iterator();
//            while (it.hasNext()){
//                String key = (String) it.next();
//                jsonRes.put(key, object.get(key));
//                System.out.println(key + ":" + object.get(key).toString().replaceAll("\\{","").replaceAll("\\}",""));
//            }
//        }
    }
    //아이디조회
    public void selectStudent() throws Exception {
        System.out.print("id 입력 : ");
        sc.nextLine();
        String id = sc.nextLine();
        System.out.println(jsonObject.get(id));

    }
    //점수 초기화

    //안내문
    public void info(){
        System.out.println("====필수과목====");
//        System.out.println("Java, 객체지향, Spring, JPA, MySQL");
        JSONObject subMandatory = (JSONObject) DBConfig.config.get("필수과목");
        subMandatory.keySet().stream().sorted().forEach(e -> {
            System.out.println(e+". "+subMandatory.get(e));
        });
        System.out.println("최소 3개 입력 (0 입력시 입력종료)");
    }
    public void info2(){
        System.out.println("=====선택과목=====");
        System.out.println("디자인 패턴, Spring Security, Redis, MongoDB");
        System.out.println("최소 2개 입력 (0 입력시 입력종료)");
    }
    //생성
    public void makeStu() throws Exception {

//        Object obj = parser.parse(reader);
//        JSONObject jsonObject = (JSONObject)obj;
        JSONObject addStudent = new JSONObject();
        JSONObject jsonArray = new JSONObject();
        JSONObject jsonArray2 = new JSONObject();
        JSONObject addSub = new JSONObject();
        boolean flag = true;
        System.out.println("========수강생 생성=========");
        while (flag) {
            //막는거 만들기
            JSONObject stuObj = new JSONObject();
//            System.out.print("ID 입력 : ");
            String id =  config.get("id").toString();
            System.out.print("이름 입력 : ");
            String name = sc.nextLine();
            info();
            Loopout:
            while (true){
                //must : 필수과목 이름
                String must = sc.nextLine();
                if(must.equals("0") || jsonArray.size()>=5)break;

                //필수과목 key값 입력 -> value(java)에 emptySubject달아서 임시 json 생성
                System.out.println(emptySubject.getClass());
//                if(jsonArray.size()<5){
//                        jsonArray.put(,emptySubject);
//                    for(int i=0; i<((JSONObject)DBConfig.config.get("필수과목")).size(); i++){ //config 최대 선택
//                        if(must.equals("0")){
//                            break Loopout;
//                        }else if(must.equals(subject.subject1[i])){
//                            for(int j=0; j<10; j++){
//                                addSub.put(j+1, "0");
//                                jsonArray.put(must,addSub);
//                            }
//                        }
//                    }
//                    if(jsonArray.equals(must)){
//                        System.out.println("잘못된 입력입니다!");
//                        break;
//                    }
//                }else{
//                    break;
//                }
            }
            info2();
            System.out.print("선택 과목 입력 : ");
            Loopout2:
            while (true){
                String may = sc.next();

                if(jsonArray2.size()<4){
                    for(int i=0; i<subject.subject2.length; i++){
                        if(may.equals("0")){
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
            if(con.equals(stuCon[0])){} // yellow, red, green 아닐때
            stuObj.put("상태", con);
            stuObj.put("선택 과목", jsonArray2);
            stuObj.put("필수 과목", jsonArray);
            stuObj.put("이름", name);
            addStudent.put(id,stuObj);

            jsonObject.put("student",addStudent);   //student{1, 2, 3, 4}, config
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
//        Object obj = parser.parse(reader);
//        JSONObject jsonObject = (JSONObject)obj;
        System.out.println("================수강생 삭제===================");
//        JSONObject jsonRes = (JSONObject) jsonObject.get("student");
//        JSONObject jsonview = new JSONObject();
//        JSONObject[] objs= new JSONObject[]{jsonRes};
//        for (JSONObject object : objs){
//            Iterator it = object.keySet().iterator();
//            while (it.hasNext()) {
//                String key = (String) it.next();
//                jsonview.put(key, object.get(key));
//                System.out.println(key + ":" + jsonview.get("이름")+ "\t/");
//
//            }
//        }

        inquiryAll();
        System.out.print("삭제할 수강생 ID 입력 : ");
        String id = sc.nextLine();
        try {
                DBConfig.students.remove(id);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("삭제 완료");
        DBConfig.updateDatabase();
    }

    //json 키값이 중복되면 뒤에 입력된 값으로 변경됨!
    //수정
    public void reStu() throws Exception{
//        Object obj = parser.parse(reader);
//        JSONObject jsonObject = (JSONObject)obj;
        JSONObject reset;
//        JSONObject reset2 = new JSONObject();
        System.out.println("================수강생 수정===================");
        inquiryAll();
        System.out.println("수정할 수강생 ID 입력");
        String id = sc.nextLine();
        reset = (JSONObject) student.get(id);//jsonObject:전체데이터, student:학생데이터
        System.out.println("1. 필수과목 수정 2. 선택과목 수정 3. 상태수정");
        switch (sc.nextLine()){
            case "1" : JSONObject op = new JSONObject();
                JSONObject tt = new JSONObject();
                System.out.println("수정할 과목 입력");
                String si = sc.nextLine();
                reset.remove(si);
                System.out.println("변경할 과목 입력");
                String gg = sc.nextLine();
                for(int j=0; j<10; j++){
                    op.put(j+1, "0");

                    tt.put(gg,op);
                }
                reset.put("필수과목",tt);
//            case 2 -> ;
//            case 3 -> ;
        }
        student.put(id,reset);
        System.out.println("수정 완료!");
        DBConfig.updateDatabase();

    }
    //필수 과목
    public void setMainSub(JSONObject reset){
        JSONObject op = new JSONObject();
        JSONObject tt = new JSONObject();
        System.out.println("수정할 과목 입력");
        String si = sc.nextLine();
        reset.remove(si);
        System.out.println("변경할 과목 입력");
        String gg = sc.nextLine();
        for(int j=0; j<10; j++){
            op.put(j+1, "0");

            tt.put(gg,op);
        }
        reset.put("필수과목",tt);
    }

    public void inquiryAll() {
        DBConfig.students.keySet().forEach(key -> {
            System.out.print("ID: "+key+"\t/ ");
            JSONObject student = (JSONObject) DBConfig.students.get(key);
            System.out.println(
                    "이름: "+student.get("이름")+ "\t/ " +
                            "필수과목: "+((JSONObject)student.get("필수과목")).keySet()+"\t/ " +
                            "선택과목: "+((JSONObject)student.get("선택과목")).keySet()+"\t/ " +
                            "상태: "+student.get("상태")
            );
        });
    }

    public void inquiryJson(JSONObject jsonObject) {
        //JSON 값 출력
        System.out.println(
                "이름: "+jsonObject.get("이름")+ "\t/ " +
                        "필수과목: "+((JSONObject)jsonObject.get("필수과목")).keySet()+"\t/ " +
                        "선택과목: "+((JSONObject)jsonObject.get("선택과목")).keySet()+"\t/ " +
                        "상태: "+jsonObject.get("상태")
        );
    }
}