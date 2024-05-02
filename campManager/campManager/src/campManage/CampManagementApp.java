import model.Student;
import studentManagement.StudentManagement_LJS;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CampManagementApp {
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        StudentManagement_LJS sm = new StudentManagement_LJS();
        boolean stop = true;
        while (stop) {
            System.out.println("====학생 조회====");
            System.out.println("1. 전체 조회");
            System.out.println("2. 이름 조회");
            System.out.println("3.  끝");
            switch (sc.nextInt()) {
                case 1->sm.getStudent();
//                case 2:
//                    System.out.println("이름을 입력하세요");
//                    if(sm.equals(sc.next())){

                    }

            }

        }


    }
}
