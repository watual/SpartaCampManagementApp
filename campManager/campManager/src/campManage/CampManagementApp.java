package campManage;

import campManage.studentManagement.StudentManagement_LJS;

import java.util.ArrayList;
import java.util.Scanner;

import campManage.scoreManagement.ScoreManagement;
import campManage.scoreManagement.ScoreManagement_BWH;
import campManage.scoreManagement.ScoreManagement_CYJ;
import campManage.scoreManagement.ScoreManagement_KMG;
import campManage.studentManagement.StudentManagement_BWH;
import campManage.studentManagement.StudentManagement_KDH;
import campManage.studentManagement.StudentManagement_LJS;

public class CampManagementApp {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        loop:
        while (true) {
            System.out.println("========================================");
            System.out.println("= = = 실행할 프로젝트를 선택해주세요 = = =");
            System.out.println("1. 학생관리 _ 김도훈");
            System.out.println("2. 학생관리 _ 이재성");
            System.out.println("3. 학생관리 _ 백원하");
            System.out.println("4. 점수관리 _ 최유진");
            System.out.println("5. 점수관리 _ 김민규");
            System.out.println("6. 점수관리 _ 백원하");
            System.out.println("0. 프로그램 종료");
            switch (sc.nextLine()) {
                case "1" -> StudentManagement_KDH.run();
                case "2" -> StudentManagement_LJS.run();
                case "3" -> StudentManagement_BWH.run();
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
