package campManage.studentManagement;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class StudentManagement_KDH {
    public static void run() throws IOException {
        test();
    }

    //점수
    public static void test() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("campManager/campManager/src/campManage/src/Database.txt"));
        File file = new File("campManager/campManager/src/campManage/src/Database.txt");
        Scanner sc = new Scanner(System.in);
        //텍스트파일에 입력
        BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
        writer.newLine();
        System.out.println("id Number 입력");
        int id = sc.nextInt();
        writer.write(id + ":");
        System.out.println("이름 입력");
        String name = sc.next();
        writer.write(name + ":");
        System.out.println("과목 입력");
        String subject = sc.next();
        writer.write(subject + ":");
        System.out.println("상태 입력");
        String status = sc.next();
        writer.write(status);
        writer.flush();
        writer.close();
        //조회
        System.out.println("[ 전체조회 또는 개별조회 ]");
        String typing = sc.next();
        if (typing.equals("전체조회")) {

            String line = "";
            while ((line = reader.readLine()) != null) { // 한줄씩 읽음
                System.out.println(line);
            }
        } else if (typing.equals("개별조회")) {
            System.out.println("id를 입력해주세요");
            boolean idcheck = true;
            String idtyping = sc.next();
            String line = "";
            while ((line = reader.readLine()) != null) { // 한줄씩 읽음
                String[] section = line.split(":");
                if (idtyping.equals(section[0])) {
                    System.out.println("아이디 : " + section[0] + ", 이름 : " + section[1] + ", 수강 과목 : " + section[2] + ", 상태 : " + section[3]);
                    idcheck = false;
                    break;
                }
            }
            if (idcheck) {
                System.out.println("아이디가 없거나 잘못된 입력입니다.");
            }
        }
    }
}

