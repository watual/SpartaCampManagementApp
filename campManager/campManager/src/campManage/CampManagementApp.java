package campManage;

import campManage.scoreManagement.ScoreManagement;
import campManage.src.DBConfig;
import campManage.studentManagement.StudentManagement;

import java.util.Scanner;


public class CampManagementApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DBConfig.initDBConfig();
        loop:
        while (true) {
            System.out.println("========================================");
            System.out.println("= = = 실행할 프로젝트를 선택해주세요 = = =");
            System.out.println("0. 프로그램 종료");
            System.out.println("1. 학생관리");
            System.out.println("2. 점수관리");
            switch (sc.nextLine()) {
                case "1" -> StudentManagement.run();
                case "2" -> ScoreManagement.run();
                case "0" -> {
                    System.out.println("= = = 프로그램을 종료합니다 = = =");
                    break loop;
                }
                default -> System.out.println("재실행");
            }
        }
        //여기로 break
    }
}
