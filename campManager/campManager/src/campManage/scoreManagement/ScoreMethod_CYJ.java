package campManage.scoreManagement;

import campManage.src.DBConfig;

import java.io.FileReader;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import org.w3c.dom.ls.LSOutput;


import java.io.*;
import java.util.Scanner;

public class ScoreMethod_CYJ {

    //생성하기
    public static void crateScore() throws IOException, ParseException {
        JSONObject j = new JSONObject();
        Scanner sc = new Scanner(System.in);
        System.out.println("고유번호를 입력하세요");
        int studentId = sc.nextInt();
        sc.nextLine();

        System.out.println("과목을 입력하세요");
        String subjectName = sc.nextLine();

        System.out.println("회차를 입력하세요");
        int round = sc.nextInt();

        System.out.println("점수를 입력하세요");
        int score = sc.nextInt();

        j.put("고유번호", studentId);
        j.put("과목", subjectName);
        j.put("회차", round);
        j.put("점수", score);


        try (FileWriter fileWriter = new FileWriter("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\testCYJ.json")) {
            fileWriter.write(j.toJSONString());
            System.out.println("JSON 파일이 성공적으로 생성되었습니다.");
        } catch (IOException e) {
            System.out.println("JSON 파일 생성 중 오류가 발생했습니다.");
            e.printStackTrace();
        }

        Object o = new JSONParser().parse(new FileReader("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\testCYJ.json"));
        j = (JSONObject) o;

        Long jsonStudentId = (Long) j.get("고유번호");
        String jsonSubjectName = (String) j.get("과목");
        Long jsonRound = (Long) j.get("회차");
        Long storedScore = (Long) j.get("점수");

        if (studentId == jsonStudentId) {
            // 입력한 과목명과 회차에 해당하는 점수를 JSON 파일에서 가져옴
            System.out.println("고유번호" + jsonStudentId);
            System.out.println("과목명: " + jsonSubjectName);
            System.out.println("회차: " + jsonRound);
            System.out.println("점수: " + storedScore);
        } else {
            System.out.println("일치하는 학생이 없습니다.");
        }

    }
    public static void updateRoundScoreBySubject() throws IOException, ParseException {
        Scanner sc = new Scanner(System.in);

        JSONObject j;
        Object o = new JSONParser().parse(new FileReader("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\testCYJ.json"));
        j = (JSONObject) o;

        System.out.println("고유번호를 입력하세요.");
        long studentId = sc.nextLong();

        Long jsonStudentId = (Long) j.get("고유번호");
        String jsonSubjectName = (String) j.get("과목");
        Long jsonRound = (Long) j.get("회차");
        Long storedScore = (Long) j.get("점수");

        if (studentId == jsonStudentId) {
            // 입력한 과목명과 회차에 해당하는 점수를 JSON 파일에서 가져옴
            System.out.println("고유번호: " + jsonStudentId);
            System.out.println("과목명: " + jsonSubjectName);
            System.out.println("회차: " + jsonRound);
            System.out.println("점수: " + storedScore);

            sc.nextLine(); // 버퍼의 개행 문자를 비우기 위해 추가

            System.out.println("수정할 과목을 입력하세요.");
            String subjectName = sc.nextLine();

            if (subjectName.equals(jsonSubjectName)) {
                System.out.println("변경할 회차를 입력해주세요");
                int round = sc.nextInt();
                sc.nextLine();

                if (round == jsonRound) {
                    System.out.println("변경할 점수를 입력해주세요");
                    int score = sc.nextInt();

                    j.put("점수", score);

                    FileWriter fileWriter = new FileWriter("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\testCYJ.json");
                    fileWriter.write(j.toJSONString());
                    fileWriter.close();
                } else {
                    System.out.println("회차 틀림");
                }
            } else {
                System.out.println("과목명 틀림");
            }
        } else {
            System.out.println("고유번호 틀림");
        }
    }

    public static void inquireRoundGradeBySubject() throws IOException, ParseException {

        JSONObject j;
        Object o = new JSONParser().parse(new FileReader("C:\\Users\\최유진\\Desktop\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\testCYJ.json"));
        j = (JSONObject) o;

        Long jsonRound = (Long) j.get("회차");
        Long storedScore = (Long) j.get("점수");

        Grade grade = calculateRequiredSubjectGrade(storedScore);
        System.out.println(grade);

    }

    private static Grade calculateRequiredSubjectGrade(long score) {
        if (score >= 95) {
            return Grade.A;
        } else if (score >= 90) {
            return Grade.B;
        } else if (score >= 80) {
            return Grade.C;
        } else if (score >= 70) {
            return Grade.D;
        } else if (score >= 60) {
            return Grade.F;
        } else {
            return Grade.N;
        }
    }

    public enum Grade {
        A, B, C, D, F, N
    }
}
