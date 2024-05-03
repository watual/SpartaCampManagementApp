package campManage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
public class test {
    public static void main(String[] args) throws IOException {


        Random rd = new Random();

        String[] first = {"김", "조", "정", "최", "나", "허", "박", "이"};
        String[] second = {"지", "인", "동", "석", "원", "현", "재", "민"};
        String[] must = {"Java","객체지향","Spring","JPA","MySQL"};
        String[] may = {"디자인 패턴","Spring Security","Redis","MongoDB"};
        String[] rr = {"Green", "Red","Yellow"};
        String[] sl = new String[3];
        String[] sk = new String[2];
        BufferedWriter br = new BufferedWriter(new FileWriter("D:\\spajava\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\Database.txt"));

        for (int i = 0; i < 30; i++) {

            String name = first[rd.nextInt(first.length)] + second[rd.nextInt(second.length)] + second[rd.nextInt(second.length)];
            for(int j = 0; j < sl.length; j++) {
                sl[j] = must[rd.nextInt(must.length)];
                for(int k = 0; k <j; k++) {
                    if(sl[j]==sl[k]){
                        j--;
                    }
                }
            }
            String st = sl[0] +","+ sl[1] +","+ sl[2];
            for(int j = 0; j < sk.length; j++) {
                sk[j]=may[rd.nextInt(may.length)];
                for(int k = 0; k <j; k++) {
                    if(sk[j]==sk[k]){
                        j--;
                    }
                }
            }
            String sr = sk[0] +","+ sk[1];

            String sb = rr[rd.nextInt(rr.length)];
            br.write(name+";"+st+";"+sr+";"+sb+";"+"\n");
        }

        br.close();
    }
}
