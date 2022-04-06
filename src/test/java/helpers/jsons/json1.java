package helpers.jsons;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class json1 {
//    RandomStringGenerator rsg = new RandomStringGenerator();
    public static void main(String[] args) {
        String sd = json1.RandomMapReg().toString();
        System.out.println("Random String is: " + sd);
    }
    public static int random (){
        Random random = new Random();
        int x = random.nextInt(1000000);
        return x;
    }
    public static String RandomMapPost (){
        Random random = new Random();
        int x = random.nextInt(1000000);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", x);
        map.put("id", x);
        map.put("title", "abcabc");
        map.put("body", "testing purpose");
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();
        return jsonfinal;
    }
    public static String RandomMapPostSec (){
        Random random = new Random();
        int x = random.nextInt(1000000);
        Map<String, Object> map = new HashMap<>();
        map.put("userId", x);
        map.put("title", "abcabc");
        map.put("body", "testing purpose");
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();
        return jsonfinal;
    }
    public static String RandomMapUsers (){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("id", "1");
        map.put("name", "test");
        map.put("username", "testname");
        map.put("email", "testing@gmail.com");
        Map<String, Object> layer2 = new LinkedHashMap<String, Object>();
        layer2.put("street", "Kulas Light");
        layer2.put("suite", "Kula");
        layer2.put("city", "Kulas Light");
        layer2.put("zipcode", "73021");

        map.put("address", layer2);

        Map<String, Object> layer3 = new LinkedHashMap<String, Object>();
        layer3.put("lat", "31");
        layer3.put("lng", "21");
        layer2.put("geo", layer3);

        map.put("phone", "1231231");
        map.put("website", "Kula.org");

        Map<String, Object> layer4 = new LinkedHashMap<String, Object>();
        layer4.put("name", "Kulas Light");
        layer4.put("catchPhrase", "Kulas Light");
        layer4.put("bs", "Kulas Light");
        map.put("company", layer4);

        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();
        return jsonfinal;
    }
    public static String RandomMapReg (){
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("email", RandomStringGenerator.RandomString() + "@gmail.com");
        map.put("password", RandomStringGenerator.RandomString());
        JSONObject json = new JSONObject(map);
        String jsonfinal = json.toString();
        return jsonfinal;
    }
}
