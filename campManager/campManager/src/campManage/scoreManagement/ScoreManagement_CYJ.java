package campManage.scoreManagement;

import java.io.File;
import java.util.Scanner;

public class ScoreManagement_CYJ {
    public static void run() throws Exception {

        ScoreMethod_CYJ scoreMethod_cyj = new ScoreMethod_CYJ();
        boolean flag = true;

        while (flag) {
            System.out.println("==================================");
            System.out.println("점수 관리 실행 중...");
            System.out.println("1. 수강생의 과목별 시험 회차 및 점수 등록");
            System.out.println("2. 수강생의 과목별 회차 점수 수정");
            System.out.println("3. 수강생의 특정 과목 회차별 등급 조회");
            System.out.println("4. 수강생의 과목별 평균 등급을 조회");
            System.out.println("5. 특정 상태 수강생들의 필수 과목 평균 등급을 조회");
            System.out.println("6. 메인 화면 이동");
            System.out.print("관리 항목을 선택하세요...");

            Scanner sc = new Scanner(System.in);
            int scoreManagementOption = Integer.parseInt(sc.nextLine());

            switch (scoreManagementOption) {
                case 1 -> scoreMethod_cyj.crateScore(); // 수강생의 과목별 시험 회차 및 점수 등록
                case 2 -> scoreMethod_cyj.updateRoundScoreBySubject(); // 수강생의 과목별 회차 점수 수정
                case 3 -> scoreMethod_cyj.inquireRoundGradeBySubject(); // 수강생의 특정 과목 회차별 등급 조회
                case 4 -> scoreMethod_cyj.avgGradeBySubject(); // 수강생의 과목별 평균 등급을 조회
                case 5 -> scoreMethod_cyj.avgGradeForRequiredSubjectByState(); // 특정 상태 수강생들의 필수 과목 평균 등급을 조회
                case 6 -> flag = false; // 메인 화면 이동
                default -> {
                    System.out.println("잘못된 입력입니다.\n메인 화면 이동...");
                    flag = false;
                }
            }
        }
    }
}
