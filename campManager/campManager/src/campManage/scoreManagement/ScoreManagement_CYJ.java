package scoreManagement;

import java.io.File;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import campManage.model.Subject;
import campManage.model.Score;
import campManage.model.Student;

public class ScoreManagement_CYJ {

    private static List<campManage.model.Student> studentStore;
    private static List<Subject> subjectStore;
    private static List<campManage.model.Score> scoreStore;
    private static String SUBJECT_TYPE_MANDATORY = "MANDATORY";
    private static String SUBJECT_TYPE_CHOICE = "CHOICE";
    private static int studentIndex;
    private static final String INDEX_TYPE_STUDENT = "ST";
    private static int subjectIndex;
    private static final String INDEX_TYPE_SUBJECT = "SU";
    private static int scoreIndex;
    private static final String INDEX_TYPE_SCORE = "SC";

    private static Scanner sc;

    private static void setInitData() {
        studentStore = new ArrayList();
        subjectStore = List.of(new Subject(sequence("SU"), "Java", SUBJECT_TYPE_MANDATORY), new Subject(sequence("SU"), "객체지향", SUBJECT_TYPE_MANDATORY), new Subject(sequence("SU"), "Spring", SUBJECT_TYPE_MANDATORY), new Subject(sequence("SU"), "JPA", SUBJECT_TYPE_MANDATORY), new Subject(sequence("SU"), "MySQL", SUBJECT_TYPE_MANDATORY), new Subject(sequence("SU"), "디자인 패턴", SUBJECT_TYPE_CHOICE), new Subject(sequence("SU"), "Spring Security", SUBJECT_TYPE_CHOICE), new Subject(sequence("SU"), "Redis", SUBJECT_TYPE_CHOICE), new Subject(sequence("SU"), "MongoDB", SUBJECT_TYPE_CHOICE));
        scoreStore = new ArrayList();
    }

    private static String sequence(String type) {
        switch (type) {
            case "ST":
                ++studentIndex;
                return "ST" + studentIndex;
            case "SU":
                ++subjectIndex;
                return "SU" + subjectIndex;
            default:
                ++scoreIndex;
                return "SC" + scoreIndex;
        }
    }





    //수강생의 과목별 시험 회차 및 점수 등록
    public void createScore() {
        System.out.println("필수과목 입력은 1, 선택과목 입력은 2를 입력하세요.");
        int choiceNum = sc.nextInt();
        switch (choiceNum) {
            case 1 -> writeMandatorySubject();
            case 2 -> writeChoiceSubject();
            default -> System.out.println("1, 2중에 입력해주세요.");
        }
    }

    //수강생의 과목별 시험 회차 및 점수 등록 - 필수과목 입력 받기
    public void writeMandatorySubject() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Java 과목 점수를 등록하고 싶으시다면: 1");
        System.out.println("객체지향 과목 점수를 등록하고 싶으시다면: 2");
        System.out.println("Spring 과목 점수를 등록하고 싶으시다면: 3");
        System.out.println("JPA 과목 점수를 등록하고 싶으시다면: 4");
        System.out.println("MySQL 과목 점수를 등록하고 싶으시다면: 5");
        System.out.println("을 입력해주세요.");

        int subject_id = sc.nextInt();
        //리스트의 subject_id와 일치하는 값 찾아서 그 과목을 선택


        System.out.println("회차에 맞는 점수를 입력해주세요.");
        //1회차는 무조건 받고, 2~10회차는 사용자 입력, 중간에 빠져나갈 수 있게 함.
    }

    //수강생의 과목별 시험 회차 및 점수 등록 - 선택 과목 입력 받기
    public void writeChoiceSubject() {
        Scanner sc = new Scanner(System.in);
        System.out.println("선택과목: 디자인 패턴, Spring Security, Redis, MongoDB");
        System.out.println("과목을 선택하고 회차에 맞는 점수를 입력해주세요.");
        String subject = sc.nextLine();
        if (subject.equals("디자인 패턴")||subject.equals("Spring Security")||subject.equals("Redis")||subject.equals("MongoDB")) {
            System.out.println("입력할 과목: " + ", 회차: "); //나중에 리스트 만들어서 넣어주기
        } else {
            System.out.println("과목명을 잘 못 입력하셨습니다.");
        }
    }

    public void checkCountofSubject() {
        //회차정보1~10
        //점수범위 1~100
        //이미 회차점수 등록되어 있으면 등록x (중복해서 등록 불가)
        //점수 등록하면 자동으로 등급 추가 저장

        }
    }



    //수강생의 과목별 회차 점수 수정
    public void updateRoundScoreBySubject() {
        //1. 과목 선택
        //2. 회차 선택
        //3. 점수 수정

    }

    //수강생의 특정 과목 회차별 등급 조회
    public void inquireRoundGradeBySubject() {
        //1. 과목 선택
        //2. 회차 선택
        //(등급 조회: 점수 -> 등급 변환) => 메서드 뽑기(필수, 선택)

    }

    public enum Grade {
        A, B, C, D, F, N
    }
    public char mandatoryRankFactory(int score) {
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

    public char choiceRankFactory(int score) {
        if (score >= 90) {
            return Grade.A;
        } else if (score >= 80) {
            return Grade.B;
        } else if (score >= 70) {
            return Grade.C;
        } else if (score >= 60) {
            return Grade.D;
        } else if (score >= 50) {
            return Grade.F;
        } else {
            return Grade.N;
        }
    }
}
