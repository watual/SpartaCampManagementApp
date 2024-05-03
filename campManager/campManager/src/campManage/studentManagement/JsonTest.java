package campManage.studentManagement;

import java.io.*;
import java.util.Scanner;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JsonTest {
    public static String uuidCreate() {
        return UUID.randomUUID().toString();
    }

    public static JSONObject createJsonData() {
        // 모든 데이터를 담을 JSONObject 생성
        Scanner sc = new Scanner(System.in);
        System.out.println("이름을 입력해주세요.");
        String name = sc.nextLine();
        String averageScore = "평균등급 : ";
        JSONObject productInfo = new JSONObject();
        productInfo.put("ID", uuidCreate());
        productInfo.put("name", name);

        JSONObject extraProductInfo = new JSONObject();
        extraProductInfo.put("Java", averageScore + "C");
        extraProductInfo.put("C#", averageScore + "D");
        productInfo.put("필수 과목", extraProductInfo);

        JSONArray itemList = new JSONArray();

        JSONObject item1 = new JSONObject();
        item1.put("item_no", 21);
        item1.put("item_name", "L/Blue");
        itemList.add(item1);
        return productInfo;

    }

    public static String jsonToString(JSONObject jsonObject) {
        return jsonObject.toString();
    }

}
