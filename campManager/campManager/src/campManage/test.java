package campManage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class test {
    public static void main(String[] args) throws IOException {


        Random rd = new Random();

        String[] first = {"김", "조", "정", "최", "나", "허", "박", "이"};
        String[] second = {"지", "인", "동", "석", "원", "현", "재", "민"};

        BufferedWriter br = new BufferedWriter(new FileWriter("D:\\spajava\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\Database.txt"));

        for (int i = 0; i < 30; i++) {
            String name = first[rd.nextInt(first.length)] + second[rd.nextInt(second.length)] + second[rd.nextInt(second.length)];
            br.write(name +"\n");
        }

        br.close();
    }
}
