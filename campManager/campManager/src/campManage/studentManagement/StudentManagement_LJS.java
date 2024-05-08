package campManage.studentManagement;

import java.util.*;

public class StudentManagement_LJS {
    // 필드

    //메서드
    //값 출력
    public static void run() throws Exception {
        Scanner s1 = new Scanner(System.in);
        StudentManager st = new StudentManager();
        boolean flag = true;
        while (flag) {
            System.out.println("==========수강생 처리=========");
            System.out.println("선택해주세요");
            System.out.println("1. 생성");
            System.out.println("2. 수정");
            System.out.println("3. 삭제");
            System.out.println("4. 조회");
            System.out.println("5. 종료");
            switch (s1.nextInt()) {
                case 1 -> st.makeStu();
                case 2 -> st.reStu();
                case 3 -> st.delStu();
                case 4 -> st.serStu();
                case 5 -> flag = false;
            }

        }

    }
}