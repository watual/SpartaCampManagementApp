package campManage.studentManagement;

import campManage.src.DBConfig;

import java.util.Scanner;

public class StudentManagement_BWH {
    public static void run() throws  Exception{
        Scanner sc = new Scanner(System.in);
        StudentRegistration_BWH sr = new StudentRegistration_BWH();
        end:
        while (true) {

            System.out.println("========================================");
            System.out.println("# # 학생관리 # #");
            System.out.println("0. 메인으로 돌아가기");
            System.out.println("1. 학생정보 조회");
            System.out.println("2. 새로운 학생정보 등록");
            System.out.println("3. 기존 학생정보 수정");
            System.out.println("4. 학생정보 삭제");

            switch (sc.nextLine().charAt(0)) {
                case '0' -> {break end;}
                case '1' -> sr.inquiry();
                case '2' -> sr.registrate();
                case '3' -> sr.modify();
                case '4' -> sr.delete();
                default -> {
                    System.out.println("입력이 올바르지 않습니다. 번호를 입력해주세요.");
                    throw new Exception();
                }
            }
        }
    }
}
