package helpers;

import java.util.Random;

public class RandomStringGenerator {
    public static void main(String[] args) {
        System.out.println("Random String is: " + RandomStringGenerator.RandomString());
    }
    public static String RandomString (){
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder sb = new StringBuilder();

        Random random = new Random();

        int length = 9;

        for(int i = 0; i < length; i++) {

            int index = random.nextInt(alphabet.length());

            char randomChar = alphabet.charAt(index);

            sb.append(randomChar);
        }
        String randomString = sb.toString();
        return randomString;
    }
}