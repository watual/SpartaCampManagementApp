package campManage.studentManagement;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

public class JsonTest2 {
    public static JSONObject jsontest2() throws IOException, ParseException {
        JSONObject jsonObject = JsonTest.createJsonData();
        System.out.println(jsonObject);
        return jsonObject;
    }
}
