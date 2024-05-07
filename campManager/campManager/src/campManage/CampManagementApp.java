package campManage;

import campManage.src.DBConfig;
import campManage.studentManagement.StudentManagement;
import campManage.studentManagement.StudentManagement_LJS;

import java.util.Scanner;

import campManage.scoreManagement.ScoreManagement_BWH;
import campManage.scoreManagement.ScoreManagement_CYJ;
import campManage.scoreManagement.ScoreManagement_KMG;
import campManage.studentManagement.StudentManagement_KDH;

public class CampManagementApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        DBConfig.initDBConfig();
        loop:
        while (true) {
            System.out.println("========================================");
            System.out.println("= = = 실행할 프로젝트를 선택해주세요 = = =");
            System.out.println("1. 학생관리");
            System.out.println("4. 점수관리 _ 최유진");
            System.out.println("5. 점수관리 _ 김민규");
            System.out.println("6. 점수관리 _ 백원하");
            System.out.println("0. 프로그램 종료");
            switch (sc.nextLine()) {
                case "1" -> StudentManagement.run();
                case "2" -> StudentManagement.run();
                case "3" -> StudentManagement.run();
//                case "1" -> StudentManagement_KDH.run();
//                case "2" -> StudentManagement_LJS.run();
//                case "3" -> StudentManagement_BWH.run();
                case "4" -> ScoreManagement_CYJ.run();
                case "5" -> ScoreManagement_KMG.run();
                case "6" -> ScoreManagement_BWH.run();
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
