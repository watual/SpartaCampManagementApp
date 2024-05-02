import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class CampManagementApp {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("D:\\spajava\\SpartaCampManagementApp\\campManager\\campManager\\src\\campManage\\src\\Database.txt"));
        ArrayList<String> a = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            a.add(line);
        }
        for (int i = 0; i < a.size(); i++) {
            System.out.println(a.get(i));
        }
    }
}
