package campManage.studentManagement;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import static campManage.studentManagement.JsonTest2.jsontest2;
// 총 필수과목이 10개고 선택과목 4개다
//수강생 이름 학번(ID) 필수과목 선택과목 상태
// {이름 학번 필수과목{수학,영어,국어} 선택과목{과학 한자} 상태}
// 1. 필수과목 목록이 아닌데 적었을경우
// 필수과목 키를 꺼내서 반복문 조건문 필수과목일떄 선택과목 4개를 조건문으로 걸어서 아닐경우 돌아가고
// 선택과목 선택과목 일때만 아니면 돌아가고
// 배열로 만든다음에 향상된 포문으로 배열만들어서 돌리고 이프구문에 선택과목 향상된포문으로 돌려서 거르고


// 파일리더로 가져온 데이터 x에 저장해서

public class StudentManagement_KDH {
    public static void run() throws IOException, ParseException { // 임시 run2
        JSONObject ab = jsontest2();
//        test(ab);

    }

    // {"선택 과목":{"자바 똥 게":"평균등급 : D"},"필수 과목":{"자바 똥 게":"평균등급 : D"},"name":"백원하","ID":"1315112","status":"그린"}
    //점수
    public static void test(JSONObject objet) throws IOException, ParseException {

        //조회
//        System.out.println("[ 전체조회 또는 개별조회 ]");
//        String typing = sc.next();
//        if (typing.equals("전체조회")) {
//
//            String line = "";
//            while ((line = reader.readLine()) != null) { // 한줄씩 읽음
//                System.out.println(line);
//            }
//
//        } else if (typing.equals("개별조회")) {
//            System.out.println("id를 입력해주세요");
//            boolean idcheck = true;
//            String idtyping = sc.next();
//            String line = "";
//            while ((line = reader.readLine()) != null) { // 한줄씩 읽음
//                String[] section = line.split(":");
//                if (idtyping.equals(section[0])) {
//                    System.out.println("아이디 : " + section[0] + ", 이름 : " + section[1] + ", 수강 과목 : " + section[2] + ", 상태 : " + section[3]);
//                    idcheck = false;
//                    break;
//                }
//            }
//            if (idcheck) {
//                System.out.println("아이디가 없거나 잘못된 입력입니다.");
//            }
//        }
    }
}

